package com.api.tests;

import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager2;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class MasterAPITest {

	@Test
	public void verifyMasterAPITest() {
		RestAssured.given()
		.spec(SpecUtil.requestSpecificationWithAuth(Roles.FD))
		.when().post("master").then()
		.spec(SpecUtil.resposeSpec_OK())
		.extract().response();
	}

}
