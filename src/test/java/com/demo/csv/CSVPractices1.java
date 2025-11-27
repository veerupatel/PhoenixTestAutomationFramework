package com.demo.csv;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVPractices1 {

	public static void main(String[] args) {
		InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/loginData.csv");
		InputStreamReader inputStreamReader = new InputStreamReader(file);
		CSVReader csvReader = new CSVReader(inputStreamReader);
		
		CsvToBean<UserBean> userList = new CsvToBeanBuilder(csvReader)
				.withType(UserBean.class)
				.withIgnoreEmptyLine(true)
				.build();
		
		List<UserBean> userListArray = userList.parse();
		System.out.println(userListArray.get(0));
		System.out.println(userListArray.get(1));
	}

}
