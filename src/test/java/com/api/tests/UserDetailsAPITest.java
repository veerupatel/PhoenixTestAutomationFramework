package com.api.tests;

import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class UserDetailsAPITest {

	@Test
	public void UserDetailsAPITest() {
		Header header = new Header("Authorization", AuthTokenProvider.getToken(Roles.FD));
		Response response = RestAssured.given().baseUri(ConfigManager2.getProperty("BASE_URL"))
				.contentType(ContentType.JSON).accept(ContentType.JSON).header(header).when().get("userdetails").then()
				.statusCode(200).extract().response();
		System.out.println(response.asPrettyString());
	}

}
