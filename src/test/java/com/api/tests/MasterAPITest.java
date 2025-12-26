package com.api.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.services.MasterService;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager2;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {

	private MasterService masterService;

	@BeforeMethod(description = "setting up the master instance")
	public void setup() {
		masterService = new MasterService();
	}

	@Test(description = "Verifying if master api is giving correct resonse")
	public void verifyMasterAPITest() {
		masterService.master(Roles.FD).then().spec(SpecUtil.resposeSpec_OK())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-Schema/MasterAPIResponseSchema.json"))
				.extract().response();
	}

}
