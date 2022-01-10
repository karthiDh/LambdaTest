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

			final String FCAUTOMATE_USERNAME = "karthik.vmindtree";
			final String FCAUTOMATE_ACCESS_KEY = "VBixRYgpCdVFTRj3u88FcgJBphDI3UzRbwFfvSUiNeCw34wzRj";
			String FURL = "https://" + FCAUTOMATE_USERNAME + ":" + FCAUTOMATE_ACCESS_KEY + "@hub.lambdatest.com/wd/hub";

			DesiredCapabilities fcaps = new DesiredCapabilities();

			fcaps.setCapability("browserName", "Firefox");
			fcaps.setCapability("version", "95.0");
			fcaps.setCapability("platform", "Windows 10");
			fcaps.setCapability("resolution", "1024x768");
			fcaps.setCapability("build", "LambdaTest");
			fcaps.setCapability("name", "FirefoxTestScript");
			fcaps.setCapability("video", true);
			fcaps.setCapability("console", true);
			fcaps.setCapability("network", true);
			fcaps.setCapability("visual", true);

			logger.info("********** Firefox driver *************");
			RemoteWebDriver.set(new RemoteWebDriver(new URL(FURL), fcaps));
			break;

		case "InternetExplorer":

			final String ICAUTOMATE_USERNAME = "karthik.vmindtree";
			final String ICAUTOMATE_ACCESS_KEY = "VBixRYgpCdVFTRj3u88FcgJBphDI3UzRbwFfvSUiNeCw34wzRj";
			String ICURL = "https://" + ICAUTOMATE_USERNAME + ":" + ICAUTOMATE_ACCESS_KEY
					+ "@hub.lambdatest.com/wd/hub";

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
			RemoteWebDriver.set(new RemoteWebDriver(new URL(ICURL), icaps));
			break;

		case "Edge":

			final String ECAUTOMATE_USERNAME = "karthik.vmindtree";
			final String ECAUTOMATE_ACCESS_KEY = "VBixRYgpCdVFTRj3u88FcgJBphDI3UzRbwFfvSUiNeCw34wzRj";
			String ECURL = "https://" + ECAUTOMATE_USERNAME + ":" + ECAUTOMATE_ACCESS_KEY
					+ "@hub.lambdatest.com/wd/hub";

			DesiredCapabilities ecaps = new DesiredCapabilities();

			ecaps.setCapability("browserName", "MicrosoftEdge");
			ecaps.setCapability("version", "96.0");
			ecaps.setCapability("platform", "Windows 10");
			ecaps.setCapability("resolution", "1024x768");
			ecaps.setCapability("build", "LambdaTest");
			ecaps.setCapability("name", "EdgeTestScript");
			ecaps.setCapability("video", true);
			ecaps.setCapability("console", true);
			ecaps.setCapability("network", true);
			ecaps.setCapability("visual", true);

			logger.info("********** Edge driver *************");
			RemoteWebDriver.set(new RemoteWebDriver(new URL(ECURL), ecaps));
			break;

		default:

			final String CAUTOMATE_USERNAME = "karthik.vmindtree";
			final String CAUTOMATE_ACCESS_KEY = "VBixRYgpCdVFTRj3u88FcgJBphDI3UzRbwFfvSUiNeCw34wzRj";
			String CURL = "https://" + CAUTOMATE_USERNAME + ":" + CAUTOMATE_ACCESS_KEY + "@hub.lambdatest.com/wd/hub";

			DesiredCapabilities ccaps = new DesiredCapabilities();

			ccaps.setCapability("browserName", "Chrome");
			ccaps.setCapability("version", "96.0");
			ccaps.setCapability("platform", "Windows 10");
			ccaps.setCapability("resolution", "1024x768");
			ccaps.setCapability("build", "LambdaTest");
			ccaps.setCapability("name", "ChromeTestScript");
			ccaps.setCapability("video", true);
			ccaps.setCapability("console", true);
			ccaps.setCapability("network", true);
			ccaps.setCapability("visual", true);

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
