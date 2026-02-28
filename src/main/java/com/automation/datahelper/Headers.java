package com.automation.datahelper;

import com.automation.utils.PropertyReader;

import java.util.HashMap;
import java.util.Map;

public class Headers {

    public Map<String, String> getHeader() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        addApiKey(headers);
        return headers;
    }

    public Map<String, String> getHeaderForFormData() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "multipart/form-data");
        headers.put("Accept", "application/json");
        addApiKey(headers);
        return headers;
    }

    public Map<String, String> getHeaderWithAuth() {
        Map<String, String> headers = getHeader();
        headers.put("Authorization", "Bearer " + PropertyReader.get("auth.token"));
        return headers;
    }

    private void addApiKey(Map<String, String> headers) {
        String key = PropertyReader.get("api.key");
        if (!key.equals("NA")) {
            headers.put("x-api-key", key);
        }
    }
}
