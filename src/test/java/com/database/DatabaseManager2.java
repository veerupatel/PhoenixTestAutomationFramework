package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager2;

public class DatabaseManager2 {
	
	private static final String  DB_URL = ConfigManager2.getProperty("");
	private static final String  DB_USER_NAME = ConfigManager2.getProperty("");
	private static final String  DB__PASSWORD = ConfigManager2.getProperty("");
	
	private DatabaseManager2() {
		
	}

	
	public static void createConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB__PASSWORD);
		System.out.println(conn);
	}
}
