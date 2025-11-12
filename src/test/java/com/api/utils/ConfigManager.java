package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

	private static Properties prop = new Properties();

	private ConfigManager() {

	}

	static {
		File configFile = new File(
				System.getProperty("user.dir") +File.separator +"src"+ File.separator +"test" +File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");

		FileReader reader = null;
		try {
			reader = new FileReader(configFile);
			prop.load(reader);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {

		return prop.getProperty(key);
	}

}
