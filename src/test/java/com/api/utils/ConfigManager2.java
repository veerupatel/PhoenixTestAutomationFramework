package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigManager2 {

	private static Properties prop;
	private static String path = "config" + File.separator + "config.properties";
	private static String env;

	private static final Logger logger = LogManager.getLogger(AuthTokenProvider.class);

	private ConfigManager2() {

	}

	static {
		logger.info("Reading env value passed from terminal ");
		if (System.getProperty("env") == null) {
			logger.warn("Env variable is not set ..... using qa as the env");
		}
		env = System.getProperty("env", "qa");
		logger.info("Running the tests in the env {} ", env);
		env = env.toLowerCase().trim();
		switch (env) {
		case "dev" -> path = "config" + File.separator + "config.dev.properties";
		case "qa" -> path = "config" + File.separator + "config.qa.properties";
		case "uat" -> path = "config" + File.separator + "config.uat.properties";
		default -> path = "config" + File.separator + "config.qa.properties";

		}
		logger.info("Using the properties file from the path  {}", path);
		prop = new Properties();
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (input == null) {
			logger.error("Cannot find the file at the path  {}", path);
			throw new RuntimeException("File not found at locaiton " + path);

		}
		try {
			prop.load(input);
		} catch (FileNotFoundException e) {
			logger.error("Cannot find the file in the path  {}", path, e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Something went wrong... please check the file in the path  {}", path, e);

		}
	}

	public static String getProperty(String key) {

		return prop.getProperty(key);
	}

}
