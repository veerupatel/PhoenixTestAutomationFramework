package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dataproviders.api.bean.UserBean;
import com.poiji.bind.Poiji;
import com.request.models.UserCredentials;

public class ExcelReaderFromPoijiUtil {
	
	private static final Logger logger = LogManager.getFormatterLogger(ExcelReaderFromPoijiUtil.class);

	private ExcelReaderFromPoijiUtil() {

	}

	public static <T> Iterator<T> loadTestData(String WorkBook,String sheetName,Class<T> clazz) {
		logger.info("Reading the test data from .xlsx file {} and the sheet name is {}",WorkBook,sheetName);
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(WorkBook);
		XSSFWorkbook myworkBook = null;
		XSSFSheet mySheet = null;

		try {
			myworkBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			logger.error("Cannot read the excel {}",WorkBook,e);
			e.printStackTrace();
		}
		mySheet = myworkBook.getSheet(sheetName);
		logger.info("Converting the XSSFSheet {} to POJO Class of type {}",sheetName,clazz);
		List<T> dataList = Poiji.fromExcel(mySheet, clazz);
		return dataList.iterator();
	}
}
