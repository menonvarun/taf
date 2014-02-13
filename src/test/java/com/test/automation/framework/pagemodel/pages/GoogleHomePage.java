package com.test.automation.framework.pagemodel.pages;

import java.io.File;

import com.test.automation.framework.locator.CustomPageFactory;
import com.test.automation.framework.pagemodel.PageClass;
import com.test.automation.framework.util.locator.GoogleLocator;

public class GoogleHomePage extends PageClass{
	
	public GoogleLocator googleLocator;

	@Override
	public String toUrl() {
		return "";
	}

	@Override
	public boolean at() {
		return getDriver().getTitle().contentEquals("Google");		
	}
	
	public void searchForString(String searchString){
		googleLocator.searchField.sendKeys(searchString);
        googleLocator.submitButton.click();
    }

	@Override
	public void init() {
		//googleLocator = new GoogleLocator().initialize(this.browser);
		File file = new File("src/test/resources/google.properties");
		googleLocator = CustomPageFactory.initElements(this.browser, GoogleLocator.class, file);
	}

}
