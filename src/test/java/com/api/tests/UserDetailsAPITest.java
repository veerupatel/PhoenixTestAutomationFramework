package com.api.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.services.UserService;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager2;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class UserDetailsAPITest {

	private UserService userService;

	@BeforeMethod(description = "Setting up the UserService instance")
	public void setup() {
		userService = new UserService();
	}

	@Test
	public void UserDetailsAPITest() {
		userService.userDetails(Roles.FD).then().spec(SpecUtil.resposeSpec_OK()).and().body(
				JsonSchemaValidator.matchesJsonSchemaInClasspath("response-Schema/UserDetailsResponseSchema.json"));

	}

}
