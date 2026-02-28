package com.automation.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public final class JsonUtil {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private JsonUtil() {
    }

    public static <T> T fromJsonFile(String resourcePath, Class<T> clazz) {
        try (InputStream stream = resourceStream(resourcePath);
             InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            return GSON.fromJson(reader, clazz);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to deserialize JSON file: " + resourcePath, e);
        }
    }

    public static <T> List<T> fromJsonArrayFile(String resourcePath, Class<T> clazz) {
        Type type = TypeToken.getParameterized(List.class, clazz).getType();
        try (InputStream stream = resourceStream(resourcePath);
             InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            return GSON.fromJson(reader, type);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to deserialize JSON array file: " + resourcePath, e);
        }
    }

    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

    public static <T> T fromJsonString(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    private static InputStream resourceStream(String resourcePath) {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
        if (stream == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }
        return stream;
    }
}
