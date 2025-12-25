package com.api.tests;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.services.JobService;
import com.api.utils.SpecUtil;
import com.database.dao.CustomerDao;
import com.database.model.CustomerDBModel;
import com.request.models.CreateJobPayload;
import com.request.models.Customer;
import com.request.models.CustomerAddress;
import com.request.models.CustomerProduct;
import com.request.models.Problems;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIWithDBValidationTest {
	private CreateJobPayload createJobPayload;
	private Customer customer;

	private JobService jobService;

	@BeforeMethod(description = "Setting up the createJob instance")
	public void setup() {
		jobService = new JobService();
		customer = new Customer("RajKumar", "Yadav", "9876543212", "", "rajkumar@gmail.com", "");
		CustomerProduct customerProduct = new CustomerProduct("2025-11-02T18:30:00.000Z", "705132348607985",
				"705132348607985", "705132348607985", "2025-11-02T18:30:00.000Z", 1, 1);
		CustomerAddress customerAddress = new CustomerAddress("51", "Sai Apartment", "Noida Sector 71",
				"near Noida sector 52 metro Station", "Gautam Buddha Nagar", "201301", "india", "Uttar Pradesh");

		Problems problems = new Problems(1, "Battery Issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);

	}

	@Test
	public void createJobAPITest() {

		int customerId = jobService.createJob(Roles.FD,createJobPayload).then().spec(SpecUtil.resposeSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-Schema/CreateJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1)).body("data.id", Matchers.notNullValue())
				.body("data.job_number", Matchers.startsWith("JOB_")).extract().body().jsonPath()
				.getInt("data.tr_customer_id");
		System.out.println("---------------------------");
		System.out.println(customerId);
		CustomerDBModel customerDatafromDb = CustomerDao.getCustomerInfo(customerId);
		System.out.println(customerDatafromDb);
		Assert.assertEquals(customer.first_name(), customerDatafromDb.getFirst_name());
		Assert.assertEquals(customer.last_name(), customerDatafromDb.getLast_name());
		Assert.assertEquals(customer.mobile_number(), customerDatafromDb.getMobile_number());
		Assert.assertEquals(customer.mobile_number_alt(), customerDatafromDb.getMobile_number_alt());
		Assert.assertEquals(customer.email_id(), customerDatafromDb.getEmail_id());
		Assert.assertEquals(customer.email_id_alt(), customerDatafromDb.getEmail_id_alt());
	}

}
