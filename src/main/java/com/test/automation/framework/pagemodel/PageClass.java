package com.test.automation.framework.pagemodel;

import java.io.File;

import org.openqa.selenium.WebDriver;

import com.test.automation.framework.util.Utilities;

/**
 * Utility class to support Page classes for the Page Object Model implementation
 * @author  Varun Menon
 *
 */
public abstract class PageClass {
	
	protected Utilities util;
	public Browser browser;
	private boolean initialized = false; 
	
	protected File locatorFile=null;
	
	/**
	 * Intialization method for the Page class
	 * @param browser
	 */
	public void init(Browser browser){
		this.browser = browser;
		this.util = new Utilities();	
		initialized = true;
		this.init();
	}
	
	/**
	 * Return true or false based on the coniditon whether the said object has been 
	 * initialized by the TAF framework or its just an object.
	 * @return <b>true</b> or <b>false</b>
	 */
	public boolean isInitialized(){
		return this.initialized;
	}
	
	/**
	 * Sets the driver object for use with this page class.
	 * @param driver {@link WebDriver} object to be set for use.
	 */
	protected void setDriver(WebDriver driver){
		this.browser.setDriver(driver);
	}
	
	/**
	 * Returns the driver object(if set) for this Page class else return the driver object from the {@link Browser} class object.
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
	 * Return the locator file which have to be used for initializing the WebElements of the current Page class.
	 * 
	 * @return <code>File</code>
	 */
	public File getLocatorFile(){
		return this.locatorFile;
	}
	
	/**
	 * Sets the locator file which have to be used for initializing the WebElements of the current Page class.
	 * Use this method if you want to specify some file else the default behavior of the Selenium PageFactory will be used.
	 * @param file
	 */
	public void setLocatorFile(File file){
		this.locatorFile = file;
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
	 * This method initializes any PageFactory based WebElements in the said
	 * class using the provided locator file.
	 * @param pageClass Page class extending {@link PageClass} which have to verified to be on.
	 * @param file - <code>File</code> object of the file containing the key/value pair of the locators. 
	 * @return true or false
	 */
	protected boolean isAt(Class<? extends PageClass> pageClass,File file){
		return this.browser.isAt(pageClass,file);
	}
	
	/**
	 * Similar to {@link #at(Class)} method, instead it return true or false based on the at verification done.
	 * This method initializes any PageFactory based WebElements in the said
	 * class using the provided locator file path.
	 * @param pageClass Page class extending {@link PageClass} which have to verified to be on.
	 * @param filePath - File path of the file containing the key/value pair of the locators.
	 * @return true or false
	 */
	protected boolean isAt(Class<? extends PageClass> pageClass,String filePath){
		return this.browser.isAt(pageClass,filePath);
	}
	
	/**
	 * The url for the said page. Please implement this method in your page classes and return the respective url for the page.
	 * In case the page dont have any url just return and empty string.	 * 
	 * 
	 * <p>
	 * <b>Note:</b>Do not append the base url to this url as it is automatically appended at runtime.
	 * @return - Page url without appending the base url.
	 */
	public abstract String toUrl();
	
	/**
	 * Method used to verify whether the driver object is on the said page or not.
	 * Return true or false based on the criteria to identify whether driver object is on the said page or not.
	 * 
	 * <p> This is a very important method as it will help in making you Page object model tests robust as it will help 
	 * you to validate the Page is shown before any of the util methods of the said page is executed.
	 * <b>Note:</b> For better results do not store your evaluated condition in a variable. Try to evaluate the success condition everytime this method gets executed. 
	 * @return true or false based on the page verification condition.
	 */
	public abstract boolean at();
	
	/**
	 * Use this method in case you want to initialize some variables or data while creating an Object of you Page class.
	 * This method will automatically get executed whenever you use {@link #to(Class)} or {@link #at(Class)} utils.
	 * In case you have nothing initialize just implement an empty method. 
	 */
	public abstract void init();

}
