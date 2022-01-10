package com.lambdatest.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.log4testng.Logger;

import com.lambdatest.pages.LambdaTestPlaygroundPageObject;

public class Initialization {
	static Logger logger = Logger.getLogger(SystemProperties.class);
	static String resultFile;
	public DriverFactory driver;
	public DriverFactory mdriver;
	public LambdaTestPlaygroundPageObject lambdatestPlaygroundPage;
	public Properties cloudProperties = new Properties();
	public static String driverInstance;

	@BeforeSuite
	public static void startTest() throws IOException {

		Utilities.deleteScreenshotFolder(System.getProperty("user.dir") + "/Results/Screenshots/");
		resultFile = ReportUtil.createReportFile();
	}

	@BeforeMethod
	@Parameters({"driverInstance"})
	public void startExecution(String driverInstance)
			throws FileNotFoundException, IOException, InterruptedException {
		Initialization.driverInstance = driverInstance;

		driver = DriverFactory.getInstance();
		driver.setDriver(driverInstance);
		pageObjects(driverInstance);
		String url = Utilities.getUrlFromPropery(SystemProperties.getEnvironment());
		DriverFactory.getRemoteWebDriver().manage().deleteAllCookies();
		DriverFactory.getRemoteWebDriver().manage().window().maximize();
		DriverFactory.getRemoteWebDriver().get(url);

	}

	public void pageObjects(String driverInstance) {

		lambdatestPlaygroundPage = new LambdaTestPlaygroundPageObject(driverInstance);
	}

	public String getURL() throws IOException {
		String url = Utilities.getUrlFromPropery(SystemProperties.getEnvironment());
		return url;
	}

	/******************************************************************
	 * Description : This Method is validate the properties and get the properties.
	 * Update Details :
	 ******************************************************************/
	public String getProperty(String property, Properties props) {
		if (System.getProperty(property) != null) {
			return System.getProperty(property);
		} else if (System.getenv().containsKey(property)) {
			return System.getenv(property);
		} else if (props != null) {
			return props.getProperty(property);
		}
		return null;
	}

	/******************************************************************
	 * Description : In this method the cloud properties provide URL and Access Key.
	 * Update Details :
	 * 
	 * @throws IOException
	 ******************************************************************/
	protected void initCloudProperties() {

		try (FileReader fr = new FileReader("cloud.properties")) {
			cloudProperties.load(fr);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@AfterMethod
	public void closeBrowser() throws IOException {
		ReportUtil.endTest();
		DriverFactory.getRemoteWebDriver().quit();
	}

	@AfterSuite
	public static void endTest() throws IOException {

		ReportUtil.RenameExtentReport(driverInstance);
		DriverFactory.getRemoteWebDriver().quit();
	}
}
