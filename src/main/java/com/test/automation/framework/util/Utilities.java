package com.test.automation.framework.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	private WebDriver driver;
	
	public Utilities(WebDriver driver){
		this.driver = driver;
	}
	
	public void waitForElementPresent(WebElement element){
		WebDriverWait wait = new WebDriverWait(this.driver, 60);
		wait.until(presenceOfElement(element));
	}
	
	public void waitForElementPresent(List<WebElement> elements){
		WebDriverWait wait = new WebDriverWait(this.driver, 60);
		wait.until(presenceOfElements(elements));
	}
	
	public ExpectedCondition<Boolean> presenceOfElement(
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
	
	public ExpectedCondition<Boolean> presenceOfElements(
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
	
	public boolean isElementPresent(By by){
		
		return false;		
	}

}
