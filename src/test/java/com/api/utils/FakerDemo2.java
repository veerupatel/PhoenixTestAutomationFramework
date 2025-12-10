package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;
import com.request.models.CreateJobPayload;
import com.request.models.Customer;
import com.request.models.CustomerAddress;
import com.request.models.CustomerProduct;
import com.request.models.Problems;

public class FakerDemo2 {

	private final static String countryName = "India";

	public static void main(String[] args) {
		Faker faker = new Faker(new Locale("en-IND"));
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String mobileNumber = faker.numerify("704#######");
		String alternateNumber = faker.numerify("704#######");
		String email_Address = faker.internet().emailAddress();
		String alternate_Email_Address = faker.internet().emailAddress();

		Customer customer = new Customer(firstName, lastName, mobileNumber, alternateNumber, email_Address,
				alternate_Email_Address);
		System.out.println(customer);

		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("#####");
		String state = faker.address().state();
		CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area,
				pincode, countryName, state);

		System.out.println(customerAddress);

		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String imeiSerialNumber = faker.numerify("###############");
		String popurl = faker.internet().url();

		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber,
				popurl, 1, 1);
		System.out.println(customerProduct);

		String remark = faker.lorem().sentence(10);
		Random random = new Random();
		int id = random.nextInt(26) + 1;
		Problems problems = new Problems(id, remark);
		System.out.println(problems);

		List<Problems> ProblemList = new ArrayList<Problems>();
		ProblemList.add(problems);

		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct,
				ProblemList);
		System.out.println(createJobPayload);
	}

}
