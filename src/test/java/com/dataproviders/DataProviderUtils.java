package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.utils.CSVreaderUtility;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtil;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;
import com.request.models.CreateJobPayload;
import com.request.models.UserCredentials;

public class DataProviderUtils {

	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataprovider() {
		return CSVreaderUtility.loadCSV("testData/loginData.csv", UserBean.class);
	}

	@DataProvider(name = "CreateJobAPIDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIDataProvider() {
		Iterator<CreateJobBean> createJobBeanIterator = CSVreaderUtility.loadCSV("testData/createJobData.csv",
				CreateJobBean.class);
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		CreateJobBean tempBean;
		CreateJobPayload tempPayload;
		while (createJobBeanIterator.hasNext()) {
			tempBean = createJobBeanIterator.next();
			tempPayload = CreateJobBeanMapper.mapper(tempBean);
			payloadList.add(tempPayload);
		}
		return payloadList.iterator();

	}

	@DataProvider(name = "CreateJobAPIFakeDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIFakerDataProvider() {
		String fakerCount = System.getProperty("fakerCount", "5");
		int fakerCountInt = Integer.parseInt(fakerCount);
		Iterator<CreateJobPayload> payloadIterator = FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
		return payloadIterator;
	}

<<<<<<< HEAD
	@DataProvider(name = "LoginAPIExcelDataProvider", parallel = true)
	public static Iterator<UserCredentials> LoginAPIExcelDataProvider() {
		return ExcelReaderUtil.loadTestData();
	}
=======
	@DataProvider(name = "LoginAPIJsonDataProvider", parallel = true)
	public static Iterator<UserCredentials> LoginAPIJsonDataProvider() {
		return JsonReaderUtil.loadJSON("testData/loginAPIData.json", UserCredentials[].class);
	}

	@DataProvider(name = "CreateJobAPIJsonDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIJsonDataProvider() {
		return JsonReaderUtil.loadJSON("testData/createJobAPIData.json", CreateJobPayload[].class);
	}

>>>>>>> 4105951014d7530cfd0c44a76e13f7e930f6eece
}
