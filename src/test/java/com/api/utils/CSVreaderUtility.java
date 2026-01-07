package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVreaderUtility {
	
	private static final Logger logger = LogManager.getFormatterLogger(CSVreaderUtility.class);
	
	private CSVreaderUtility() {
		
	}
	
	public static <T> Iterator<T> loadCSV(String pathOfCSVFile,Class<T> bean){
		logger.info("Loading the CSV file from the path {} ",pathOfCSVFile);
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
		InputStreamReader inputStreamReader = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(inputStreamReader);
		
		logger.info("Converting the CSV to the bean class {} ",bean);
		CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(bean)
				.withIgnoreEmptyLine(true)
				.build();
		
		List<T> list = csvToBean.parse();
		return list.iterator();
}

	
	
}
