package com.automation.datahelper;

import com.automation.utils.PropertyReader;

import java.util.Map;

public class UrlGenerator {

    private final String baseUrl;
    private final Map<String, String> endpoints;

    public UrlGenerator() {
        this.baseUrl = PropertyReader.get("base.url");
        this.endpoints = Map.of(
                "createUser", PropertyReader.get("endpoint.create.user"),
                "getUserById", PropertyReader.get("endpoint.get.user.by.id"),
                "updateUser", PropertyReader.get("endpoint.update.user"),
                "deleteUser", PropertyReader.get("endpoint.delete.user")
        );
    }

    public String build(String endpointKey) {
        String endpoint = endpoints.get(endpointKey);
        if (endpoint == null) {
            throw new IllegalArgumentException("Unsupported endpoint key: " + endpointKey);
        }
        return baseUrl + endpoint;
    }

    public String build(String endpointKey, Object pathParam) {
        return build(endpointKey).replace("{id}", String.valueOf(pathParam));
    }
}
