package com.api.utils;

import static com.api.constants.Role.*;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	private AuthTokenProvider()
	{
		
	}

	public static String getToken(Role role) {

		UserCredentials u1 = null;
		if(role == FD)
		{
			u1 = new UserCredentials("iamfd", "password");
		}
		else if(role == SUP)
		{
			u1 = new UserCredentials("iamsup", "password");
		}
		else if(role == ENG)
		{
			u1 = new UserCredentials("iameng", "password");
		}
		else if(role == QC)
		{
			u1 = new UserCredentials("iamqc", "password");
		}
		
		
		String token = given()
			.baseUri(ConfigManager.getProperty("BASE_URL"))
			.and()
			.contentType(ContentType.JSON)
			.body(u1)
			.log().all()
		.when()
			.post("/login")
		.then()
			.log().ifValidationFails()
			.log().all()
			.statusCode(200)
			.body("message", Matchers.equalTo("Success"))
			.extract().response().jsonPath().getString("data.token");
		
		return token;
		
	}

}
