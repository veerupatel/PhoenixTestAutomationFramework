package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.services.DashboardService;
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
@Feature("Job Count")
public class CountAPITest {

	private DashboardService dashboardService;

	@BeforeMethod(description = "setting up the count api instance")
	public void setup() {
		dashboardService = new DashboardService();
	}

	@Story("Job Count data should shown correctly")
	@Description("Verify if count API is giving response correctly")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verifying if Count API is working correctly for FD role", groups = { "api",
			"regression", "smoke" })
	public void verifyCountAPITest() {
		dashboardService.count(Roles.FD).then().spec(SpecUtil.resposeSpec_OK())
				.body("message", Matchers.equalTo("Success")).time(Matchers.lessThan(1000L))
				.body("data", Matchers.notNullValue()).body("data.size()", Matchers.equalTo(3))
				.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
				.body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-Schema/CountAPIResponseSchema.json"))
				.body("data.key",
						Matchers.containsInAnyOrder("pending_for_delivery", "pending_fst_assignment", "created_today"));
	}

	@Test
	public void countAPITest_MissingAuthToken() {
		dashboardService.countWithNoAuth().then().spec(SpecUtil.resposeSpec_TEXT(401));
	}

}
