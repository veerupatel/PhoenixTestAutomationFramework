package com.api.services;

import com.api.constants.Roles;
import com.api.request.models.CreateJobPayload;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT = "/job/create";

	private static final String SEARCH_ENDPOINT = "/job/search";

	private static final Logger lOGGER = LogManager.getLogger(JobService.class);

	public Response createJob(Roles role, CreateJobPayload createJobPayload) {
		lOGGER.info("Making the request to {} with the role {} and for the payload {} ", CREATE_JOB_ENDPOINT, role,
				createJobPayload);
		Response response = given().spec(SpecUtil.requestSpecificationWithAuth(role)).body(createJobPayload).when()
				.post(CREATE_JOB_ENDPOINT);
		return response;
	}

	public Response search(Roles role, Object payload) {
		lOGGER.info("Making the request to {} with the role {} and for the payload {} ", SEARCH_ENDPOINT, role,
				payload);
		return given().spec(SpecUtil.requestSpecificationWithAuth(role)).body(payload).post(SEARCH_ENDPOINT);
	}
}
