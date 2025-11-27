package com.dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.utils.CSVreaderUtility;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
	
	@DataProvider(name="LoginAPIDataProvider",parallel = true)
	public static Iterator<UserBean> loginAPIDataprovider() {
		return CSVreaderUtility.loadCSV("testData/loginData.csv");
	}

}
