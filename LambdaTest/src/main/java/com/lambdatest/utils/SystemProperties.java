package com.lambdatest.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemProperties {
    private Properties properties;
 
    public SystemProperties(String propertyFileName) throws IOException {
        InputStream is = getClass().getClassLoader()
            .getResourceAsStream(propertyFileName);
        this.properties = new Properties();
        this.properties.load(is);
    }
 
    public String getSystemProperty(String propertyName) {
        return this.properties.getProperty(propertyName);
    }
    
	public static String getBrowserType() throws IOException{
		SystemProperties reader = new SystemProperties("System.properties"); 
		return reader.getSystemProperty("browserType");
	}
	
	public static String getEnvironment() throws IOException{
		SystemProperties reader = new SystemProperties("System.properties"); 
		return reader.getSystemProperty("environmentType");
	}
}
