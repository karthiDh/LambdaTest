package com.lambdatest.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.log4testng.Logger;

public class DriverFactory extends Initialization {
	static Logger logger = Logger.getLogger(SystemProperties.class);

	private static DriverFactory instance = null;
	public static ThreadLocal<RemoteWebDriver> RemoteWebDriver = new ThreadLocal<RemoteWebDriver>();
	public static ThreadLocal<Robot> robot = new ThreadLocal<Robot>();
	public static ThreadLocal<Logger> log = new ThreadLocal<Logger>();

	private DriverFactory() {
	}

	public static DriverFactory getInstance() {
		if (instance == null) {
			instance = new DriverFactory();
		}
		return instance;
	}

	public final void setDriver(String browser) throws FileNotFoundException, IOException {

		switch (browser) {

		case "Firefox":
			
			final String FAUTOMATE_USERNAME = "karthikvaradhan_ED8g2l";
			final String FAUTOMATE_ACCESS_KEY = "dBqn73B3xLzCAaXrKNz6";
			String FURL = "https://" + FAUTOMATE_USERNAME + ":" + FAUTOMATE_ACCESS_KEY
					+ "@hub-cloud.browserstack.com/wd/hub";

			DesiredCapabilities fcaps = new DesiredCapabilities();
			fcaps.setCapability("os", "Windows");
			fcaps.setCapability("os_version", "11");
			fcaps.setCapability("browser", "Firefox");
			fcaps.setCapability("browser_version", "latest");
			fcaps.setCapability("browserstack.local", "false");
			fcaps.setCapability("browserstack.selenium_version", "3.10.0");
			fcaps.setCapability("name", "Firefox_Execution ");
			fcaps.setCapability("project", "LambdaTest");
			fcaps.setCapability("build", "LambdaTestMacSafari_Execution");

			logger.info("********** Firefox driver *************");
			RemoteWebDriver.set(new RemoteWebDriver(new URL(FURL), fcaps));
			break;

		case "InternetExplorer":
			
			final String IAUTOMATE_USERNAME = "karthikvaradhan_ED8g2l";
			final String IAUTOMATE_ACCESS_KEY = "dBqn73B3xLzCAaXrKNz6";
			String IURL = "https://" + IAUTOMATE_USERNAME + ":" + IAUTOMATE_ACCESS_KEY
					+ "@hub-cloud.browserstack.com/wd/hub";

			DesiredCapabilities icaps = new DesiredCapabilities();
			icaps.setCapability("os", "OS X");
			icaps.setCapability("os_version", "Catalina");
			icaps.setCapability("browser", "safari");
			icaps.setCapability("browser_version", "13.1");
			icaps.setCapability("browserstack.local", "false");
			icaps.setCapability("name", "InternetExplorer_Execution ");
			icaps.setCapability("project", "LambdaTest");
			icaps.setCapability("build", "LambdaTestMacSafari_Execution");

			logger.info("********** IE driver *************");
			RemoteWebDriver.set(new RemoteWebDriver(new URL(IURL), icaps));
			break;
			
		case "Edge":
			
			final String EAUTOMATE_USERNAME = "karthikvaradhan_ED8g2l";
			final String EAUTOMATE_ACCESS_KEY = "dBqn73B3xLzCAaXrKNz6";
			String EURL = "https://" + EAUTOMATE_USERNAME + ":" + EAUTOMATE_ACCESS_KEY
					+ "@hub-cloud.browserstack.com/wd/hub";

			DesiredCapabilities ecaps = new DesiredCapabilities();
			ecaps.setCapability("os", "Windows");
			ecaps.setCapability("os_version", "11");
			ecaps.setCapability("browser", "Edge");
			ecaps.setCapability("browser_version", "latest");
			ecaps.setCapability("browserstack.local", "false");
			ecaps.setCapability("browserstack.selenium_version", "3.5.2");
			ecaps.setCapability("name", "Edge_Execution ");
			ecaps.setCapability("project", "LambdaTest");
			ecaps.setCapability("build", "LambdaTestMacSafari_Execution");

			logger.info("********** Edge driver *************");
			RemoteWebDriver.set(new RemoteWebDriver(new URL(EURL), ecaps));
			break;

		default:
			
			final String CAUTOMATE_USERNAME = "karthikvaradhan_ED8g2l";
			final String CAUTOMATE_ACCESS_KEY = "dBqn73B3xLzCAaXrKNz6";
			String CURL = "https://" + CAUTOMATE_USERNAME + ":" + CAUTOMATE_ACCESS_KEY
					+ "@hub-cloud.browserstack.com/wd/hub";

			DesiredCapabilities ccaps = new DesiredCapabilities();
			ccaps.setCapability("os", "Windows");
			ccaps.setCapability("os_version", "11");
			ccaps.setCapability("browser", "Chrome");
			ccaps.setCapability("browser_version", "latest");
			ccaps.setCapability("browserstack.local", "false");
			ccaps.setCapability("browserstack.selenium_version", "3.14.0");
			ccaps.setCapability("name", "Chrome_Execution ");
			ccaps.setCapability("project", "LambdaTest");
			ccaps.setCapability("build", "LambdaTestMacSafari_Execution");

			logger.info("********** Chrome driver *************");
			RemoteWebDriver.set(new RemoteWebDriver(new URL(CURL), ccaps));
			break;
		}
	}

	public static RemoteWebDriver getRemoteWebDriver() {
		return RemoteWebDriver.get();
	}

	public static Robot getrobot() {
		try {
			robot.set(new Robot());
		} catch (AWTException e) {
			e.printStackTrace();
		}
		return robot.get();
	}

	public static Logger getLog() {
		return log.get();
	}
}
