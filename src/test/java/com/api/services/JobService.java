package com.api.services;

import com.api.constants.Roles;
import com.api.utils.SpecUtil;
import com.request.models.CreateJobPayload;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class JobService {
	
	private static final String CREATE_JOB_ENDPOINT = "/job/create";

	public Response createJob(Roles role, CreateJobPayload createJobPayload) {
		Response response = given()
		.spec(SpecUtil.requestSpecificationWithAuth(role)).body(createJobPayload).when().post(CREATE_JOB_ENDPOINT);
	return response;
	}
}
