package com.api.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.services.UserService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)
@Epic("User Management")
@Feature("User Details")
public class UserDetailsAPITest {

	private UserService userService;

	@BeforeMethod(description = "Setting up the UserService instance")
	public void setup() {
		userService = new UserService();
	}

	@Story("User Details should be shown")
	@Description("Verifying if User Details API response is shown correctly")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify if the Userdetails API response is shwon correctly", groups = { "api", "regression","smoke" })
	public void UserDetailsAPITest() {
		userService.userDetails(Roles.FD).then().spec(SpecUtil.resposeSpec_OK()).and().body(
				JsonSchemaValidator.matchesJsonSchemaInClasspath("response-Schema/UserDetailsResponseSchema.json"));

	}

}
