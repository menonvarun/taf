package org.imaginea.test.automation.framework.locator.pagefactory;

import java.lang.reflect.Field;

import org.imaginea.test.automation.framework.locator.locatorfiles.ILocatorFile;
import org.imaginea.test.automation.framework.pagemodel.Browser;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;


/**
 * Factory to identify the respective Element locator based on whether the {@link Browser} object was set or not.
 * @author  Varun Menon
 *
 */
public class KeywordBasedLocatorFactory implements ElementLocatorFactory{
	
	private final SearchContext searchContext;
	private final ILocatorFile locatorFile;
	private final Browser browser;

	public KeywordBasedLocatorFactory(ILocatorFile locatorFile,SearchContext searchContext) {
		this.locatorFile = locatorFile;
	    this.searchContext = searchContext;
	    this.browser =null;
	}
	
	public KeywordBasedLocatorFactory(ILocatorFile locatorFile,Browser browser) {
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
