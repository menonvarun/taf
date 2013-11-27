package com.test.automation.framework.locator.pagefactory;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import com.test.automation.framework.locator.locatorfiles.ILocatorFile;
import com.test.automation.framework.util.Browser;

public class BrowserBasedElementLocator implements ElementLocator {
	private final Browser browser;
	private final boolean shouldCache;
	private final By by;
	private WebElement cachedElement;
	private List<WebElement> cachedElementList;

	public BrowserBasedElementLocator(ILocatorFile locatorFile, Browser browser, Field field) {
		this.browser = browser;
		Annotations annotations = new KeywordBasedAnnotations(locatorFile,field);
		shouldCache = annotations.isLookupCached();
		by = annotations.buildBy();
	}

	/**
	 * Find the element.
	 */
	public WebElement findElement() {
		if (cachedElement != null && shouldCache) {
			return cachedElement;
		}
		SearchContext searchContext = browser.getDriver();
		WebElement element = searchContext.findElement(by);
		if (shouldCache) {
			cachedElement = element;
		}

		return element;
	}

	/**
	 * Find the element list.
	 */
	public List<WebElement> findElements() {
		if (cachedElementList != null && shouldCache) {
			return cachedElementList;
		}
		
		SearchContext searchContext = browser.getDriver();
		List<WebElement> elements = searchContext.findElements(by);
		if (shouldCache) {
			cachedElementList = elements;
		}

		return elements;
	}

}
