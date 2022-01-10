package com.lambdatest.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportUtil extends Initialization {

	public ReportUtil() {
		throw new IllegalStateException("Report Utility class");
	}

	static ExtentReports report;
	static ExtentTest test;
	protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public static String reportFile = "";

	public static String date = Utilities.getDate(UtilConstants.DD_M_YY_HH_MM_SS);
	public static String concatinatedDate = date.replace(" ", "_");
	public static String modifiedDate = concatinatedDate.replace(":", "_");

	public static String createReportFile() throws IOException {

		if (Utilities.getConfigData("jenkinsTrigger").equalsIgnoreCase("true")) {
			reportFile = UtilConstants.SYSTEM_USER + "\\Results\\LambdaTest_.html";
		} else {
			reportFile = UtilConstants.SYSTEM_USER + "\\Results\\LambdaTest_" + modifiedDate + ".html";
		}
		report = new ExtentReports(reportFile);
		report.loadConfig(new File(UtilConstants.SYSTEM_USER + "\\Properties\\extent-config.xml"));

		return reportFile;
	}

	public static void startReport(String testCaseName) {
		test = report.startTest(testCaseName);
		extentTest.set(test);
	}

	public static void pass(String message) throws IOException {
		if (Utilities.getConfigData("passScreenshot").equalsIgnoreCase("true")) {
			extentTest.get().log(LogStatus.PASS, message, extentTest.get().addScreenCapture(takeScreenShot("PassImg")));
		} else {
			extentTest.get().log(LogStatus.PASS, message);
		}
	}

	public static void fail(String message) throws IOException {
		if (Utilities.getConfigData("failScreenshot").equalsIgnoreCase("true") && !message.contains("Error occurred")) {
			extentTest.get().log(LogStatus.FAIL, message);
			extentTest.get().log(LogStatus.FAIL, message, extentTest.get().addScreenCapture(takeScreenShot("FailImg")));
			throw new RuntimeException(message);
		} else {
			extentTest.get().log(LogStatus.FAIL, message);
			throw new RuntimeException(message);
		}
	}

	public static void info(String message) throws IOException {
		extentTest.get().log(LogStatus.INFO, message);
	}

	public static void skip(String message) {
		extentTest.get().log(LogStatus.SKIP, message);
	}

	public static void endTest() {
		report.endTest(test);
		report.endTest(extentTest.get());
		report.flush();
	}

	public static String takeScreenShot(String screenShotName) throws IOException {

		String df = Utilities.getDate("yyyyMMddhhss");

		TakesScreenshot ts;
		ts = (TakesScreenshot) DriverFactory.getRemoteWebDriver();

		File source = ts.getScreenshotAs(OutputType.FILE);
		String path = UtilConstants.SYSTEM_USER + "/Results/Screenshots/" + screenShotName + df + ".png";
		File destination = new File(path);
		FileUtils.copyFile(source, destination);

		return path;
	}

	public static void captureScreenShot(String screenShot) throws IOException {
		String screenshotPath = takeScreenShot(screenShot);
		extentTest.get().addScreenCapture(screenshotPath);
	}
}
