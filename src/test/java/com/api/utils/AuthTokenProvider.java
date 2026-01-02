package com.api.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Roles;
import com.request.models.UserCredentials;

import io.restassured.RestAssured;

public class AuthTokenProvider {

	private static Map<Roles, String> tokenCache = new ConcurrentHashMap<Roles, String>();

	private static final Logger logger = LogManager.getLogger(AuthTokenProvider.class);

	private AuthTokenProvider() {

	}

	public static String getToken(Roles roles) {

		logger.info("Checking if the token for {} is present in the cache ", roles);
		if (tokenCache.containsKey(roles)) {
			logger.info("token found for {} ", roles);
			return tokenCache.get(roles);
		}
		logger.info("token not found making the request for the role {} ", roles);
		UserCredentials userCredentials = null;
		if (roles == Roles.FD) {
			userCredentials = new UserCredentials("iamfd", "password");
		} else if (roles == Roles.SUP) {
			userCredentials = new UserCredentials("iamsup", "password");
		} else if (roles == Roles.QC) {
			userCredentials = new UserCredentials("iamqc", "password");
		} else if (roles == Roles.ENG) {
			userCredentials = new UserCredentials("iameng", "password");
		}

		String token = RestAssured.given().spec(SpecUtil.requestSpec()).body(userCredentials).when().post("login")
				.then().log().ifValidationFails().statusCode(200).extract().jsonPath().getString("data.token");
		System.out.println(token);
		logger.info("Token cached for future request");
		tokenCache.put(roles, token);
		return token;
	}

}
