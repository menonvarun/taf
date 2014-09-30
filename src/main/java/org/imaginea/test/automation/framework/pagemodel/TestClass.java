package org.imaginea.test.automation.framework.pagemodel;

import java.io.File;

import org.imaginea.test.automation.framework.util.Utilities;
import org.openqa.selenium.WebDriver;


/**
 * Base Class to be extended by your Test classes required to be executed for using the in-built 
 * Page Object Model supported by the framework. 
 * @author  Varun Menon
 *
 */
public class TestClass {
	protected WebDriver driver;
	protected Utilities util;
	protected Browser browser;
	
	
	public TestClass(){
		initialize();
	}
	
	private void initialize(){
		this.browser = new Browser();
		this.util = new Utilities();			
	}
	
	/**
	 * Sets the driver for this Test class
	 * @param driver {@link WebDriver} object to be set for the Test class
	 */
	protected void setDriver(WebDriver driver){
		this.browser.setDriver(driver);
		//this.driver=driver;
	}
	
	/**
	 * Return the current driver object for the said Test Class
	 * @return
	 */
	protected WebDriver getDriver(){
		return this.browser.getDriver();
	}
	
	/**
	 * Navigates to said page after appending the base url to Page url.
	 * This utility also verifies whether the driver is at the said Page by using "at" method of the Page after navigation. 
	 * @param pageClass - Page class extending {@link PageClass} to where the driver have to be navigated.
	 * @return The said page class object once verification is successful.
	 */
	protected <T> T to(Class<? extends PageClass> pageClass){
		T page = this.browser.to(pageClass);		
		return page;		
	}
	

	/**
	 * Navigates to said page after appending the base url to Page url and initializes any 
	 * PageFactory based WebElements in the said class using the provided locator file. 
	 * This utility also verifies whether the driver is at the said Page by using "at" method of the Page after navigation. 
	 * @param pageClass - Page class extending {@link PageClass} to where the driver have to be navigated.
	 * @param file - <code>File</code> object of the file containing the key/value pair of the locators.
	 * @return The said page class object once verification is successful.
	 */
	protected <T> T to(Class<? extends PageClass> pageClass,File file){
		T page = this.browser.to(pageClass);		
		return page;		
	}
	
	/**
	 * Navigates to said page after appending the base url to Page url. This method initializes any 
	 * PageFactory based WebElements in the said class using the provided locator file path. 
	 * This utility also verifies whether the driver is at the said Page by using "at" method of the Page after navigation. 
	 * @param pageClass - Page class extending {@link PageClass} to where the driver have to be navigated.
	 * @param filePath - File path of the file containing the key/value pair of the locators.
	 * @return The said page class object once verification is successful.
	 */
	protected <T> T to(Class<? extends PageClass> pageClass,String filePath){
		T page = this.browser.to(pageClass,filePath);		
		return page;		
	}
	
	/**
	 * Utility to verify that the driver is at said page or not.
	 * @param pageClass Page class extending {@link PageClass} which have to verified to be on.
	 * @return The said page class object once verification is successful.
	 */
	protected <T> T at(Class<? extends PageClass> pageClass){
		T page = this.browser.at(pageClass);
		return page;
	}
	

	/**
	 * Utility to verify that the driver is at said page or not.
	 * This method initializes any PageFactory based WebElements in the said
	 * class using the provided locator file.
	 * @param pageClass Page class extending {@link PageClass} which have to verified to be on.
	 * @param file - <code>File</code> object of the file containing the key/value pair of the locators.
	 * @return The said page class object once verification is successful.
	 */
	protected <T> T at(Class<? extends PageClass> pageClass,File file){
		T page = this.browser.at(pageClass);
		return page;
	}
	
	/**
	 * Utility to verify that the driver is at said page or not.
	 * This method initializes any PageFactory based WebElements in the said
	 * class using the provided locator file path.
	 * @param pageClass Page class extending {@link PageClass} which have to verified to be on.
	 * @param filePath - File path of the file containing the key/value pair of the locators.
	 * @return The said page class object once verification is successful.
	 */
	protected <T> T at(Class<? extends PageClass> pageClass,String filePath){
		T page = this.browser.at(pageClass,filePath);
		return page;
	}
	
	/**
	 * Similar to {@link #at(Class)} method, instead it return true or false based on the at verification done.
	 * @param pageClass Page class extending {@link PageClass} which have to verified to be on.
	 * @return true or false
	 */
	protected boolean isAt(Class<? extends PageClass> pageClass){
		return this.browser.isAt(pageClass);
	}
	
	/**
	 * Similar to {@link #at(Class)} method, instead it return true or false based on the at verification done.
	 * @param pageClass Page class extending {@link PageClass} which have to verified to be on.
	 * @param file - <code>File</code> object of the file containing the key/value pair of the locators. 
	 * @return true or false
	 */
	protected boolean isAt(Class<? extends PageClass> pageClass,File file){
		return this.browser.isAt(pageClass,file);
	}
	
	/**
	 * Similar to {@link #at(Class)} method, instead it return true or false based on the at verification done.
	 * @param pageClass Page class extending {@link PageClass} which have to verified to be on.
	 * @param filePath - File path of the file containing the key/value pair of the locators.
	 * @return true or false
	 */
	protected boolean isAt(Class<? extends PageClass> pageClass,String filePath){
		return this.browser.isAt(pageClass,filePath);
	}
	
	/**
	 * Utility to create and get the said page class object.
	 * This utility directly creates the said page class object and returns it. No navigation or verification is done for the said page.
	 * @param pageClass Page class extending {@link PageClass} for which the object has to be created and returned.
	 * @return The said page class object
	 */
	protected <T> T getPageObject(Class<? extends PageClass> pageClass){
		T page = this.browser.getPageObject(pageClass);		
		return page;		
	}
	
	/**
	 * Utility to create and get the said page class object.
	 * This utility directly creates the said page class object and returns it. No navigation or verification is done for the said page.
	 * @param pageClass Page class extending {@link PageClass} for which the object has to be created and returned.
	 * @param file - <code>File</code> object of the file containing the key/value pair of the locators.
	 * @return The said page class object
	 */
	protected <T> T getPageObject(Class<? extends PageClass> pageClass,File file){
		return this.browser.getPageObject(pageClass,file);
	}
	
	/**
	 * Utility to create and get the said page class object.
	 * This utility directly creates the said page class object and returns it. No navigation or verification is done for the said page.
	 * @param pageClass Page class extending {@link PageClass} for which the object has to be created and returned.
	 * @param filePath - File path of the file containing the key/value pair of the locators.
	 * @return The said page class object
	 */
	protected <T> T getPageObject(Class<? extends PageClass> pageClass,String filePath){
		File file = new File(filePath);
		return getPageObject(pageClass,file);
	}
	
}
