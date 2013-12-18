package com.test.automation.framework.pagemodel.pages;

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
		googleLocator = new GoogleLocator().initialize(this.browser);
	}
	
	public void clickOnResult(){
        googleLocator.searchResult.get(0).click();
    }

}
