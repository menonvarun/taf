package com.test.automation.framework.locator;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.test.automation.framework.locator.locatorfiles.LocatorFactory;
import com.test.automation.framework.locator.locatorfiles.LocatorFile;
import com.test.automation.framework.locator.pagefactory.TafDriverLocatorFactory;

public class Locator {
	File fl;
	WebDriver driver;
	public Locator(WebDriver driver,File file){
		this.driver = driver;
		this.fl = file;		
	}
	
	@SuppressWarnings("unchecked")
	public <T> T initialize(Class<?> locatorPage){
		LocatorFile locatorFile = new LocatorFactory().getLocatorFile(this.fl);
		ElementLocatorFactory locatorFactory = new TafDriverLocatorFactory(locatorFile, driver);
		T page;
		try {
			page = (T) locatorPage.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new LocatorException(e);
		}
		PageFactory.initElements(locatorFactory, page);
		return page;
	}

}
