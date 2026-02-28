package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public final class RestUtil {
    private RestUtil() {
    }

    public static Response get(String url, Map<String, String> headers) {
        return request(headers, null).get(url).then().extract().response();
    }

    public static Response post(String url, Map<String, String> headers, Object body) {
        return request(headers, body).post(url).then().extract().response();
    }

    public static Response put(String url, Map<String, String> headers, Object body) {
        return request(headers, body).put(url).then().extract().response();
    }

    public static Response patch(String url, Map<String, String> headers, Object body) {
        return request(headers, body).patch(url).then().extract().response();
    }

    public static Response delete(String url, Map<String, String> headers) {
        return request(headers, null).delete(url).then().extract().response();
    }

    private static RequestSpecification request(Map<String, String> headers, Object body) {
        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);
        if (headers != null && !headers.isEmpty()) {
            request.headers(headers);
        }
        if (body != null) {
            request.body(body);
        }
        return request;
    }
}
