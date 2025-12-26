package com.api.services;

import com.api.constants.Roles;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DashboardService {
	
	private static final String COUNT_ENDPOINT = "/dashboard/count";
	private static final String DETAIL_ENDPOINT = "/dashboard/details";
	
	public Response count(Roles role) {
		 Response response = given()
		.spec(SpecUtil.requestSpecificationWithAuth(role))
			.when()
			.get(COUNT_ENDPOINT);
		return response;
	}

	public Response countWithNoAuth() {
		 Response response = given().spec(SpecUtil.requestSpec())
					.when()
			.get(COUNT_ENDPOINT);
		return response;
	}
	public Response details(Roles role, Object payload) {
	return	given().spec(SpecUtil.requestSpecificationWithAuth(role))
		.body(payload).when().post(DETAIL_ENDPOINT);
		
	}
}
