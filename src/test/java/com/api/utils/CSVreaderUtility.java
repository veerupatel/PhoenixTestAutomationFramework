package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVreaderUtility {
	
	private CSVreaderUtility() {
		
	}
	
	public static <T> Iterator<T> loadCSV(String pathOfCSVFile,Class<T> bean){
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
		InputStreamReader inputStreamReader = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(inputStreamReader);
		
	
		CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(bean)
				.withIgnoreEmptyLine(true)
				.build();
		
		List<T> list = csvToBean.parse();
		return list.iterator();
}

	
	
}
