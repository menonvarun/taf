package com.test.automation.framework.locator.pagefactory;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.test.automation.framework.locator.locatorfiles.LocatorFile;
import com.test.automation.framework.util.Browser;

public class KeywordBasedLocatorFactory implements ElementLocatorFactory{
	
	private final SearchContext searchContext;
	private final LocatorFile locatorFile;
	private final Browser browser;

	public KeywordBasedLocatorFactory(LocatorFile locatorFile,SearchContext searchContext) {
		this.locatorFile = locatorFile;
	    this.searchContext = searchContext;
	    this.browser =null;
	}
	
	public KeywordBasedLocatorFactory(LocatorFile locatorFile,Browser browser) {
		this.locatorFile = locatorFile;
	    this.searchContext = null;
	    this.browser = browser;
	}
	
	@Override
	public ElementLocator createLocator(Field field) {
		ElementLocator elementLocator = null;
		if(this.browser!=null){
			elementLocator = new BrowserBasedElementLocator(locatorFile, browser, field);
		} else {
			elementLocator = new DriverBasedElementLocator(locatorFile, searchContext, field);
		}
		return elementLocator;
	}

}
