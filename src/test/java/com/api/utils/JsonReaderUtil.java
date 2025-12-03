package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtil {

	public static <T> Iterator<T> loadJSON(String fileName, Class<T[]> clazz) {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
//		Creates a new Jackson ObjectMapper object.
//		 This object is responsible for converting JSON → Java object.
		ObjectMapper objectMapper = new ObjectMapper();
		T[] classArray;
		List<T> list = null;
		try {
			classArray = objectMapper.readValue(inputStream, clazz);
			list = Arrays.asList(classArray);
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list.iterator();
	}

}
