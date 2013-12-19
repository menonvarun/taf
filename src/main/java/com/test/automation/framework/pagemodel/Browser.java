package com.test.automation.framework.pagemodel;

import org.openqa.selenium.WebDriver;

import com.test.automation.framework.config.DefaultConfig;
import com.test.automation.framework.driver.CacheDriverFactory;
import com.test.automation.framework.util.CommonMethods;
/**
 * Utility to provide Page Object Model support in the framework. This class provides all the utilities to create and navigate between page objects.
 * 
 * @author  Varun Menon
 *
 */
public class Browser {
	
	private WebDriver driver;
	private DefaultConfig config;
	private String baseUrl;
	private CommonMethods commonMethods;
	
	public Browser(){
		this.commonMethods = new CommonMethods(getConfig());
	}
	
	/**
	 * Returns the stored {@link WebDriver} object(if any) else fetches the {@link WebDriver} object from the {@link CacheDriverFactory} class.
	 * @return {@link WebDriver} object.
	 */
	public WebDriver getDriver() {
		if(this.driver==null){
			return new CacheDriverFactory(getConfig()).getDriver();
		} else{
			return driver;
		}		
	}
	
	/**
	 * Sets the {@link WebDriver} object into this Browser class object.
	 * 
	 * @param driver - {@link WebDriver} to be set and used by the Browser object.
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Returns the currently stored configuration object.
	 * @return stored {@link DefaultConfig} object(if any) else create and get the {@link DefaultConfig} object
	 */
	public DefaultConfig getConfig() {
		if(config ==null){
			config = DefaultConfig.getDefaultConfig();
		}
		return config;
	}
	
	/**
	 * Set the Default Config object to this object.
	 * @param config
	 */
	public void setConfig(DefaultConfig config) {
		this.config = config;
	}
	
	/**
	 * Get the base url for the Browser object
	 * @return
	 */
	public String getBaseUrl() {
		if(baseUrl.equalsIgnoreCase(""))
			baseUrl = config.getConfigValue("base_url");
		return baseUrl;
	}
	
	/**
	 * Sets the base Url
	 * @param baseUrl
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	/**
	 * Navigates to said page after appending the base url to Page url.
	 * This utility also verifies whether the driver is at the said Page by using "at" method of the Page after navigation. 
	 * @param pageClass - Page class extending {@link PageClass} to where the driver have to be navigated.
	 * @return The said page class object once verification is successful.
	 */
	public <T> T to(Class<?> pageClass){
		T page = this.commonMethods.to(this, pageClass);		
		return page;		
	}
	
	/**
	 * Utility to verify that the driver is at said page or not.
	 * @param pageClass Page class extending {@link PageClass} which have to verified to be on.
	 * @return The said page class object once verification is successful.
	 */
	public <T> T at(Class<?> pageClass){
		T page = this.commonMethods.at(this,pageClass);
		return page;
	}
	
	/**
	 * Similar to {@link #at(Class)} method, instead it return true or false based on the at verification done.
	 * @param pageClass Page class extending {@link PageClass} which have to verified to be on.
	 * @return true or false
	 */
	public boolean isAt(Class<?> pageClass){
		return this.commonMethods.isAt(this,pageClass);
	}
	
	/**
	 * Utility to create and get the said page class object.
	 * This utility directly creates the said page class object and returns it. No navigation or verification is done for the said page.
	 * @param pageClass Page class extending {@link PageClass} for which the object has to be created and returned.
	 * @return The said page class object
	 */
	public <T> T getPageObject(Class<?> pageClass){
		return this.commonMethods.getPageObject(this, pageClass);
	}
	

}
