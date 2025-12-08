package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dataproviders.api.bean.UserBean;
import com.poiji.bind.Poiji;
import com.request.models.UserCredentials;

public class ExcelReaderFromPoijiUtil {

	private ExcelReaderFromPoijiUtil() {

	}

	public static <T> Iterator<T> loadTestData(String WorkBook,String sheetName,Class<T> clazz) {
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(WorkBook);
		XSSFWorkbook myworkBook = null;
		XSSFSheet mySheet = null;

		try {
			myworkBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mySheet = myworkBook.getSheet(sheetName);
		List<T> dataList = Poiji.fromExcel(mySheet, clazz);
		return dataList.iterator();
	}
}
