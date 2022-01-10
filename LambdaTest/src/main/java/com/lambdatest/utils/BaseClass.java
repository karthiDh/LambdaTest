package com.lambdatest.utils;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

public class BaseClass extends Initialization {

	public static Logger logger = DriverFactory.getLog().getLogger(SystemProperties.class);

	public void waitUntilElementIsVisible(WebElement element, long timeoutInSecs)
			throws IOException, InterruptedException {

		WebDriverWait webDriverWait;
		webDriverWait = new WebDriverWait(DriverFactory.getRemoteWebDriver(), timeoutInSecs);

		try {
			webDriverWait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			logger.error("WebElement is not visible");
			ReportUtil.fail(element + " WebElement is not visible");
		}
	}

	public void clickElement(WebElement element, String objname, int... waittime)
			throws IOException, InterruptedException {

		JavascriptExecutor js;
		js = (JavascriptExecutor) DriverFactory.getRemoteWebDriver();

		if (waittime.length > 0) {
			waitUntilElementIsVisible(element, waittime[0]);
		} else {
			waitUntilElementIsVisible(element, 20);
		}
		if (isObjectExists(element)) {
			js.executeScript("arguments[0].click()", element);
			logger.info("Clicked " + objname);
			ReportUtil.pass("Clicked " + objname);
		} else {
			logger.error("Unable to Click" + objname);
			ReportUtil.fail("Unable to Click" + objname);
		}

	}

	public boolean isObjectExists(WebElement element, int... waittime) throws IOException {
		try {
			if (waittime.length > 0) {
				waitUntilElementIsVisible(element, waittime[0]);
			} else {
				waitUntilElementIsVisible(element, 20);
			}
			return true;
		} catch (Exception e) {

			logger.error("Object Desn't Exists");
			ReportUtil.fail(element + " Object Desn't Exist");

			return false;
		}
	}

	public void verifyText(WebElement element, String expectedText) throws IOException {
		String actualText;
		SoftAssert s = new SoftAssert();
		DriverFactory.getRemoteWebDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		try {
			actualText = element.getText();
			if (StringUtils.isEmpty(actualText)) {
				actualText = element.getAttribute("value");
			}
			logger.info(" expected text : " + expectedText + " actualText :" + actualText);
			s.assertEquals(actualText, expectedText);
			logger.info("Following text is displayed =" + actualText);
			ReportUtil.pass("Following text is displayed =" + actualText);
		} catch (Exception e) {
			logger.error("Following text is not displayed =" + expectedText);
			ReportUtil.fail("Following text is not dispalyed =" + expectedText);
		}

	}

	public void verifyTextWithElement(WebElement element, String expectedText, String webelement) throws IOException {
		String actualText;
		SoftAssert s = new SoftAssert();
		DriverFactory.getRemoteWebDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		try {
			actualText = element.getText();
			if (StringUtils.isEmpty(actualText)) {
				actualText = element.getAttribute("value");
			}
			logger.info(" expected text : " + expectedText + " actualText :" + actualText + " in " + webelement);
			s.assertEquals(actualText, expectedText);
			logger.info("Following text is displayed =" + actualText + " in " + webelement);
			ReportUtil.pass("Following text is displayed =" + actualText + " in " + webelement);
		} catch (Exception e) {
			logger.error("Following text is not displayed =" + expectedText + " in " + webelement);
			ReportUtil.fail("Following text is not dispalyed =" + expectedText + " in " + webelement);
		}
	}

	public boolean isElementPresent(WebElement element) {
		try {
			waitUntilElementIsVisible(element, 20);
			ReportUtil.pass("Element is present: " + element.isDisplayed());
			logger.info("Element is present");
			return element.isDisplayed() || element.isEnabled();
		} catch (Exception e) {
			logger.info(this.getClass().getName() + " element is not present.\n" + e.getMessage());
		}
		logger.info("Element is not present");

		return false;
	}

