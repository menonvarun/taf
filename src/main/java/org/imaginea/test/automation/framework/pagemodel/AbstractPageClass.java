package org.imaginea.test.automation.framework.pagemodel;

import java.io.File;
import java.net.URL;

import org.imaginea.test.automation.framework.util.PageObjectUtils;
import org.imaginea.test.automation.framework.util.Utilities;
import org.openqa.selenium.WebDriver;


/**
 * Utility class to support Page classes for the Page Object Model implementation
 * @author  Varun Menon
 *
 */
public abstract class AbstractPageClass extends PageObjectUtils {
	
	private boolean initialized = false;
	
	protected File locatorFile=null;
	
	/**
	 * Intialization method for the Page class
	 * @param browser
	 */
	public void init(Browser browser){
		this.browser = browser;
        config = browser.getConfig();
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
	
	public File evaluateLocatorFile(String fileName) {
		URL fileStream = this.toString().getClass().getResource(fileName);
		if(fileStream == null)
			return null;
		else
			return new File(fileStream.getPath());
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
