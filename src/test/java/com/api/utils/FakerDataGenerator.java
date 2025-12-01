package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;
import com.request.models.CreateJobPayload;
import com.request.models.Customer;
import com.request.models.CustomerAddress;
import com.request.models.CustomerProduct;
import com.request.models.Problems;

public class FakerDataGenerator {

	private static Faker faker = new Faker(new Locale("en-IND"));
	private final static String COUNTRY = "India";
	private final static Random RANDOM = new Random();
	private final static int MST_SERVICE_LOCATION_ID = 0;
	private final static int MST_PLATFORM_ID = 2;
	private final static int MST_WARRANTY_STATUS_ID = 1;
	private final static int MST_OEM_ID = 1;
	private final static int PRODUCT_ID = 1;
	private final static int MST_MODEL_ID = 1;

	private FakerDataGenerator() {

	}

	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		for (int i = 1; i <= count; i++) {

			Customer customer = generateFakeCustomerData();
			CustomerAddress customerAddress = generateFakeCustomerAddressData();
			CustomerProduct customerProduct = generateFakerCustomerProductData();
			List<Problems> problems = generateFakeProblemsList();
			CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
					MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problems);
			payloadList.add(payload);
		}
		return payloadList.iterator();
	}

	public static Customer generateFakeCustomerData() {
		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String mobileNumber = faker.numerify("70#######");
		String alternateMobileNumber = faker.numerify("70#######");
		String customEmailAddress = faker.internet().emailAddress();
		String altCustomerEmailAddress = faker.internet().emailAddress();
		Customer customer = new Customer(fname, lname, mobileNumber, alternateMobileNumber, customEmailAddress,
				altCustomerEmailAddress);
		return customer;
	}

	public static CustomerAddress generateFakeCustomerAddressData() {
		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pinCode = faker.numerify("#####");
		String state = faker.address().state();

		CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area,
				pinCode, state, COUNTRY);
		return customerAddress;
	}

	public static CustomerProduct generateFakerCustomerProductData() {
		String dop = DateTimeUtil.getTimewithDaysAgo(10);
		String imeiSerialNumber = faker.numerify("###############");
		String popURL = faker.internet().url();
		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber,
				popURL, PRODUCT_ID, MST_MODEL_ID);
		return customerProduct;
	}

	public static List<Problems> generateFakeProblemsList() {

		int problemId = RANDOM.nextInt(25) + 1;
		String fakeRemark = faker.lorem().sentence(5);
		Problems problems = new Problems(problemId, fakeRemark);
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);

		return problemList;
	}
}
