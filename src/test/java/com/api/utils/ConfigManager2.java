package com.api.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager2 {

	private static Properties prop;
	private static String path = "config" + File.separator + "config.properties";
	private static String env;

	private ConfigManager2() {

	}

	static {
		env =System.getProperty("env", "qa");
		env= env.toLowerCase().trim();
		System.out.println(env + "Enviornment is Runngin" + path);
		switch (env) {
		case "dev" ->	path = "config" + File.separator + "config.dev.properties";
		case "qa" ->	path = "config" + File.separator + "config.qa.properties";
		case "uat" ->	path = "config" + File.separator + "config.uat.properties";
		default -> path =  "config" + File.separator + "config.qa.properties";
		}
		prop = new Properties();
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (input == null) {
			throw new RuntimeException("File not found at locaiton " + path);
		}
		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {

		return prop.getProperty(key);
	}

}
