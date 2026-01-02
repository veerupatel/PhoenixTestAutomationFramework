package com.api.services;

import com.api.constants.Roles;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DashboardService {
	
	private static final String COUNT_ENDPOINT = "/dashboard/count";
	
	private static final String DETAIL_ENDPOINT = "/dashboard/details";
	
	private static final Logger lOGGER = LogManager.getLogger(DashboardService.class);
	
	public Response count(Roles role) {
		lOGGER.info("Making request to the {} for the role {}", COUNT_ENDPOINT, role);
		 Response response = given()
		.spec(SpecUtil.requestSpecificationWithAuth(role))
			.when()
			.get(COUNT_ENDPOINT);
		return response;
	}

	public Response countWithNoAuth() {
		lOGGER.info("Making request to the {} with No Auth", COUNT_ENDPOINT);
		 Response response = given().spec(SpecUtil.requestSpec())
					.when()
			.get(COUNT_ENDPOINT);
		return response;
	}
	public Response details(Roles role, Object payload) {
		lOGGER.info("Making request to the {} with role {} and the payload {}", DETAIL_ENDPOINT, role, payload);
	return	given().spec(SpecUtil.requestSpecificationWithAuth(role))
		.body(payload).when().post(DETAIL_ENDPOINT);
		
	}
}
