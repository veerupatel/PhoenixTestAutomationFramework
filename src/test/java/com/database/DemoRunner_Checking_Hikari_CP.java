package com.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DemoRunner_Checking_Hikari_CP {

	public static void main(String[] args) throws SQLException {
		Connection conn = DatabaseManager2.getConnection();
		System.out.println(conn);
	}

}
