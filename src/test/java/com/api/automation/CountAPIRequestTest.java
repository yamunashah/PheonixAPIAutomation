package com.api.automation;

import static com.api.constants.Role.FD;
import static com.api.utils.specUtil.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPIRequestTest {

	@Test
	public void countAPITest()
	{
		given()
			.spec(requestBuilder(FD))
		.when()
			.get("dashboard/count")
		.then()
			.spec(responseBuilder_Ok())
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
			.spec(requestBuilderEmptyHeader())
		.when()
			.get("dashboard/count")
		.then()
			.spec(responseBuilder_401());
	}
}
