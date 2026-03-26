package com.api.utils;

import static io.restassured.filter.log.LogDetail.*;

import org.hamcrest.Matchers;

import com.api.constants.Role;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class specUtil {

	public static RequestSpecification requestBuilder()
	{
		    RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URL"))
				.and()
				.setContentType(ContentType.JSON)
				.and()
				.log(URI)
				.log(HEADERS)
				.log(METHOD)
				.log(BODY)
				.build();
		    
		    return requestSpec;
	}
	
	public static RequestSpecification requestBuilder(Role role)
	{
		    RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URL"))
				.and()
				.setContentType(ContentType.JSON)
				.and()
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.and()
				.log(URI)
				.log(HEADERS)
				.log(METHOD)
				.log(BODY)
				.build();
		    
		    return requestSpec;
	}
	
	public static RequestSpecification requestBuilderWithBody(Role role, Object payload)
	{
		    RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URL"))
				.and()
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.and()
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.and()
				.setBody(payload)
				.and()
				.log(URI)
				.log(HEADERS)
				.log(METHOD)
				.log(BODY)
				.build();
		    
		    return requestSpec;
	}
	
	
	public static RequestSpecification requestBuilderEmptyHeader()
	{
		    RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URL"))
				.and()
				.setContentType(ContentType.JSON)
				.and()
				.addHeader("Authorization", "")
				.and()
				.log(URI)
				.log(HEADERS)
				.log(METHOD)
				.log(BODY)
				.build();
		    
		    return requestSpec;
	}
	
	
	
	public static RequestSpecification requestBuilderEmptyContentType(Role role)
	{
		    RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URL"))
				.and()
				.setContentType("")
				.and()
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.and()
				.log(URI)
				.log(HEADERS)
				.log(METHOD)
				.log(BODY)
				.build();
		    
		    return requestSpec;
	}
	
	
	public static ResponseSpecification responseBuilder_Ok()
	{
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
			.expectStatusCode(200)
			.expectResponseTime(Matchers.lessThan(1500L))
			//.expectBody("message", Matchers.equalTo("Success"))
			.log(ALL)
			.build();
		
		return responseSpecification;
	}
	
	public static ResponseSpecification responseBuilder_401()
	{
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
			.expectStatusCode(401)
			.expectResponseTime(Matchers.lessThan(1500L))
			.log(ALL)
			.build();
		
		return responseSpecification;
	}
	
}
