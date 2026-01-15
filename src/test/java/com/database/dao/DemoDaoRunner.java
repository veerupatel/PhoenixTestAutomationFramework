package com.database.dao;

import java.sql.SQLException;

import org.testng.Assert;

import com.api.request.models.Customer;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerJobHeadModel;
import com.database.model.CustomerMapJobProblemModel;
import com.database.model.CustomerProductDBModel;

public class DemoDaoRunner {

	public static void main(String[] args) throws SQLException {
		CustomerDBModel customerModel = CustomerDao.getCustomerInfo(120936);
		System.out.println(customerModel);
		CustomerAddressDBModel customerAddressModel = CustomerAddressDao.getCustomerAddressData(120936);
		System.out.println(customerAddressModel);
		CustomerProductDBModel customerProductModel = CustomerProductDao.getCustomerProductInfo(120936);
		System.out.println(customerProductModel);
		CustomerJobHeadModel customerJobHeadModel = CustomerJobheadDao.getCustomerJobHeadInfo(120934);
		System.out.println(customerJobHeadModel);
		CustomerMapJobProblemModel customerMapJobProblemModel = CustomerMapJobProblemDao
				.getCustomerMapJobProblemInfo(30609);
		System.out.println(customerMapJobProblemModel);
	}

}
