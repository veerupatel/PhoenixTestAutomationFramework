package com.api.tests.datadriven;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;
import com.request.models.UserCredentials;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIJsonDataDrivenTest {
	
	
	@Test(description = "Verifying if login api is working for FD user"
			,groups= {"api","regression","smoke","datadriven"}
	,dataProviderClass = com.dataproviders.DataProviderUtils.class,dataProvider = "LoginAPIJsonDataProvider")
	public void loginAPITest(UserCredentials userCredentials) {
		
		RestAssured.given()
		.spec(SpecUtil.requestSpec(userCredentials))
		.when()
		.post("login")
		.then()
		.spec(SpecUtil.resposeSpec_OK())
		.body("message", Matchers.equalTo("Success"))
		.body("data.token", Matchers.notNullValue())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-Schema/LoginAPIResponseSchema.json"))
		.extract().response();
	}

}
