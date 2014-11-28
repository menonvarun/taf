package org.imaginea.test.automation.framework.pagemodel;

import org.imaginea.test.automation.framework.config.DefaultConfig;
import org.imaginea.test.automation.framework.driver.CacheDriverFactory;
import org.imaginea.test.automation.framework.util.PageObjectUtils;
import org.openqa.selenium.WebDriver;

/**
 * Utility to provide Page Object Model support in the framework. This class provides all the utilities to create and navigate between page objects.
 * 
 * @author  Varun Menon
 *
 */
public class Browser extends PageObjectUtils {
	
	private WebDriver driver;
	private String baseUrl;

	public Browser(){
        config = getConfig();
        browser = this;
	}
	
	/**
	 * Returns the stored {@link WebDriver} object(if any) else fetches the {@link WebDriver} object from the {@link CacheDriverFactory} class.
	 * @return {@link WebDriver} object.
	 */
	public WebDriver getDriver() {
		String inbuiltDriverRequired = getConfig().getConfigValue("use.inbuilt.driver");
			
		if(this.driver==null){
			if(!inbuiltDriverRequired.contentEquals("false"))
				return new CacheDriverFactory(getConfig()).getDriver();
			else
				throw new PageException("Inbulit driver provider feature is set to false. " +
						"Please explicitly create the driver object and set it to the browser object using the 'setDriver' method." +
						" Or set the value of 'use.inbuilt.driver' in taf config file to true");
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

}
