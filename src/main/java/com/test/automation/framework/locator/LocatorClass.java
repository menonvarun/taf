package com.test.automation.framework.locator;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.test.automation.framework.locator.locatorfiles.LocatorFactory;
import com.test.automation.framework.locator.locatorfiles.LocatorFile;
import com.test.automation.framework.locator.pagefactory.TafDriverLocatorFactory;

public abstract class LocatorClass {
	private File file;
	
	protected LocatorClass(String filePath){
		this.file = new File(filePath);
	}
	
	protected LocatorClass(File file){
		this.file = file;
	}
	
	public File getLocatorFile(){
		return this.file;
	}
	
	public void setLocatorFile(String filePath){
		this.file = new File(filePath);		
	}
	
	public void setLocatorFile(File file){
		this.file = file;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T initialize(WebDriver driver){
		LocatorFile locatorFile = new LocatorFactory().getLocatorFile(this.file);
		ElementLocatorFactory locatorFactory = new TafDriverLocatorFactory(locatorFile, driver);
		PageFactory.initElements(locatorFactory, this);
		return (T) this;
	}

}
