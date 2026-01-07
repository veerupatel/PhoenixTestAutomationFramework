package com.api.services;

import com.api.constants.Roles;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService {
	
	private static final String USERDETAILS_ENDPOINT = "/userdetails";
	
	private static final Logger lOGGER = LogManager.getLogger(UserService.class);

	public Response userDetails(Roles role) {
		lOGGER.info("Making the request to the endpoint {} with role {} ", USERDETAILS_ENDPOINT, role);
		Response response = given()
		.spec(SpecUtil.requestSpecificationWithAuth(Roles.FD))
		.when().get(USERDETAILS_ENDPOINT);
		return response;
	}
}
