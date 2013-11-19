package com.test.automation.framework.locator.pagefactory;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.test.automation.framework.locator.locatorfiles.LocatorFile;

public class TafDriverLocatorFactory implements ElementLocatorFactory{
	
	private final SearchContext searchContext;
	private final LocatorFile locatorFile;

	public TafDriverLocatorFactory(LocatorFile locatorFile,SearchContext searchContext) {
		this.locatorFile = locatorFile;
	    this.searchContext = searchContext;
	}	 
	
	@Override
	public ElementLocator createLocator(Field field) {
		ElementLocator elementLocator = new TafElementLocator(locatorFile, searchContext, field);
		return elementLocator;
	}

}
