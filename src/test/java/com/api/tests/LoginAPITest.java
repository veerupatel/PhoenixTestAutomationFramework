package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.services.AuthService;
import com.api.utils.SpecUtil;

public class LoginAPITest {
	
	private UserCredentials userCredentials;
	private AuthService authService;
	
	@BeforeMethod(description = "Create the payload for the Login API")
	public void setup() {
		userCredentials = new UserCredentials("iamfd", "password");
		authService = new AuthService();
	}

	@Test(description = "Verifying if login api is working for FD user")
	public void loginAPITest() {
		authService.login(userCredentials)
		.then().spec(SpecUtil.resposeSpec_OK())
		.body("message", Matchers.equalTo("Success"));
		//.and()
		//.body(Matchers.matchesJsonSchemaInClasspath("response-Schema/LoginAPIResponseSchema.json"));
	}
}
