package com.test.automation.framework.driver;

import org.openqa.selenium.WebDriver;

public interface IDriverFactory {
	
	public WebDriver getCurrentDriver(IDriverProvider driverProvider);
	public WebDriver clearCurrentDriver();

}
