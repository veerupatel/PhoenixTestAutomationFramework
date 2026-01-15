package com.api.tests;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.request.models.CreateJobPayload;
import com.api.request.models.Customer;
import com.api.request.models.CustomerAddress;
import com.api.request.models.CustomerProduct;
import com.api.request.models.Problems;
import com.api.response.model.CreateJobResponseModel;
import com.api.services.JobService;
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

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)
@Epic("Job Management")
@Feature("Create Job API")
public class CreateJobAPITest {
	Customer customer;
	private CreateJobPayload createJobPayload;
	CustomerProduct customerProduct;
	CustomerAddress customerAddress;
	Problems problems;

	private JobService jobService;

	@BeforeMethod(description = "Setting up the createJob instance")
	public void setup() {
		jobService = new JobService();
		customer = new Customer("RajKumar", "Yadav", "9876543212", "", "rajkumar@gmail.com", "");
		customerProduct = new CustomerProduct("2025-11-02T18:30:00.000Z", "760432345667985", "760432345667985",
				"760432345667985", "2025-11-02T18:30:00.000Z", 1, 1);
		customerAddress = new CustomerAddress("51", "Sai Apartment", "Noida Sector 71",
				"near Noida sector 52 metro Station", "Gautam Buddha Nagar", "201301", "india", "Uttar Pradesh");

		problems = new Problems(1, "Battery Issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);

	}

	@Story("FD should be able to create job")
	@Description("Verifying if FD is able to use create job api and Inwarranty job is created successfully")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Verifying if FD is able to use create job api and Inwarranty job is created successfully",groups = { "api", "regression",
			"smoke" })
	public void createJobAPITest() {

		CreateJobResponseModel createJobResponseModel = jobService.createJob(Roles.FD, createJobPayload).then()
				.spec(SpecUtil.resposeSpec_OK())
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
