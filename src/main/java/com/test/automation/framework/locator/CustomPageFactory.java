package com.test.automation.framework.locator;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.test.automation.framework.locator.locatorfiles.ILocatorFile;
import com.test.automation.framework.locator.locatorfiles.LocatorFileFactory;
import com.test.automation.framework.locator.pagefactory.KeywordBasedLocatorFactory;
import com.test.automation.framework.locator.pagefactory.SimpleLocatorFactory;
import com.test.automation.framework.pagemodel.Browser;
import com.test.automation.framework.pagemodel.PageException;

/**
 * Custom Page Factory implementation of TAF which extends the original {@link PageFactory} of selenium.
 * 
 *<p>This class enhances the PageFactory implementation provided by Selenium by providing an enhanced feature of 
 * storing locators onto a different file and fetching it from the file based on the keyword provided at runtime.
 * 
 * @author  Varun Menon
 *
 */

public class CustomPageFactory extends PageFactory {
	
	/**
	 * Custom Page Factory method that initializes the Selenium PageFactory based element locators {@link org.openqa.selenium.PageFactory PageFactory} 
	 * with the passed driver object.
	 * 
	 * <p><b>Note:</b> Use this method when you are not using the page object model.\n
	 * If using the Page Object model supported by the given framework, try to use the method 
	 * {@link #initElements(Browser, Class, File)}. 
	 * @param driver Driver object which should be used to initialize the Page Factory elements.
	 * @param pageClassToProxy A class which will be initialized.
	 * @param file <code>File</code> object of the file containing the key/value pair.
	 * @return An instantiated instance of the class with WebElement and List<WebElement> fields proxied
	 */
	public static <T> T initElements(WebDriver driver, Class<T> pageClassToProxy,File file) {
		if(driver == null)
			throw new PageException("Driver passed for page initialization is null. Make sure the driver is initialized");
		T page = instantiatePage(driver, pageClassToProxy);
		initElements(driver, page, file);
		return (T) page;
	}
	
	/**
	 * Custom Page Factory method that initializes the Selenium PageFactory based element locators {@link org.openqa.selenium.PageFactory PageFactory} 
	 * with the passed browser object.
	 * 
	 * <p><b>Note:</b> Only use this method when you are using the page object model.
	 * If using not using the Page Object model , try to use the method 
	 * {@link #initElements(WebDriver, Class, File)}. 
	 * 
	 * @param browser Browser object which should be used to initialize the Page Factory elements.
	 * @param pageClassToProxy A class which will be initialized.
	 * @param file <code>File</code> object of the file containing the key/value pair.
	 * @return An instantiated instance of the class with WebElement and List<WebElement> fields proxied
	 */
	public static <T> T initElements(Browser browser, Class<T> pageClassToProxy,File file) {
		T page = instantiatePage(browser, pageClassToProxy);
		initElements(browser, page, file);
		return (T) page;
	}
	
	/**
	 * Custom Page Factory method that initializes the Selenium PageFactory based element locators {@link org.openqa.selenium.PageFactory PageFactory} 
	 * with the passed browser object.
	 * 
	 * <p><b>Note:</b> Only use this method when you are using the page object model.
	 * If using not using the Page Object model , try to use the method 
	 * {@link #initElements(WebDriver, Class, File)}. 
	 * 
	 * @param browser Browser object which should be used to initialize the Page Factory elements.
	 * @param pageClassToProxy A class which will be initialized.
	 * @param filePath File path of the file containing the key/value pair.
	 * @return An instantiated instance of the class with WebElement and List<WebElement> fields proxied
	 */
	public static <T> T initElements(Browser browser, Class<T> pageClassToProxy,String filePath) {
		File file = new File(filePath);
		T page = initElements(browser, pageClassToProxy,file);
		return (T) page;
	}
	
	/**
	 * Custom Page Factory method that initializes the Selenium PageFactory based element locators {@link org.openqa.selenium.PageFactory PageFactory} 
	 * with the passed driver object.
	 * 
	 * <p><b>Note:</b> Use this method when you are not using the page object model.\n
	 * If using the Page Object model supported by the given framework, try to use the method 
	 * {@link #initElements(Browser, Class, File)}. 
	 * @param driver Driver object which should be used to initialize the Page Factory elements.
	 * @param pageClassToProxy A class which will be initialized.
	 * @param filePath File path of the file containing the key/value pair.
	 * @return An instantiated instance of the class with WebElement and List<WebElement> fields proxied
	 */
	public static <T> T initElements(WebDriver driver, Class<T> pageClassToProxy,String filePath) {
		File file = new File(filePath);
		T page = initElements(driver, pageClassToProxy,file);
		return (T) page;
	}
	
