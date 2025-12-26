package com.api.tests.datadriven;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.api.utils.ConfigManager;
import com.api.utils.ConfigManager2;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;
import com.request.models.UserCredentials;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIDataDrivenTest {

	private AuthService authService;

	@BeforeMethod(description = "Initializing the Auth Service")
	public void setup() {
		authService = new AuthService();
	}

	@Test(description = "Verifying if login api is working for FD user", groups = { "api", "regression", "smoke",
			"datadriven" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "LoginAPIDataProvider")
	public void loginAPITest(UserBean userbean) {
		authService.login(userbean).then().spec(SpecUtil.resposeSpec_OK()).body("message", Matchers.equalTo("Success"))
				.body("data.token", Matchers.notNullValue())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-Schema/LoginAPIResponseSchema.json"))
				.extract().response();
	}

}
