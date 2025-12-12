package com.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.api.utils.ConfigManager2;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager2 {

	private static final String DB_URL = ConfigManager2.getProperty("DB_URL");
	private static final String DB_USER_NAME = ConfigManager2.getProperty("DB_USER_NAME");
	private static final String DB_PASSWORD = ConfigManager2.getProperty("DB_PASSWORD");
	private static final int MAXIMUM_POOL_SIZE = Integer.parseInt(ConfigManager2.getProperty("MAXIMUM_POOL_SIZE"));
	private static final int MINIMUM_IDLE_COUNT = Integer.parseInt(ConfigManager2.getProperty("MINIMUM_IDLE_COUNT"));
	private static final int CONNECTION_TIME_OUT_IN_SECS = Integer
			.parseInt(ConfigManager2.getProperty("CONNECTION_TIME_OUT_IN_SECS"));
	private static final int IDLE_TIMEOUT_SECS = Integer.parseInt(ConfigManager2.getProperty("IDLE_TIMEOUT_SECS"));
	private static final int MAX_LIFE_TIME_IN_MINS = Integer
			.parseInt(ConfigManager2.getProperty("MAX_LIFE_TIME_IN_MINS"));
	private static final String HIKARI_CP_POOL_NAME = ConfigManager2.getProperty("HIKARI_CP_POOL_NAME");
	private static HikariConfig hikariConfig;
	private static volatile HikariDataSource hikariDataSource;

	private DatabaseManager2() {

	}

	private static void instantiatePool() throws SQLException {
		if (hikariDataSource == null) {
			synchronized (DatabaseManager2.class) {
				if (hikariDataSource == null) {
					hikariConfig = new HikariConfig();
					hikariConfig.setJdbcUrl(DB_URL);
					hikariConfig.setUsername(DB_USER_NAME);
					hikariConfig.setPassword(DB_PASSWORD);
					hikariConfig.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
					hikariConfig.setMinimumIdle(MINIMUM_IDLE_COUNT);
					hikariConfig.setConnectionTimeout(CONNECTION_TIME_OUT_IN_SECS * 1000);
					hikariConfig.setIdleTimeout(IDLE_TIMEOUT_SECS);
					hikariConfig.setMaxLifetime(MAX_LIFE_TIME_IN_MINS);
					hikariConfig.setPoolName(HIKARI_CP_POOL_NAME);
					hikariDataSource = new HikariDataSource(hikariConfig);
				}

			}
		}

	}

	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		if (hikariDataSource == null) {
			instantiatePool();
		} else if (hikariDataSource.isClosed()) {
			throw new SQLException("HIKARI DATA SOURCE IS CLOSED");
		}

		connection = hikariDataSource.getConnection();

		return connection;
	}
}
