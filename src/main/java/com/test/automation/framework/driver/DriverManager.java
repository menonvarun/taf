package com.test.automation.framework.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	private static final ThreadLocal<WebDriver> driverManager = new ThreadLocal<WebDriver>();
	
	public synchronized static WebDriver getStoredDriver(){
		WebDriver driver =  driverManager.get();
		if(driver == null){
			throw new RuntimeException("No driver has been stored for the current thread");			
		}
		return driver;
	}
	
	public synchronized static void storeDriver(WebDriver driver){
		driverManager.set(driver);
	}

}
