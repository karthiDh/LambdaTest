package com.lambdatest.utils;

public class UtilConstants {

	private UtilConstants() {
		throw new IllegalStateException("Constants class");
	}

	// Date formats
	public static final String DD_M_YY_HH_MM_SS = "dd-M-yyyy hh:mm:ss";

	// Rest header constants
	public static final String HEADER_AUTHORIZATION = "Authorization";
	public static final String HEADER_APP_TYPE_JSON = "application/json";
	public static final String HEADER_CONTENT_TYPE = "Content-type";

	//logger constants
	public static final String TEST_EXECUTION_START = "TEST_EXECUTION_START";
	public static final String TEST_EXECUTION_END = "TEST_EXECUTION_END";
	public static final String ERROR_OCCURED = "Error occurred ";
	public static final String SYSTEM_USER = System.getProperty("user.dir");
	public static final String MOBILECAPABILITYTYPE_BROWSER_NAME = "MobileCapabilityType.BROWSER_NAME";
	public static final String MOBILECAPABILITYTYPE_PLATFORM_NAME = "MobileCapabilityType.PLATFORM_NAME";

}
