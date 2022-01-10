package com.lambdatest.scripts;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.lambdatest.utils.BaseClass;
import com.lambdatest.utils.ReportUtil;
import com.lambdatest.utils.SystemProperties;
import com.lambdatest.utils.UtilConstants;

public class LambdaTestPlaygroundTest extends BaseClass {

	static Logger logger = Logger.getLogger(SystemProperties.class);

	@Test
	public void SimpleFormDemo() throws IOException {
		try {
			String testName = new Throwable().getStackTrace()[0].getMethodName();
			logger.info(UtilConstants.TEST_EXECUTION_START);
			ReportUtil.startReport(testName);
			lambdatestPlaygroundPage.clickOnSimpleFormDemo();
			lambdatestPlaygroundPage.validatingURL();
			lambdatestPlaygroundPage.enteringValue();
			lambdatestPlaygroundPage.clickOnGetCheckedValue();
			lambdatestPlaygroundPage.validatingOutputMessage();
			ReportUtil.pass(UtilConstants.TEST_EXECUTION_END);
			logger.info(UtilConstants.TEST_EXECUTION_END);
		} catch (Exception e) {
			e.printStackTrace();
			ReportUtil.fail(UtilConstants.ERROR_OCCURED + e.getMessage());
		}
	}

	@Test
	public void DragAndDropSliders() throws IOException {
		try {
			String testName = new Throwable().getStackTrace()[0].getMethodName();
			logger.info(UtilConstants.TEST_EXECUTION_START);
			ReportUtil.startReport(testName);
			lambdatestPlaygroundPage.clickOnprogressBarSliders();
			lambdatestPlaygroundPage.defaultValue15();
			ReportUtil.pass(UtilConstants.TEST_EXECUTION_END);
			logger.info(UtilConstants.TEST_EXECUTION_END);
		} catch (Exception e) {
			e.printStackTrace();
			ReportUtil.fail(UtilConstants.ERROR_OCCURED + e.getMessage());
		}
	}

	@Test
	public void InputFormSubmit() throws IOException {
		try {
			String testName = new Throwable().getStackTrace()[0].getMethodName();
			logger.info(UtilConstants.TEST_EXECUTION_START);
			ReportUtil.startReport(testName);
			lambdatestPlaygroundPage.clickOnInputFormSubmit();
			lambdatestPlaygroundPage.enteringInputForm();
			lambdatestPlaygroundPage.clickOnInputFormSubmitButton();
			lambdatestPlaygroundPage.validatingInputFormOutputMessage();
			ReportUtil.pass(UtilConstants.TEST_EXECUTION_END);
			logger.info(UtilConstants.TEST_EXECUTION_END);
		} catch (Exception e) {
			e.printStackTrace();
			ReportUtil.fail(UtilConstants.ERROR_OCCURED + e.getMessage());
		}
	}

}
