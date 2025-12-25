package com.api.services;

import com.api.constants.Roles;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class MasterService {
	
	private static final String MASTER_ENDPOINT = "/master";
	
	public Response master(Roles role) {
		Response response = given()
		.spec(SpecUtil.requestSpecificationWithAuth(role))
		.when().post(MASTER_ENDPOINT);
		
		return response;
	}

}
