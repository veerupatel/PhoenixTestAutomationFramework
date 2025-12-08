package com.api.utils;

import com.api.constants.Roles;
import com.request.models.UserCredentials;

import io.restassured.RestAssured;

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

		String token = RestAssured.given()
				.spec(SpecUtil.requestSpec())
				.body(userCredentials).when().post("login").then().log().ifValidationFails()
				.statusCode(200).extract().jsonPath().getString("data.token");
		System.out.println(token);

		return token;
	}

}
