package com.test.automation.framework.pagemodel.pages;

import java.io.File;

import com.test.automation.framework.locator.CustomPageFactory;
import com.test.automation.framework.pagemodel.PageClass;
import com.test.automation.framework.util.locator.GoogleLocator;

public class GoogleResultsPage extends PageClass{
	GoogleLocator googleLocator;
	
	@Override
	public String toUrl() {
		return "";
	}

	@Override
	public boolean at() {		
		return googleLocator.searchResult.size() > 0;
	}
	
	@Override
	public void init() {
		File file = new File("src/test/resources/google.properties");
		googleLocator = CustomPageFactory.initElements(this.browser, GoogleLocator.class, file);
	}
	
	public void clickOnResult(){
        googleLocator.searchResult.get(0).click();
    }

}
