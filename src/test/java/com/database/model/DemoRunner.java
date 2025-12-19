package com.database.model;

public class DemoRunner {

	public static void main(String[] args) {
		CustomerDBModel customer = new CustomerDBModel(123,"Biraju", "Patel", "8448752554", "9599153229",
				"biraju@fonada.com", "birajup01@gmail.com",3343);
		System.out.println(customer);
	}

}
