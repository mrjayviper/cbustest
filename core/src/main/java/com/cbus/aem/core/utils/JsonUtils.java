package com.cbus.aem.core.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtils {
    private JsonUtils() {
        throw new IllegalStateException("JsonUtils class");
    }

    public static boolean isJsonObject(String string) {
        try {
            new JSONObject(string);
        } catch (Exception exception) {
            return false;
        }

        return true;
    }

    public static boolean isJsonArray(String string) {
        try {
            new JSONArray(string);
        } catch (Exception exception) {
            return false;
        }

        return true;
    }
}
