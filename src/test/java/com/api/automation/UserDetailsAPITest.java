package com.api.automation;

import static com.api.utils.Utils.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import static com.api.constants.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {

	Header authtoken = new Header("Authorization", getToken(FD));
	@Test
	
	public void UserDetialsTest()
	{
		given()
		 .baseUri(getProperty("BASE_URL"))
		 .and()
		 .header(authtoken)
		 .contentType(ContentType.JSON)
		 .log().uri()
		 .log().method()
		 .log().headers()
		 .log().body()
		.when()
		 .get("/userdetails")
		.then()
		 .log().all()
		 .statusCode(200)
		 .and()
		 .time(lessThan(3000L))
		 .and()
		 .body("message", equalTo("Success"))
		 .and()
		 .body("data.first_name",equalTo("fd"))
		 .and()
		 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponseSchema/userDetailsResponseSchema.json"));
		 
		 
	}
}
