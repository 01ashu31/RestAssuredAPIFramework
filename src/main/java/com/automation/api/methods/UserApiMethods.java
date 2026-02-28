package com.automation.api.methods;

import com.automation.constants.EndpointKeys;
import com.automation.datahelper.Headers;
import com.automation.datahelper.UrlGenerator;
import com.automation.models.CreateUserRequest;
import com.automation.utils.RestUtil;
import io.restassured.response.Response;

public class UserApiMethods {
    private final UrlGenerator urlGenerator;
    private final Headers headers;

    public UserApiMethods() {
        this.urlGenerator = new UrlGenerator();
        this.headers = new Headers();
    }

    public Response createUser(CreateUserRequest requestBody) {
        return RestUtil.post(urlGenerator.build(EndpointKeys.CREATE_USER), headers.getHeader(), requestBody);
    }

    public Response getUserById(int userId) {
        return RestUtil.get(urlGenerator.build(EndpointKeys.GET_USER_BY_ID, userId), headers.getHeader());
    }

    public Response updateUser(int userId, CreateUserRequest requestBody) {
        return RestUtil.put(urlGenerator.build(EndpointKeys.UPDATE_USER, userId), headers.getHeaderWithAuth(), requestBody);
    }

    public Response deleteUser(int userId) {
        return RestUtil.delete(urlGenerator.build(EndpointKeys.DELETE_USER, userId), headers.getHeaderWithAuth());
    }
}
