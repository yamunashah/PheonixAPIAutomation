package com.api.automation;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.Utils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPIRequestTest {

	
	@Test
	public void MasterAPITest()
	{
		given()
			.baseUri(Utils.getProperty("BASE_URL"))
			.and()
			.header("Authorization",AuthTokenProvider.getToken(Role.FD))
			.and()
			.contentType("")
			.log().all()
		.when()
			.post("/master")
		.then()
			.log().all()
			.statusCode(200)
			.and()
			.time(lessThan(1000L))
			.and()
			.body("$",hasKey("message"))
			.and()
			.body("$",hasKey("data"))
			.and()
			.body("message",equalTo("Success"))
			.and()
			.body("data",notNullValue())
			.and()
			.body("data",hasKey("mst_oem"))
			.and()
			.body("data.mst_model.name", Matchers.not(everyItem(blankOrNullString())))
			.and()
			.body("data.mst_oem.id",everyItem(greaterThanOrEqualTo(0)))
			.and()
			.body("data.mst_oem.name",notNullValue())
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponseSchema/MasterAPIResponseSchema_FD.json"));		
			
	}
	
	@Test
	public void invalidAuthToken()
	{
		given()
			.baseUri(Utils.getProperty("BASE_URL"))
			.and()
			.contentType("")
			.log().all()
		.when()
			.post("/master")
		.then()
			.log().all()
			.statusCode(401);
	}
}
