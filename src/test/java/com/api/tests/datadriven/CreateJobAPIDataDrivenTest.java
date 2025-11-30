package com.api.tests.datadriven;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.CreateJobBean;
import com.request.models.CreateJobPayload;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIDataDrivenTest {

	@Test(dataProviderClass = com.dataproviders.DataProviderUtils.class,dataProvider = "CreateJobAPIDataProvider")
	public void createJobAPITest(CreateJobPayload createJobPayload ) {

		RestAssured.given().spec(SpecUtil.requestSpecificationWithAuth(Roles.FD)).body(createJobPayload).when()
				.post("/job/create").then().spec(SpecUtil.resposeSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-Schema/CreateJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.job_number", Matchers.startsWith("JOB_"))
				.body("data.mst_service_location_id", Matchers.equalTo(1)).body("data.id", Matchers.notNullValue());
	}

}
