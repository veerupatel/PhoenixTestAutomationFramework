package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.utils.ConfigManager;
import com.api.utils.ConfigManager2;
import com.request.models.UserCredentials;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class LoginAPITest {
	
	
	@Test
	public void loginAPITest() {
		UserCredentials credentials = new UserCredentials("iamfd", "password");
		RestAssured.given().baseUri(ConfigManager2.getProperty("BASE_URL"))
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(credentials)
		.log().uri()
		.log().method()
		.log().headers()
		.log().body()
		.when()
		.post("login")
		.then()
		.log().all()
		.statusCode(200)
		.time(Matchers.lessThan(1500L))
		.body("message", Matchers.equalTo("Success"))
		.body("data.token", Matchers.notNullValue())
		.extract().response();
	}

}
