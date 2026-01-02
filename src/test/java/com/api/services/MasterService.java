package com.api.services;

import com.api.constants.Roles;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MasterService {
	
	private static final String MASTER_ENDPOINT = "/master";
	
	private static final Logger lOGGER = LogManager.getLogger(MasterService.class);
	
	public Response master(Roles role) {
		lOGGER.info("Making the request to the endpoint {} with role {} ", MASTER_ENDPOINT, role);
		Response response = given()
		.spec(SpecUtil.requestSpecificationWithAuth(role))
		.when().post(MASTER_ENDPOINT);
		
		return response;
	}

}
