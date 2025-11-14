package com.api.tests;

import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class MasterAPITest {

	@Test
	public void verifyMasterAPITest() {
		RestAssured.given()
		.baseUri(ConfigManager2.getProperty("BASE_URL"))
		.contentType("")
		//.accept(ContentType.JSON)
		.header("Authorization", AuthTokenProvider.getToken(Roles.FD))
		.when().post("master").then().log().all().statusCode(200).extract().response();
	}

}
