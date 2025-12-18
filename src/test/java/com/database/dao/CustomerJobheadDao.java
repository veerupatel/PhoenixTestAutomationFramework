package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DatabaseManager;
import com.database.model.CustomerJobHeadModel;

public class CustomerJobheadDao {

	private CustomerJobheadDao() {

	}

	private static final String CUSTOMER_JOB_HEAD_QUERY = """
						SELECT 
			
			id,
			job_number,
			tr_customer_id,
			tr_customer_product_id,
			mst_service_location_id,
			mst_platform_id,
			mst_warrenty_status_id,
			mst_oem_id,
			repair_start_date,
			repair_end_date
			from tr_job_head where tr_customer_id = ?
						""";

	public static CustomerJobHeadModel getCustomerJobHeadInfo(int tr_customer_id) {
		Connection conn;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		CustomerJobHeadModel customerJobHeadModel = null;
		try {
			conn = DatabaseManager.getConnection();
			preparedStatement = conn.prepareStatement(CUSTOMER_JOB_HEAD_QUERY);
			preparedStatement.setInt(1, tr_customer_id);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				customerJobHeadModel = new CustomerJobHeadModel(resultSet.getInt("id"), resultSet.getString("job_number"),
						resultSet.getInt("tr_customer_id"), resultSet.getInt("tr_customer_product_id"), resultSet.getInt("mst_service_location_id"), resultSet.getInt("mst_platform_id"),
						resultSet.getInt("mst_warrenty_status_id"), resultSet.getInt("mst_oem_id"), resultSet.getString("repair_start_date"), resultSet.getString("repair_end_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return customerJobHeadModel;

	}
}
