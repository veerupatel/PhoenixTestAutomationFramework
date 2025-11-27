package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class CSVPractices {

	public static void main(String[] args) throws IOException, CsvException {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData\\loginData.csv");
		InputStreamReader inputStreamReader = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(inputStreamReader);
		
		
		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(UserBean.class)
				.withIgnoreEmptyLine(true)
				.build();
		
		List<UserBean> userlist = csvToBean.parse();
		System.out.println(userlist.get(0));
		System.out.println(userlist.get(1));
		
		
				
		

	}

}
