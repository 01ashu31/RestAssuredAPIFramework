package TestAPI;

import org.apache.logging.log4j.core.util.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.http.ContentType;
import listeners.RetryAnalyzer;
import modal.User;

public class GetUserAPITest extends BaseTest{
	
	 @Test(retryAnalyzer = RetryAnalyzer.class)
	    public void createUserTest() {

	        // Create request body using Lombok Builder
	        User user = User.builder()
	                .name("Ashutosh")
	                .job("SDET")
	                .build();

	        // Make API call and extract response
	        String response = given()
	                .contentType(ContentType.JSON)
	                .body(JsonUtils.toJson(user))
	        .when()
	                .post("/users")
	        .then()
	                .statusCode(201)
	                .extract()
	                .asString();

	        // Validation
	        Assert.assertTrue(response.contains("Ashutosh"),
	                "Response does not contain expected name");

	        System.out.println("Response: " + response);
	
	}

}
