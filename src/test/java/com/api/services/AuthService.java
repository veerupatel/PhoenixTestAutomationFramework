package com.api.services;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthService {

	// Service class!! it is going to hold the APIs that belongs to the Auth

	private static final String LOGIN_ENDPOINT = "/login";

	public Response login(UserCredentials userCredentials) {
		  Response response = given()
				  .spec(SpecUtil.requestSpec(userCredentials))
				  .when()
				  .post(LOGIN_ENDPOINT);
		          return response;
	}
}
