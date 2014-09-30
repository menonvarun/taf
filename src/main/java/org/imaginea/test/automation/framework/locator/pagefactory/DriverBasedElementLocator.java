package org.imaginea.test.automation.framework.locator.pagefactory;

import java.lang.reflect.Field;
import java.util.List;

import org.imaginea.test.automation.framework.locator.locatorfiles.ILocatorFile;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;


/**
 * Simple driver baed elelment locator implementation for Page Factory
 * @author  Varun Menon
 *
 */
public class DriverBasedElementLocator implements ElementLocator {
	private final SearchContext searchContext;
	private final boolean shouldCache;
	private final By by;
	private WebElement cachedElement;
	private List<WebElement> cachedElementList;

	public DriverBasedElementLocator(ILocatorFile locatorFile, SearchContext searchContext, Field field) {
		this.searchContext = searchContext;
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

		List<WebElement> elements = searchContext.findElements(by);
		if (shouldCache) {
			cachedElementList = elements;
		}

		return elements;
	}

}
