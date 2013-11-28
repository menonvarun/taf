package com.test.automation.framework.locator;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.test.automation.framework.locator.locatorfiles.LocatorFileFactory;
import com.test.automation.framework.locator.locatorfiles.ILocatorFile;
import com.test.automation.framework.locator.pagefactory.KeywordBasedLocatorFactory;
import com.test.automation.framework.util.Browser;
/**
 * Base locator class which provides the flexibility of defining your locators as WebElement variables.
 * 
 * <p>This class enhances the PageFactory implementation provided by Selenium by providing an enhanced feature of 
 * storing locators onto a different file and fetching it from the file based on the keyword provided at runtime.
 * 
 * @author  Varun Menon
 *
 */
public abstract class Locator {
	private File file;
	
	/**
	 * Locator constructor to be called with default file-path containing 
	 * the key/value pair of key and respective locator.
	 * @param filePath Filepath of the file containing the key value pair having key and respective locator values.
	 */
	protected Locator(String filePath){
		this.file = new File(filePath);
	}
	
	/**
	 * Locator constructor to be called with default <code>File</code> object of the file containing 
	 * the key/value pair of key and respective locator.
	 * @param file <code>File</code> object of the file containing the key/value pair
	 */
	protected Locator(File file){
		this.file = file;
	}
	
	/**
	 * Get the current locator fiel stored.
	 * @return <code>File</code> object of the file currently used.
	 */
	public File getLocatorFile(){
		return this.file;
	}
	
	/**
	 * Set the locator file-path of the file that have to be used for fetching the locators
	 * 
	 * <p><b>Note:</b> Please call the {@link #initialize(driver) initialize(WebDriver driver)} or the 
	 * {@link #initialize(browser) initialize(Browser browser)} method after you set the file 
	 * to reinitialize your <code>WebElement</code> varaibles with the new locator file.
	 * 
	 * @param filePath File-path of the file that contains the key/value pair containing locators.
	 */
	public void setLocatorFile(String filePath){
		this.file = new File(filePath);		
	}
	
	/**
	 * Set the locator <code>File</code> object of the file containing 
	 * the key/value pair of key and respective locator.	 * 
	 * 
	 * <p><b>Note:</b> Please call the {@link #initialize(driver) initialize(WebDriver driver)} or the 
	 * {@link #initialize(browser) initialize(Browser browser)} method after you set the file 
	 * to reinitialize your <code>WebElement</code> varaibles with the new locator file.
	 * 
	 * @param file <code>File</code> object of the file containing the key/value pair.
	 * 
	 */
	public void setLocatorFile(File file){
		this.file = file;
	}
	
	/**
	 * Initializes the Selenium PageFactory based element locators {@link org.openqa.selenium.PageFactory PageFactory} 
	 * with the passed driver object.
	 * <p><b>Note:</b> Use this method when you are not using the page object model.\n
	 * If using the Page Object model supported by the given framework, try to use the method 
	 * {@link #initialize(Browser) initialize(Browser browser)}. 
	 * @param driver Driver object which should be used to initialize the Page Factory elements.
	 * @return Return the same class object
	 */
	@SuppressWarnings("unchecked")
	public <T> T initialize(WebDriver driver){
		if(driver == null)
			throw new LocatorException("Driver passed for locator initialization is null. Make suee the driver is initialized");
		ILocatorFile locatorFile = new LocatorFileFactory().getLocatorFile(this.file);
		ElementLocatorFactory locatorFactory = new KeywordBasedLocatorFactory(locatorFile, driver);
		PageFactory.initElements(locatorFactory, this);
		return (T) this;
	}
	
	/**
	 * Initializes the Selenium PageFactory based element locators {@link org.openqa.selenium.PageFactory PageFactory} 
	 * with the passed browser object.
	 * <p><b>Note:</b> Only use this method when you are using the page object model.
	 * If using not using the Page Object model , try to use the method 
	 * {@link #initialize(WebDriver) initialize(WebDriver driver)}. 
	 * @param browser Driver object which should be used to initialize the Page Factory elements.
	 * @return Return the same class object
	 */
	@SuppressWarnings("unchecked")
	public <T> T initialize(Browser browser){
		ILocatorFile locatorFile = new LocatorFileFactory().getLocatorFile(this.file);
		ElementLocatorFactory locatorFactory = new KeywordBasedLocatorFactory(locatorFile, browser);
		PageFactory.initElements(locatorFactory, this);
		return (T) this;
	}

}
