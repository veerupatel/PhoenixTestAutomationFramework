package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;
import com.request.models.UserCredentials;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	
	@Test
	public void loginAPITest() {
		UserCredentials credentials = new UserCredentials("iamfd", "password");
		RestAssured.given()
		.spec(SpecUtil.requestSpec(credentials))
		.when()
		.post("login")
		.then()
		.spec(SpecUtil.resposeSpec_OK())
		.body("message", Matchers.equalTo("Success"))
		.body("data.token", Matchers.notNullValue())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-Schema/LoginAPIResponseSchema.json"))
		.extract().response();
	}

}
