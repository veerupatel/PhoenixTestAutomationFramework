package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager2;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {
	
	@Test
	public void verifyCountAPITest() {
		RestAssured.given()
	.spec(SpecUtil.requestSpecificationWithAuth(Roles.FD))
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtil.resposeSpec_OK())
		.body("message", Matchers.equalTo("Success"))
		.time(Matchers.lessThan(1000L))
		.body("data", Matchers.notNullValue())
		.body("data.size()", Matchers.equalTo(3))
        .body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
        .body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-Schema/CountAPIResponseSchema.json"))
        .body("data.key", Matchers.containsInAnyOrder("pending_for_delivery","pending_fst_assignment","created_today"));
	}
	
	@Test
	public void countAPITest_MissingAuthToken() {
		RestAssured.given()
		.spec(SpecUtil.requestSpec())
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtil.resposeSpec_TEXT(401));
	}

}
