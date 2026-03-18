package com.api.automation;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.Utils.*;

public class CountAPIRequestTest {

	@Test
	public void countAPITest()
	{
		given()
			.baseUri(getProperty("BASE_URL"))
			.and()
			.header("Authorization", getToken(FD))
			.and()
			.log().uri()
			.and()
			.log().method()
			.and()
			.log().body()
		.when()
			.get("dashboard/count")
		.then()
			.log().all()
			.statusCode(200)
			.and()
			.time(lessThan(2000L))
			.and()
			.body("message", equalTo("Success"))
			.and()
			.body("data", notNullValue())
			.and()
			.body("data.size()", equalTo(3))
			.and()
			.body("data.count", everyItem(greaterThanOrEqualTo(0)))
			.and()
			.body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponseSchema/CountAPIResponseSchema_FD.json"));	
	}
	
	
	@Test
	public void invalidAuthTest()
	{
		given()
			.baseUri(getProperty("BASE_URL"))
			.and()
			.header("Authorization", "")
		.when()
			.get("dashboard/count")
		.then()
			.log().all()
			.statusCode(401);
	}
}
