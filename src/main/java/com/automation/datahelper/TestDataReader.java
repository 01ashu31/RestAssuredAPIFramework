package com.automation.datahelper;

import com.automation.utils.JsonUtil;

import java.util.List;

public class TestDataReader {

    public <T> T readObject(String resourcePath, Class<T> clazz) {
        return JsonUtil.fromJsonFile(resourcePath, clazz);
    }

    public <T> List<T> readList(String resourcePath, Class<T> clazz) {
        return JsonUtil.fromJsonArrayFile(resourcePath, clazz);
    }
}