	public void verifyTextEquals(String actual, String expected) throws IOException {
		try {
			SoftAssert s = new SoftAssert();
			s.assertEquals(actual, expected);
			logger.info("Actual value " + actual + " and Expected value " + expected + " both are equals");
			ReportUtil.pass("Actual value  " + actual + " and Expected value " + expected + " both are equals");
		} catch (Exception e) {
			logger.info("Actual value " + actual + " and Expected value " + expected + " both are not equals");
			ReportUtil.fail("Actual value  " + actual + " and Expected value " + expected + " both are not equals");
		}
	}

	public void sendKeys(WebElement element, String objname, String text) throws IOException {
		try {
			if (isObjectExists(element)) {
				element.clear();
				element.sendKeys(text);
				logger.info("'" + text + "' is entered in  " + objname + " field");
				ReportUtil.pass("'" + text + "' is entered in " + objname + " field");
			} else {
				logger.error("Element not present on the screen. Cannot set the Text: " + text);
				ReportUtil.fail("Element not present on the screen. Cannot set the Text: " + text);
			}
		} catch (Exception e) {
			logger.error("Element not present on the screen. Cannot set the Text: ");
			ReportUtil.fail(element + "Element not present on the screen. Cannot set the Text: ");
		}
	}

	public void selectvalue(WebElement element, String text) throws IOException {
		Select select = new Select(element);
		try {
			select.selectByVisibleText(text);
			logger.info("value selected from DropDown : " + text);
			ReportUtil.pass("value selected from DropDown : " + text);
		} catch (Exception e) {
			logger.error("value is not selected from DropDown");
			ReportUtil.fail(text + " value is not selected from DropDown");
		}
	}

	public void selectvalue1(WebElement element, String text) throws IOException {
		Select select = new Select(element);
		try {
			select.selectByValue(text);
			logger.info("value selected from DropDown : " + text);
			ReportUtil.pass("value selected from DropDown : " + text);
		} catch (Exception e) {
			logger.info("value selected from DropDown : " + text);
			ReportUtil.pass("value selected from DropDown : " + text);
		}
	}

	public void clickCheckbox(WebElement element, String objname, int... waittime)
			throws IOException, InterruptedException {

		JavascriptExecutor js;
		js = (JavascriptExecutor) DriverFactory.getRemoteWebDriver();

		if (waittime.length > 0) {
			waitUntilElementIsVisible(element, waittime[0]);
		} else {
			waitUntilElementIsVisible(element, 20);
		}
		if (isObjectExists(element)) {
			js.executeScript("arguments[0].click()", element);
			logger.info("CheckBox Clicked " + objname);
			ReportUtil.pass("CheckBox Clicked " + objname);
		} else {
			logger.error("Unable to Click the CheckBox");
			ReportUtil.fail("Unable to Click the CheckBox");
		}

	}

	public void clickJSButton(WebElement element, String objname, int... waittime)
			throws IOException, InterruptedException {

		JavascriptExecutor js;
		js = (JavascriptExecutor) DriverFactory.getRemoteWebDriver();

		if (waittime.length > 0) {
			waitUntilElementIsVisible(element, waittime[0]);
		} else {
			waitUntilElementIsVisible(element, 20);
		}
		if (isObjectExists(element)) {
			js.executeScript("arguments[0].click()", element);
			logger.info("Clicked " + objname);
			ReportUtil.pass("Clicked " + objname);
		} else {
			logger.error("Unable to Click" + objname);
			ReportUtil.fail("Unable to Click" + objname);
		}

	}

	public String createUniqueEmailAddress() {
		DateFormat dateFormat = new SimpleDateFormat("MMddyyyyhhmmss");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		String email = "Test" + date1 + "@gmail.com";
		return email;
	}

	public void selectOption(WebElement element) {
		Select s = new Select(element);
		s.getFirstSelectedOption();
		logger.info("First option selected:" + element);
	}

	public void robotTab(WebElement element, int... waittime) throws AWTException, IOException {

		try {
			Thread.sleep(2000);
			waitUntilElementIsVisible(element, 20);
			DriverFactory.getrobot().keyPress(KeyEvent.VK_TAB);
			DriverFactory.getrobot().keyRelease(KeyEvent.VK_TAB);
			logger.info("passed into next Tab after" + waittime + "Sec");
			ReportUtil.pass("passed into next Tab");

		} catch (Exception e) {
			logger.error("Failed to pass into next Tab");
			ReportUtil.fail("Failed to Pass into next Tab");
		}
	}

