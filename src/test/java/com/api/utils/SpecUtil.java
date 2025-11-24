package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constants.Roles;
import com.request.models.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
	
	// GET DEL
	public static RequestSpecification requestSpec() {
		RequestSpecification request = new RequestSpecBuilder()
		.setBaseUri(ConfigManager2.getProperty("BASE_URL"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		return request;
	}

	
	// POST-PUT-PATCH {Body}
		public static RequestSpecification requestSpec(Object payload) {
			RequestSpecification request = new RequestSpecBuilder()
			.setBaseUri(ConfigManager2.getProperty("BASE_URL"))
			.setContentType(ContentType.JSON)
			.setAccept(ContentType.JSON)
			.setBody(payload)
			.log(LogDetail.URI)
			.log(LogDetail.METHOD)
			.log(LogDetail.HEADERS)
			.log(LogDetail.BODY)
			.build();
			return request;
		}
		
		public static RequestSpecification requestSpecificationWithAuth(Roles role) {
			RequestSpecification requestSpecification = new RequestSpecBuilder()
			.setBaseUri(ConfigManager2.getProperty("BASE_URL"))
			.setContentType(ContentType.JSON)
			.setAccept(ContentType.JSON)
			.addHeader("Authorization", AuthTokenProvider.getToken(role))
			.log(LogDetail.URI)
			.log(LogDetail.METHOD)
			.log(LogDetail.HEADERS)
			.log(LogDetail.BODY)
			.build();
			return requestSpecification;
		}
		
		public static ResponseSpecification resposeSpec_OK() {
			ResponseSpecification responseSpecification = new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
			.expectResponseTime(Matchers.lessThan(1500L))
			.log(LogDetail.ALL)
			.build();
			return responseSpecification;
		}
		
		public static ResponseSpecification resposeSpec(int statusCode) {
			ResponseSpecification responseSpecification = new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(statusCode)
			.expectResponseTime(Matchers.lessThan(1500L))
			.log(LogDetail.ALL)
			.build();
			return responseSpecification;
		}
		
		public static ResponseSpecification resposeSpec_TEXT(int statusCode) {
			ResponseSpecification responseSpecification = new ResponseSpecBuilder()
			.expectStatusCode(statusCode)
			.expectResponseTime(Matchers.lessThan(1500L))
			.log(LogDetail.ALL)
			.build();
			return responseSpecification;
		}
}