	/**
	 * Custom Page Factory method that initializes the Selenium PageFactory based element locators {@link org.openqa.selenium.PageFactory PageFactory} 
	 * with the passed browser object.
	 * 
	 * <p><b>Note:</b> Only use this method when you are using the page object model.
	 * If using not using the Page Object model , try to use the method 
	 * {@link #initElements(WebDriver, Class, File)}. 
	 * 
	 * @param driver Driver object which should be used to initialize the Page Factory elements.
	 * @param pageClassObjectToProxy A class object which have to be initialized.
	 * @param file <code>File</code> object of the file containing the key/value pair.
	 * @return An instantiated instance of the class with WebElement and List<WebElement> fields proxied
	 */
	public static <T> T initElements(WebDriver driver, T pageClassObjectToProxy,File file){
		ILocatorFile locatorFile = new LocatorFileFactory().getLocatorFile(file);
		ElementLocatorFactory locatorFactory = new KeywordBasedLocatorFactory(locatorFile, driver);
		PageFactory.initElements(locatorFactory, pageClassObjectToProxy);
		return (T) pageClassObjectToProxy;
		
	}
	
	/**
	 * Custom Page Factory method that initializes the Selenium PageFactory based element locators {@link org.openqa.selenium.PageFactory PageFactory} 
	 * with the passed browser object.
	 * 
	 * <p><b>Note:</b> Only use this method when you are using the page object model.
	 * If using not using the Page Object model , try to use the method 
	 * {@link #initElements(WebDriver, Class, File)}. 
	 * 
	 * @param browser Browser object which should be used to initialize the Page Factory elements.
	 * @param pageClassObjectToProxy A class object which have to be initialized.
	 * @param file <code>File</code> object of the file containing the key/value pair.
	 * @return An instantiated instance of the class with WebElement and List<WebElement> fields proxied
	 */
	public static <T> T initElements(Browser browser, T pageClassObjectToProxy,File file){
		ILocatorFile locatorFile = new LocatorFileFactory().getLocatorFile(file);
		ElementLocatorFactory locatorFactory = new KeywordBasedLocatorFactory(locatorFile, browser);
		PageFactory.initElements(locatorFactory, pageClassObjectToProxy);
		return (T) pageClassObjectToProxy;		
	}
	
	/**
	 * Custom Page Factory method that initializes the Selenium PageFactory based element locators {@link org.openqa.selenium.PageFactory PageFactory} 
	 * with the passed driver object.
	 * 
	 * <p><b>Note:</b> Use this method when you are not using the page object model.\n
	 * If using the Page Object model supported by the given framework, try to use the method 
	 * {@link #initElements(Browser, Class, File)}. 
	 * @param driver Driver object which should be used to initialize the Page Factory elements.
	 * @param pageClassObjectToProxy A class object which have to be initialized.
	 * @param filePath File path of the file containing the key/value pair.
	 * @return An instantiated instance of the class with WebElement and List<WebElement> fields proxied
	 */
	public static <T> T initElements(WebDriver driver, T pageClassObjectToProxy,String filePath) {
		File file = new File(filePath);
		T page = initElements(driver, pageClassObjectToProxy,file);
		return (T) page;
	}
	
	/**
	 * Custom Page Factory method that initializes the Selenium PageFactory based element locators {@link org.openqa.selenium.PageFactory PageFactory} 
	 * with the passed driver object.
	 * 
	 * <p><b>Note:</b> Use this method when you are not using the page object model.\n
	 * If using the Page Object model supported by the given framework, try to use the method 
	 * {@link #initElements(Browser, Class, File)}. 
	 * @param browser Browser object which should be used to initialize the Page Factory elements.
	 * @param pageClassObjectToProxy A class object which have to be initialized.
	 * @param filePath File path of the file containing the key/value pair.
	 * @return An instantiated instance of the class with WebElement and List<WebElement> fields proxied
	 */
	public static <T> T initElements(Browser browser, T pageClassObjectToProxy,String filePath) {
		File file = new File(filePath);
		T page = initElements(browser, pageClassObjectToProxy,file);
		return (T) page;
	}
	
	 /**
	   * As
	   * {@link org.openqa.selenium.support.PageFactory#initElements(org.openqa.selenium.WebDriver, Class)}
	   * but will use the TAF Browser class object for getting the driver.
	   * 
	   * @param browser Browser object which should be used to initialize the Page Factory elements.
	   * @param page The object with WebElement and List<WebElement> fields that should be proxied.
	   */
	public static <T> T initElements(Browser browser, T pageClassObjectToProxy) {
		ElementLocatorFactory locatorFactory = new SimpleLocatorFactory(browser);
		initElements(locatorFactory, pageClassObjectToProxy);
		return pageClassObjectToProxy;		
	}
	
	
	private static <T> T instantiatePage(WebDriver driver,
			Class<T> pageClassToProxy) {
		try {
			try {
				Constructor<T> constructor = pageClassToProxy
						.getConstructor(WebDriver.class);
				return constructor.newInstance(driver);
			} catch (NoSuchMethodException e) {
				return pageClassToProxy.newInstance();
			}
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static <T> T instantiatePage(Browser browser,
			Class<T> pageClassToProxy) {
		try {
			try {
				Constructor<T> constructor = pageClassToProxy
						.getConstructor(Browser.class);
				return constructor.newInstance(browser);
			} catch (NoSuchMethodException e) {
				return pageClassToProxy.newInstance();
			}
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

}
