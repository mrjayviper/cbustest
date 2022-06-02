package com.cbus.aem.core.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtils {
    public static Boolean isJsonObject(String string) {
        try {
            new JSONObject(string);
        } catch (Exception exception) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public static Boolean isJsonArray(String string) {
        try {
            new JSONArray(string);
        } catch (Exception exception) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
