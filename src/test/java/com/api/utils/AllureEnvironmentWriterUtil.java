package com.api.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AllureEnvironmentWriterUtil {
	
	public static final Logger logger = LogManager.getLogger(AllureEnvironmentWriterUtil.class);

	public static void createEnvironmentPropertiesFile() {
		String folderPath = "target/allure-results";
		File file = new File(folderPath);
		file.mkdirs();
		Properties prop = new Properties();
		// prop.setProperty("Name","Jatin");
		prop.setProperty("Project Name", "Phoenix API Automation Framework");
		prop.setProperty("Env", ConfigManager2.env);
		prop.setProperty("BASE_URI", ConfigManager2.getProperty("BASE_URL"));
		prop.setProperty("Operating System", System.getProperty("os.name"));
		prop.setProperty("Operating System Version", System.getProperty("os.version"));
		prop.setProperty("Java Version", System.getProperty("java.version"));
		
		FileWriter fw;
		try {
			fw = new FileWriter(folderPath+"/environment.properties");
			prop.store(fw, "My Properties File");
			logger.info("environment.properties file created successfully at {}", folderPath);

		} catch (IOException e) {
			logger.info("unable to create the environment.properties file",e);
			e.printStackTrace();
		}
		
	}

}
