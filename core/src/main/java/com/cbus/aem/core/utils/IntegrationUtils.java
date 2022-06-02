package com.cbus.aem.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.tika.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegrationUtils {
    private static Logger log = LoggerFactory.getLogger(IntegrationUtils.class);

    private static final Integer CONNECTION_TIMEOUT = 5000;

    private static final Integer READ_TIMEOUT = 10000;

    public static String getResponseFromUrl(String url, Integer connectionTimeout, Integer readTimeout) throws IOException {
        connectionTimeout = ! Objects.isNull(connectionTimeout) ? connectionTimeout : CONNECTION_TIMEOUT;

        readTimeout = ! Objects.isNull(readTimeout) ? readTimeout : READ_TIMEOUT;

        URL url1 = new URL(url);

        try {
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(connectionTimeout);
            connection.setReadTimeout(readTimeout);

            int connectionStatus = connection.getResponseCode();

            if (connectionStatus == HttpURLConnection.HTTP_OK) {
                return IOUtils.toString(connection.getInputStream());
            } else {
                log.error("IntegrationUtils/getResponseFromUrl - non-200 response received from " + url + ". Status received was " + connectionStatus);
            }
        } catch (IOException exception) {
            throw new IOException("error connecting to host: URL: " + url);
        }

        return StringUtils.EMPTY;
    }
}
