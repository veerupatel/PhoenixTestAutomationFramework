package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.request.models.UserCredentials;
import com.api.services.AuthService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(com.listeners.APITestListener.class)
@Epic("User Management")
@Feature("Authentication")


public class LoginAPITest {
	
	private UserCredentials userCredentials;
	private AuthService authService;
	
	@BeforeMethod(description = "Create the payload for the Login API")
	public void setup() {
		userCredentials = new UserCredentials("iamfd", "password");
		authService = new AuthService();
	}

	
	
	
	@Story("Valid User should be able to login into the application")
	@Description("Verifying if login api is working for FD user")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Verifying if login api is working for FD user",groups = {"api","regression","smoke"})
	public void loginAPITest() {
		authService.login(userCredentials)
		.then().spec(SpecUtil.resposeSpec_OK())
		.body("message", Matchers.equalTo("Success"));
		//.and()
		//.body(Matchers.matchesJsonSchemaInClasspath("response-Schema/LoginAPIResponseSchema.json"));
	}
}
