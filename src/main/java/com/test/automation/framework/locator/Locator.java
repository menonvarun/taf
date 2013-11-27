package com.test.automation.framework.locator;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.test.automation.framework.locator.locatorfiles.LocatorFileFactory;
import com.test.automation.framework.locator.locatorfiles.ILocatorFile;
import com.test.automation.framework.locator.pagefactory.KeywordBasedLocatorFactory;
import com.test.automation.framework.util.Browser;

public abstract class Locator {
	private File file;
	
	protected Locator(String filePath){
		this.file = new File(filePath);
	}
	
	protected Locator(File file){
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
		if(driver == null)
			throw new LocatorException("Driver passed for locator initialization is null. Make suee the driver is initialized");
		ILocatorFile locatorFile = new LocatorFileFactory().getLocatorFile(this.file);
		ElementLocatorFactory locatorFactory = new KeywordBasedLocatorFactory(locatorFile, driver);
		PageFactory.initElements(locatorFactory, this);
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T initialize(Browser browser){
		ILocatorFile locatorFile = new LocatorFileFactory().getLocatorFile(this.file);
		ElementLocatorFactory locatorFactory = new KeywordBasedLocatorFactory(locatorFile, browser);
		PageFactory.initElements(locatorFactory, this);
		return (T) this;
	}

}
