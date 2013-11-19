package com.test.automation.framework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.test.automation.framework.config.DefaultConfig;

public class UserDefinedDriverProvider implements IDriverProvider{
	
	DefaultConfig config = new DefaultConfig();

	@Override
	public WebDriver getDriver() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		return driver;
	}
	
	
	

}
