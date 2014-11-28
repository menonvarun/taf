package org.imaginea.test.automation.framework.pagemodel;

import org.imaginea.test.automation.framework.util.PageObjectUtils;
import org.imaginea.test.automation.framework.util.Utilities;
import org.openqa.selenium.WebDriver;


/**
 * Base Class to be extended by your Test classes required to be executed for using the in-built 
 * Page Object Model supported by the framework. 
 * @author  Varun Menon
 *
 */
public class TestClass extends PageObjectUtils {
	protected WebDriver driver;
	
	public TestClass(){
		initialize();
	}
	
	private void initialize(){
		this.browser = new Browser();
        this.config = browser.getConfig();
	}
	
	/**
	 * Sets the driver for this Test class
	 * @param driver {@link WebDriver} object to be set for the Test class
	 */
	protected void setDriver(WebDriver driver){
		this.browser.setDriver(driver);
	}
	
	/**
	 * Return the current driver object for the said Test Class
	 * @return
	 */
	protected WebDriver getDriver(){
		return this.browser.getDriver();
	}

}
