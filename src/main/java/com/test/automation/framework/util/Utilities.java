package com.test.automation.framework.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.automation.framework.pagemodel.Browser;

public class Utilities {
	private static int DEFAULT_TIMEOUT =60;
	
	
	public void waitForElementPresent(WebDriver driver, WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
		wait.until(presenceOfElement(element));
	}
	
	public void waitForElementPresent(WebDriver driver, List<WebElement> elements){
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
		wait.until(presenceOfElements(elements));
	}
	
	public void waitForPage(Browser browser, Class<?> pageClass){
		WebDriverWait wait = new WebDriverWait(browser.getDriver(), DEFAULT_TIMEOUT);
		wait.until(presenceOfPage(browser, pageClass));		
	}
	
	private ExpectedCondition<Boolean> presenceOfElement(
			final WebElement element) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				element.getTagName();
				return true;
			}

			@Override
			public String toString() {
				return "presence of element located by: " + element;
			}
		};
	}
	
	private ExpectedCondition<Boolean> presenceOfElements(
			final List<WebElement> elements) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				if(elements.size()>0)
					return true;
				return false;
			}

			@Override
			public String toString() {
				return "presence of element located by: " + elements;
			}
		};
	}
	
	private ExpectedCondition<Boolean> presenceOfPage(final Browser browser,
			final Class<?> pageClass) {
		return new ExpectedCondition<Boolean>() {
			CommonMethods commonMethods = new CommonMethods();
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean)commonMethods.isAt(browser,pageClass);				
			}

			@Override
			public String toString() {
				return "Presence of page: " + pageClass.getName();
			}
		};
	}
	
	public boolean isElementPresent(By by){
		
		return false;		
	}

}
