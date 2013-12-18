package com.test.automation.framework.pagemodel;

import org.openqa.selenium.WebDriver;

import com.test.automation.framework.util.Utilities;

public abstract class PageClass {
	
	protected Utilities util;
	public Browser browser;
	
	public void init(Browser browser){
		this.browser = browser;
		this.util = new Utilities();	
		this.init();
	}
	
	protected void setDriver(WebDriver driver){
		this.browser.setDriver(driver);
	}
	
	protected WebDriver getDriver(){
		return this.browser.getDriver();
	}
	
	protected <T> T to(Class<PageClass> pageClass){
		T page = this.browser.to(pageClass);		
		return page;		
	}
	
	protected <T> T at(Class<PageClass> pageClass){
		T page = this.browser.at(pageClass);
		return page;
	}
	
	protected boolean isAt(Class<PageClass> pageClass){
		return this.browser.isAt(pageClass);
	}
	
	public abstract String toUrl();
	
	public abstract boolean at();
	
	public abstract void init();

}
