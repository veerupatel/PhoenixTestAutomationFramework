package com.database.dao;

import java.sql.SQLException;

import org.testng.Assert;

import com.database.model.CustomerDBModel;
import com.request.models.Customer;

public class DemoDaoRunner {

	public static void main(String[] args) throws SQLException {
		CustomerDBModel customerDb = CustomerDao.getCustomerInfo();
		System.out.println(customerDb);
		System.out.println(customerDb.getFirst_name());
		System.out.println(customerDb.getLast_name());
		System.out.println(customerDb.getEmail_id());
		Customer customer = new Customer("Margaretta", "Yadav", "9876543212", "", "rajkumar@gmail.com", "");
		System.out.println(customer.first_name());
		Assert.assertEquals(customerDb.getFirst_name(), customer.first_name());
	}

}
