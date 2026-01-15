package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.request.models.Detail;
import com.api.services.DashboardService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Listeners(com.listeners.APITestListener.class)

@Epic("Job Management")
@Feature("Job Details")
public class DetailsAPITest {

	private DashboardService dashboardService;
	private Detail detailPayload;

	@BeforeMethod(description = "Instantiating the Dashboard service and creating detail payload")
	public void setup() {
		dashboardService = new DashboardService();
		detailPayload = new Detail("created_today");
	}

	@Story("Job Details should be shown correctly for FD role")
	@Description("Verify if Details API is working correctly")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verifying if Details API is working correctly for FD role", groups = { "api",
			"regression", "smoke" })
	public void detailAPITest() {
		dashboardService.details(Roles.FD, detailPayload).then().spec(SpecUtil.resposeSpec_OK()).body("message",
				Matchers.equalTo("Success"));
	}
}
