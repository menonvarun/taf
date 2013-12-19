package com.test.automation.framework.datadrive;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.test.automation.framework.driver.CacheDriverFactory;

public class DriverTest {
	
	@Test
	public void getDriverTest(){
		CacheDriverFactory driverFactory = new CacheDriverFactory();
		WebDriver driver = driverFactory.getDriver();
		driver.get("http://www.google.com");
	}

}
