package com.api.tests.datadriven;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.services.JobService;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.CreateJobBean;
import com.request.models.CreateJobPayload;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIJsonDataDrivenTest {
	private JobService jobService;

	@BeforeMethod(description = "Instantiating CreateJob Service")
	public void setup() {
		jobService = new JobService();
	}

	@Test(dataProviderClass = com.dataproviders.DataProviderUtils.class,dataProvider = "CreateJobAPIJsonDataProvider")
	public void createJobAPITest(CreateJobPayload createJobPayload ) {

		jobService.createJob(Roles.FD,createJobPayload).then().spec(SpecUtil.resposeSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-Schema/CreateJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.job_number", Matchers.startsWith("JOB_"))
				.body("data.mst_service_location_id", Matchers.equalTo(1)).body("data.id", Matchers.notNullValue());
	}

}
