package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DatabaseManager;
import com.database.model.CustomerMapJobProblemModel;

public class CustomerMapJobProblemDao {

	private CustomerMapJobProblemDao() {

	}

	private static final String CUSTOMER_MAP_JOB_PROBLEM_QUERY = """
						select
			id,
			tr_job_head_id,
			mst_problem_id,
			remark
			from map_job_problem where tr_job_head_id = ?
						""";

	public static CustomerMapJobProblemModel getCustomerMapJobProblemInfo(int tr_job_head_id) {
		Connection conn;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		CustomerMapJobProblemModel customerMapJobProblemModel = null;
		try {
			conn = DatabaseManager.getConnection();
			preparedStatement = conn.prepareStatement(CUSTOMER_MAP_JOB_PROBLEM_QUERY);
			preparedStatement.setInt(1, tr_job_head_id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customerMapJobProblemModel = new CustomerMapJobProblemModel(resultSet.getInt("id"),
						resultSet.getInt("tr_job_head_id"), resultSet.getInt("mst_problem_id"),
						resultSet.getString("remark"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerMapJobProblemModel;
	}
}
