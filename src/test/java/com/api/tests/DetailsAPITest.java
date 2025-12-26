package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.services.DashboardService;
import com.api.utils.SpecUtil;
import com.request.models.Detail;

public class DetailsAPITest {

	private DashboardService dashboardService;
	private Detail detailPayload;

	@BeforeMethod(description = "Instantiating the Dashboard service and creating detail payload")
	public void setup() {
		dashboardService = new DashboardService();
		detailPayload = new Detail("created_today");
	}

	@Test
	public void detailAPITest() {
		dashboardService.details(Roles.FD, detailPayload).then().spec(SpecUtil.resposeSpec_OK()).body("message",
				Matchers.equalTo("Success"));
	}
}
