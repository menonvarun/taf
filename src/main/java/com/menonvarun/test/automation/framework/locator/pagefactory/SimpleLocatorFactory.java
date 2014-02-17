package com.menonvarun.test.automation.framework.locator.pagefactory;

import java.lang.reflect.Field;

import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.menonvarun.test.automation.framework.pagemodel.Browser;

/**
 * Factory to identify the respective Element locator based on whether the {@link Browser} object was set or not.
 * @author  Varun Menon
 *
 */
public class SimpleLocatorFactory implements ElementLocatorFactory{
	
	private final Browser browser;

	public SimpleLocatorFactory(Browser browser) {
	    this.browser = browser;
	}
	
	@Override
	public ElementLocator createLocator(Field field) {
		ElementLocator elementLocator = null;
		elementLocator = new BrowserBasedSimpleElementLocator(browser, field);
		return elementLocator;
	}

}