	public void clearText(WebElement element) {
		element.clear();
	}

	public String getText(WebElement element) throws IOException {

		DriverFactory.getRemoteWebDriver().manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		String actualValue = element.getText();
		logger.info("value : " + actualValue);
		ReportUtil.info("value : " + actualValue);
		return actualValue;
	}

	public void verifyAttribute(WebElement element) throws IOException {

		DriverFactory.getRemoteWebDriver().manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		String actualValue = element.getAttribute("value");
		ReportUtil.info("value : " + actualValue);
	}

	public void scrolltoelement(WebElement element) throws IOException {

		((JavascriptExecutor) DriverFactory.getRemoteWebDriver()).executeScript("arguments[0].scrollIntoView(true);",
				element);

		ReportUtil.pass("Moved to element" + element);
		logger.info("Moved to element" + element);
	}

	public boolean isElementPresent(WebElement element, String objName) {
		try {
			waitUntilElementIsVisible(element, 20);
			logger.info("Element is present " + objName);
			ReportUtil.pass("Element is present: " + element.isDisplayed());
			return element.isDisplayed() || element.isEnabled();
		} catch (Exception e) {
			logger.info(this.getClass().getName() + " element is not present.\n" + e.getMessage());
		}
		logger.error("Element is not present " + objName);
		return false;
	}

	public void mouseHover(WebElement element, String objName) throws IOException {

		Actions actions;
		try {
			actions = new Actions(DriverFactory.getRemoteWebDriver());
			actions.moveToElement(element).perform();
			logger.info("MouseHover To Element: " + objName);
			ReportUtil.pass("MouseHover To Element: " + objName);
		} catch (Exception e) {
			logger.error("unable to perform MouseHover To Element:" + objName);
			ReportUtil.fail("unable to perform MouseHover To Element: " + objName);
		}
	}

	public void robotEnter(WebElement element, int... waittime) throws AWTException, IOException {

		try {
			Thread.sleep(5000);
			waitUntilElementIsVisible(element, 20);
			DriverFactory.getrobot().keyPress(KeyEvent.VK_ENTER);
			DriverFactory.getrobot().keyRelease(KeyEvent.VK_ENTER);
			logger.info("Pressed Enter after " + waittime);
			ReportUtil.pass("Pressed Enter");
		} catch (Exception e) {
			logger.error("Failed to Press Enter");
			ReportUtil.fail("Failed to Press Enter");
		}

	}

	public boolean isElementPresent(List<WebElement> element, String objName, int i) {
		try {
			waitUntilElementIsVisible(element.get(i), 20);
			logger.info("Element is present " + objName);
			ReportUtil.pass(objName + " is present: " + element.get(i).isDisplayed());
			return element.get(i).isDisplayed() || element.get(i).isEnabled();
		} catch (Exception e) {
			logger.info(this.getClass().getName() + " element is not present.\n" + e.getMessage());
		}
		logger.error("Element is not present " + objName);
		return false;
	}

	public void moveToElementTillObject(WebElement element, String objName) throws IOException {

		Actions ac;
		try {
			waitUntilElementIsVisible(element, 20);
			ac = new Actions(DriverFactory.getRemoteWebDriver());
			ac.moveToElement(element).perform();
			logger.info("Scroll down till element " + objName);
			ReportUtil.pass("Scroll down till element " + objName);
		} catch (Exception e) {
			logger.info(this.getClass().getName() + " element is not present.\n" + e.getMessage());
			logger.info("Unable to scroll down till element " + objName);
			ReportUtil.fail("Unable to Scroll down till element " + objName);
		}
	}

	public void sendkeyAction(String element) {

		Actions act = new Actions(DriverFactory.getRemoteWebDriver());
		act.sendKeys(element).build().perform();
	}
}
