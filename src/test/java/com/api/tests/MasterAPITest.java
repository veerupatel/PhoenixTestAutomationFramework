package com.api.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.services.MasterService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)
@Epic("Job Management")
@Feature("Master API")
public class MasterAPITest {

	private MasterService masterService;

	@BeforeMethod(description = "setting up the master instance")
	public void setup() {
		masterService = new MasterService();
	}

	@Story("Master API should bring OEM details, Problem Type, Warranty Status")
	@Description("Verifying if master api is giving correct resonse")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Verifying if master api is giving correct resonse", groups = { "api", "regression", "smoke" })
	public void verifyMasterAPITest() {
		masterService.master(Roles.FD).then().spec(SpecUtil.resposeSpec_OK())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-Schema/MasterAPIResponseSchema.json"))
				.extract().response();
	}

}
