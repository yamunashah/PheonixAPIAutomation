package com.api.automation;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import static com.api.utils.specUtil.*;

import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {

	//Header authtoken = new Header("Authorization", getToken(Role.FD));
	@Test
	
	public void UserDetialsTest()
	{
		given()
		.spec(requestBuilder(FD))
		.when()
		 .get("/userdetails")
		.then()
		 .spec(responseBuilder_Ok())
		 .and()
		 .body("data.first_name",equalTo("fd"))
		 .and()
		 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponseSchema/userDetailsResponseSchema.json"));
		 	 
	}
}
