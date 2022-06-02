package com.cbus.aem.core.models;

import com.cbus.aem.core.config.PetsApiConfig;
import com.cbus.aem.core.data.PetType;
import com.cbus.aem.core.services.PetsApiConfigService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Model(
    adaptables = SlingHttpServletRequest.class
    , resourceType = PetTypesModel.RESOURCE_TYPE
)
public class PetTypesModel {
    protected static final String RESOURCE_TYPE = "cbustest/components/pets";

    private static final String PET_TYPE_DELIMITER = ",";

    @Inject
    PetsApiConfigService service;

    private static final Logger log = LoggerFactory.getLogger(PetTypesModel.class);

    public List<PetType> petTypeList = new ArrayList<PetType>();

    public String defaultOption = "CAT";

    @PostConstruct
    private void initModel() {
        final PetsApiConfig config = service.getConfig();

        Arrays.asList(StringUtils.splitPreserveAllTokens(config.petsTypes(), PET_TYPE_DELIMITER))
            .forEach(type -> {
                petTypeList.add(new PetType(type, type.toUpperCase()));
            });

        String test="";
    }
}
