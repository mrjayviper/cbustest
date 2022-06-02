package com.cbus.aem.core.servlets;

import com.cbus.aem.core.config.PetsApiConfig;
import com.cbus.aem.core.services.PetsApiConfigService;
import com.cbus.aem.core.data.Owner;
import com.cbus.aem.core.utils.JsonUtils;
import com.cbus.aem.core.utils.IntegrationUtils;
import com.cbus.aem.core.utils.PetsUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
    service = Servlet.class
    , property = {
        Constants.SERVICE_DESCRIPTION + "=JSON Servlet to read the data from the external webservice"
        , "sling.servlet.methods=" + HttpConstants.METHOD_GET
        , "sling.servlet.paths=" + "/bin/getpets"
    }
)
public class PetsServlet extends SlingSafeMethodsServlet {
    private static final Logger log = LoggerFactory.getLogger(PetsServlet.class);

    private static final String JSON_EMPTY = "{}";

    @Reference
    private PetsApiConfigService service;

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws ServletException, IOException {
        log.info("PetsServlet/doGet - initiating servlet request");

        String jsonResponse = JSON_EMPTY;

        List<Owner> ownerList = new ArrayList<Owner>();

        //get URL parameters
        final String type = request.getParameter("type") != null && request.getParameter("type").length() > 0
            ? request.getParameter("type").toUpperCase()
            : StringUtils.EMPTY;

        if (type.length() > 0) {
            try {
                final PetsApiConfig config = service.getConfig();

                final String sourceApiResponse = IntegrationUtils.getResponseFromUrl(
                    config.petsApiUrl()
                    , config.connectionTimeout()
                    , config.readTimeout()
                );

                //just making sure the lists are empty before we start
                //had some weird behaviour when debugging via IDE
                ownerList.clear();

                if (StringUtils.isNotBlank(sourceApiResponse)) {
                    if (JsonUtils.isJsonObject(sourceApiResponse)) {
                        ownerList = PetsUtils.parseJsonObject(sourceApiResponse);
                    } else if (JsonUtils.isJsonArray(sourceApiResponse)) {
                        ownerList = PetsUtils.parseJsonArray(sourceApiResponse);
                    }

                    if (ownerList.size() > 0) {
                        jsonResponse = PetsUtils.getAllPetsFilterByType(ownerList, type);

                        if (jsonResponse.length() == 0) {
                            jsonResponse = JSON_EMPTY;
                        }
                    }
                }
            } catch (Exception exception) {
                log.error("PetsServlet/doGet - error in servlet | type: " + type, exception);
            }
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/Json;charset=UTF-8");

        final PrintWriter writer = response.getWriter();
        writer.write(jsonResponse);
    }
}
