package com.test.automation.framework.util;

import org.openqa.selenium.WebDriver;

import com.test.automation.framework.config.DefaultConfig;
import com.test.automation.framework.driver.CacheDriverFactory;

public class Browser {
	
	private WebDriver driver;
	private DefaultConfig config;
	private String baseUrl;
	private CommonMethods commonMethods;
	
	public Browser(){
		this.commonMethods = new CommonMethods(getConfig());
	}
	
	public WebDriver getDriver() {
		if(this.driver==null){
			return new CacheDriverFactory(getConfig()).getDriver();
		} else{
			return driver;
		}
		
		
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public DefaultConfig getConfig() {
		if(config ==null){
			config = DefaultConfig.getDefaultConfig();
		}
		return config;
	}
	
	public void setConfig(DefaultConfig config) {
		this.config = config;
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}
	
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	public <T> T to(Class<?> pageClass){
		T page = this.commonMethods.to(this, pageClass);		
		return page;		
	}
	
	public <T> T at(Class<?> pageClass){
		T page = this.commonMethods.at(this,pageClass);
		return page;
	}
	
	public boolean isAt(Class<?> pageClass){
		return this.commonMethods.isAt(this,pageClass);
	}
	
	public <T> T getPageObject(Class<?> pageClass){
		return this.commonMethods.getPageObject(this, pageClass);
	}
	

}
