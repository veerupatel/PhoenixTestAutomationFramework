package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager2;

public class DatabaseManager {

	private static final String DB_URL = ConfigManager2.getProperty("DB_URL");
	private static final String DB_USER_NAME = ConfigManager2.getProperty("DB_USER_NAME");
	private static final String DB_PASSWORD = ConfigManager2.getProperty("DB_PASSWORD");
	private volatile static Connection conn;

	private DatabaseManager() {

	}

	public static void createConnection() throws SQLException {
		if (conn == null) {
			synchronized (DriverManager.class) {

				conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);

			}

			System.out.println(conn);
		}

	}
}
