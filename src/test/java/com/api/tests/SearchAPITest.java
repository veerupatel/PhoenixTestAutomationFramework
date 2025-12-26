package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.services.JobService;
import com.api.utils.SpecUtil;
import com.request.models.Search;

public class SearchAPITest {

	private JobService jobService;
	private static final String JOB_NUMBER = "JOB_131343";
	private Search searchPayload;

	@BeforeMethod
	public void setup() {
		jobService = new JobService();
		searchPayload = new Search(JOB_NUMBER);
	}
	
	@Test(description = "Verify if the search api is working properly ")
	public void SearchAPITest() {
		jobService.search(Roles.FD, searchPayload)
		.then().spec(SpecUtil.resposeSpec_OK())
		.body("message", Matchers.equalTo("Success"));
	}
}
