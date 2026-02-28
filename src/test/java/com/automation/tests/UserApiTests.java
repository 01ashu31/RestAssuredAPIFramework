package com.automation.tests;

import com.automation.api.methods.UserApiMethods;
import com.automation.datahelper.TestDataReader;
import com.automation.listeners.RetryAnalyzer;
import com.automation.listeners.TestListener;
import com.automation.models.CreateUserRequest;
import com.automation.models.CreateUserResponse;
import com.automation.models.GetUserResponse;
import com.automation.models.GetUserTestData;
import com.automation.utils.JsonUtil;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

@Listeners(TestListener.class)
public class UserApiTests {

    private final UserApiMethods userApiMethods = new UserApiMethods();
    private final TestDataReader testDataReader = new TestDataReader();

    @DataProvider(name = "createUserData")
    public Iterator<Object[]> createUserDataProvider() {
        List<CreateUserRequest> requests = testDataReader.readList("testdata/create-users.json", CreateUserRequest.class);
        return requests.stream().map(data -> new Object[]{data}).iterator();
    }

    @Test(dataProvider = "createUserData", retryAnalyzer = RetryAnalyzer.class)
    @Description("Validate create user API with reusable request builder and POJO serialization")
    public void createUser_shouldReturnCreated(CreateUserRequest request) {
        Response response = userApiMethods.createUser(request);
        CreateUserResponse responseBody = JsonUtil.fromJsonString(response.asString(), CreateUserResponse.class);

        Assert.assertEquals(response.statusCode(), 201, "Status code should be 201 for create user");
        Assert.assertEquals(responseBody.getName(), request.getName(), "Response name should match request");
        Assert.assertEquals(responseBody.getJob(), request.getJob(), "Response job should match request");
        Assert.assertNotNull(responseBody.getId(), "Created user id should not be null");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Description("Validate get user by id API and assert response fields")
    public void getUserById_shouldReturnExpectedUser() {
        GetUserTestData data = testDataReader.readObject("testdata/get-user.json", GetUserTestData.class);
        Response response = userApiMethods.getUserById(data.getId());
        GetUserResponse responseBody = JsonUtil.fromJsonString(response.asString(), GetUserResponse.class);

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200 for get user");
        Assert.assertEquals(responseBody.getData().getId(), data.getId(), "User id should match expected");
        Assert.assertEquals(responseBody.getData().getEmail(), data.getExpectedEmail(), "User email should match expected");
    }
}
