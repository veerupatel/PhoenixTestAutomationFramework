package com.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtil {

	private static Dotenv dotenv;
	
	private static final Logger logger = LogManager.getFormatterLogger(EnvUtil.class);

	private EnvUtil() {

	}

	static {
		logger.info("Loading the .env file .....");
		dotenv = Dotenv.load();
	}

	public static String getValue(String varName) {
		logger.info("Reading the value of {} from .env ",varName);
		return dotenv.get(varName);
	}
}
