package com.api.utils;

import com.api.constants.Roles;
import com.request.models.UserCredentials;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AuthTokenProvider {

	private AuthTokenProvider() {

	}

	public static String getToken(Roles roles) {
		UserCredentials userCredentials = null;
		if (roles == Roles.FD) {
			userCredentials = new UserCredentials("iamfd", "password");
		} else if (roles == Roles.SUP) {
			userCredentials = new UserCredentials("iamsup", "password");
		} else if (roles == Roles.QC) {
			userCredentials = new UserCredentials("iamqc", "password");
		}
		else if (roles == Roles.ENG) {
			userCredentials = new UserCredentials("iameng", "password");
		}

		String token = RestAssured.given().baseUri(ConfigManager2.getProperty("BASE_URL")).contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(userCredentials).when().post("login").then().log().ifValidationFails()
				.statusCode(200).extract().jsonPath().getString("data.token");
		System.out.println(token);

		return token;
	}

}
