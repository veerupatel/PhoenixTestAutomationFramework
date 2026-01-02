package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtil {
	
	private static final Logger logger = LogManager.getFormatterLogger(JsonReaderUtil.class);

	public static <T> Iterator<T> loadJSON(String path, Class<T[]> clazz) {
		logger.info("Reading the JSON from the file {}",path);
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		ObjectMapper objectMapper = new ObjectMapper();
		T[] classArray;
		List<T> list = null;
		try {
			logger.info("Converting the JSON Data to the bean class {}",clazz);
			classArray = objectMapper.readValue(is, clazz);
			list = Arrays.asList(classArray);
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("Cannot read the json from the file {}",path);
			e.printStackTrace();
		}

		return list.iterator();
	}

}
