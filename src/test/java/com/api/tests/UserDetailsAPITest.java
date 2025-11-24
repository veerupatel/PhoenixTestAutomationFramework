package com.api.tests;

import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager2;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class UserDetailsAPITest {

	@Test
	public void UserDetailsAPITest() {
		RestAssured.given()
				.spec(SpecUtil.requestSpecificationWithAuth(Roles.FD))
				.when().get("userdetails").then()
				.spec(SpecUtil.resposeSpec_OK())
						.and()
						.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-Schema/UserDetailsResponseSchema.json"));
		
	}

}
