package com.lambdatest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

public class Utilities extends Initialization {

	private Utilities() {
		throw new IllegalStateException("Utility class");
	}

	static Logger logger = Logger.getLogger(Utilities.class);

	public static String getConfigData(String key) throws IOException {

		Properties prop = readPropertiesFile(".\\Properties\\Config.properties");
		if (prop != null) {
			return prop.getProperty(key).trim();
		} else {
			return "Property value is null";
		}

	}

	public static Properties readPropertiesFile(String fileName) throws IOException {

		Properties prop = null;
		try (FileInputStream fis = new FileInputStream(fileName)) {
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			logger.error(fnfe.getMessage());
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
		}
		return prop;

	}

	public static String getDate(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = new Date();
		return formatter.format(date);
	}


	public static String getUrlFromPropery(String environment) throws IOException {

		switch (environment) {

		case "PROD":
			return Utilities.getConfigData("prod");

		default:
			return Utilities.getConfigData("qa");
		}
	}
	
	public static void deleteScreenshotFolder(String folderPath) throws IOException {

		logger.info("Checking existance of " + folderPath + " folder for the session run");
		File file = new File(folderPath);
		if (file.exists()) {
			FileUtils.deleteDirectory(new File(folderPath));
			logger.info("Folder " + folderPath + " exists and is deleted");
		} else {
			logger.info("Folder " + folderPath + " does not exists");
		}
	}


	public static void enterText(WebElement webElement, String textToEnter) {

		logger.info("Entering " + textToEnter + " in specified field");
		webElement.sendKeys(textToEnter);
		logger.info("Entered " + textToEnter + " in the specified field");
	}	

}
