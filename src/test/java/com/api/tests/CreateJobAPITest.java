package com.api.tests;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.response.model.CreateJobResponseModel;
import com.api.utils.SpecUtil;
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.CustomerJobheadDao;
import com.database.dao.CustomerMapJobProblemDao;
import com.database.dao.CustomerProductDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerJobHeadModel;
import com.database.model.CustomerMapJobProblemModel;
import com.database.model.CustomerProductDBModel;
import com.request.models.CreateJobPayload;
import com.request.models.Customer;
import com.request.models.CustomerAddress;
import com.request.models.CustomerProduct;
import com.request.models.Problems;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	Customer customer;
	CreateJobPayload createJobPayload;
	CustomerProduct customerProduct;
	CustomerAddress customerAddress;

	@Test
	public void createJobAPITest() {
		customer = new Customer("RajKumar", "Yadav", "9876543212", "", "rajkumar@gmail.com", "");
		customerProduct = new CustomerProduct("2025-11-02T18:30:00.000Z", "765432345667985", "765432345667985",
				"765432345667985", "2025-11-02T18:30:00.000Z", 1, 1);
		customerAddress = new CustomerAddress("51", "Sai Apartment", "Noida Sector 71", "near Noida sector 52 metro Station",
				"Gautam Buddha Nagar", "201301", "india", "Uttar Pradesh");

		Problems problems = new Problems(1, "Battery Issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);

		CreateJobResponseModel createJobResponseModel = RestAssured.given()
				.spec(SpecUtil.requestSpecificationWithAuth(Roles.FD)).body(createJobPayload).when().post("/job/create")
				.then().spec(SpecUtil.resposeSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-Schema/CreateJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.job_number", Matchers.startsWith("JOB_"))
				.body("data.mst_service_location_id", Matchers.equalTo(1)).body("data.id", Matchers.notNullValue())
				.extract().as(CreateJobResponseModel.class);
		System.out.println(createJobResponseModel);

		int customerId = createJobResponseModel.getData().getTr_customer_id();
		CustomerDBModel customerDataFromDB = CustomerDao.getCustomerInfo(customerId);
		Assert.assertEquals(customer.first_name(), customerDataFromDB.getFirst_name());
		Assert.assertEquals(customer.last_name(), customerDataFromDB.getLast_name());
		Assert.assertEquals(customer.email_id(), customerDataFromDB.getEmail_id());
		Assert.assertEquals(customer.email_id_alt(), customerDataFromDB.getEmail_id_alt());
		Assert.assertEquals(customer.mobile_number(), customerDataFromDB.getMobile_number());
		Assert.assertEquals(customer.mobile_number_alt(), customerDataFromDB.getMobile_number_alt());

		CustomerAddressDBModel customerAddressDataFromDB = CustomerAddressDao.getCustomerAddressData(customerId);
		Assert.assertEquals(customerAddress.apartment_name(), customerAddressDataFromDB.getApartment_name());
		Assert.assertEquals(customerAddress.flat_number(), customerAddressDataFromDB.getFlat_number());
		Assert.assertEquals(customerAddress.street_name(), customerAddressDataFromDB.getStreet_name());
		Assert.assertEquals(customerAddress.landmark(), customerAddressDataFromDB.getLandmark());
		Assert.assertEquals(customerAddress.area(), customerAddressDataFromDB.getArea());
		Assert.assertEquals(customerAddress.pincode(), customerAddressDataFromDB.getPincode());
		Assert.assertEquals(customerAddress.state(), customerAddressDataFromDB.getState());

		CustomerProductDBModel customerProductDataFromDB = CustomerProductDao.getCustomerProductInfo(customerId);
		Assert.assertEquals(customerProduct.mst_model_id(), customerProductDataFromDB.getMst_model_id());
		// Assert.assertEquals(customerProduct.product_id(),
		// customerProductDataFromDB.getMst_model_id());
		Assert.assertEquals(customerProduct.popurl(), customerProductDataFromDB.getPopurl());
		Assert.assertEquals(customerProduct.imei1(), customerProductDataFromDB.getImei1());
		Assert.assertEquals(customerProduct.imei2(), customerProductDataFromDB.getImei2());
		Assert.assertEquals(customerProduct.serial_number(), customerProductDataFromDB.getSerial_number());
		Assert.assertEquals(customerProduct.dop(), customerProductDataFromDB.getDop());

		CustomerMapJobProblemModel customerMapJobDataFromDB = CustomerMapJobProblemDao
				.getCustomerMapJobProblemInfo(customerId);
		Assert.assertEquals(problems.remark(), customerMapJobDataFromDB.getRemark());
		Assert.assertEquals(problems.id(), customerMapJobDataFromDB.getId());
	}

}
