package com.lambdatest.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.testng.annotations.DataProvider;
import org.testng.log4testng.Logger;

public class DataproviderUtil extends BaseClass {

	private DataproviderUtil() {
		throw new IllegalStateException("DataProvider Utility class");
	}

	static Logger logger = Logger.getLogger(DataproviderUtil.class);
	static String path = ".\\Data\\DataSheet.xlsx";
	static LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
	static JSONArray jsonArray = new JSONArray();

	public static Object[][] getTestDataArray(String methodName) throws IOException {

		String[][] tabArray = null;
		String line = "";
		String[] value = null;
		int rowcount = 0;
		int colcount = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(".\\Data\\Testdata.csv"))) {
			while ((line = br.readLine()) != null) {
				value = line.split(",", -1);

				if (value[0].equals(methodName)) {
					rowcount++;
					colcount = value.length;
				}
			}

			line = "";
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		try (BufferedReader br1 = new BufferedReader(new FileReader(".\\Data\\Testdata.csv"))) {

			int i = 0;

			tabArray = new String[rowcount][colcount - 1];
			while ((line = br1.readLine()) != null) {
				value = line.split(",", -1);

				int p = 1;
				if (value[0].equals(methodName)) {
					for (int j = 0; j < colcount - 1; j++) {
						tabArray[i][j] = value[p];
						p++;
					}
					i++;
				}
			}

		} catch (

		Exception e) {
			logger.error(e.getMessage());
		}

		return (tabArray);

	}

	@DataProvider
	public static Object[][] verify_CreateNewAccount_validDetails() throws IOException {
		return getTestDataArray("verify_CreateNewAccount_validDetails");
	}

}
