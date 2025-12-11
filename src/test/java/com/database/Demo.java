package com.database;

import java.sql.SQLException;

public class Demo {

	public static void main(String[] args) throws SQLException {
		DatabaseManager.createConnection();
		DatabaseManager.createConnection();
		DatabaseManager.createConnection();
		DatabaseManager.createConnection();

	}

}
