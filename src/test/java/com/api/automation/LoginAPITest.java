package com.api.automation;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import static com.api.utils.Utils.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	UserCredentials creds = new UserCredentials("iamfd","password");
	
	@Test
	
	public void LoginTest()
	{
		given()
		 .baseUri(getProperty("BASE_URL"))
		 .and()
		 .body(creds)
		 .contentType(ContentType.JSON)
		 .log().uri()
		 .log().method()
		 .log().body()
		.when()
		 .post("/login")
		.then()
		 .log().all()
		 .statusCode(200)
		 .and()
		 .time(lessThan(3000L))
		 .and()
		 .body("message", equalTo("Success"))
		 .and()
		 .body("data.token", notNullValue())
		 .and()
		 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponseSchema/loginAPIResponseSchema.json"));
		 
		 
	}
}
