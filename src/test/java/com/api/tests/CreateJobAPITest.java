package com.api.tests;

import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager2;
import com.api.utils.SpecUtil;
import com.request.models.CreateJobPayload;
import com.request.models.Customer;
import com.request.models.CustomerAddress;
import com.request.models.CustomerProduct;
import com.request.models.Problems;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest() {
		Customer customer = new Customer("RajKumar", "Yadav", "9876543212", "", "rajkumar@gmail.com", "");
		CustomerProduct customerProduct = new CustomerProduct("2025-11-02T18:30:00.000Z", "765432345687981", "765432345687981", "765432345687981", "2025-11-02T18:30:00.000Z", 1, 1);
		CustomerAddress customerAddress = new CustomerAddress("51", "Sai Apartment", "Noida Sector 71", "near Noida sector 52 metro Station", "Gautam Buddha Nagar", "201301","india","Uttar Pradesh");
		
		Problems problems = new Problems(1, "Battery Issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0]=problems;
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		RestAssured.given()
		.spec(SpecUtil.requestSpecificationWithAuth(Roles.FD, createJobPayload))
		.body(createJobPayload).when().post("/job/create")
		.then()
		.spec(SpecUtil.resposeSpec_OK());
	}

}
