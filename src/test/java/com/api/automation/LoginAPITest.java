package com.api.automation;

import static com.api.utils.specUtil.requestBuilder;
import static com.api.utils.specUtil.responseBuilder_Ok;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	UserCredentials creds = new UserCredentials("iamfd","password");
	
	@Test
	
	public void LoginTest()
	{
		given()
		 .spec(requestBuilder())
		 .body(creds)
		.when()
		 .post("/login")
		.then()
		 .spec(responseBuilder_Ok())
		 .body("data.token", notNullValue())
		 .and()
		 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponseSchema/loginAPIResponseSchema.json"));
		 	 
	}
}
