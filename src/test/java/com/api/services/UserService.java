package com.api.services;

import com.api.constants.Roles;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserService {
	
	private static final String USERDETAILS_ENDPOINT = "/userdetails";

	public Response userDetails(Roles role) {
		Response response = given()
		.spec(SpecUtil.requestSpecificationWithAuth(Roles.FD))
		.when().get(USERDETAILS_ENDPOINT);
		return response;
	}
}
