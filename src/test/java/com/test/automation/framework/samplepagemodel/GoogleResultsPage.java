package com.test.automation.framework.samplepagemodel;

import org.openqa.selenium.WebDriver;

import com.test.automation.framework.pagemodel.locator.GoogleLocator;

public class GoogleResultsPage {
	GoogleLocator googleLocator;
	WebDriver driver;
	public GoogleResultsPage(WebDriver driver){
		this.driver = driver;
		googleLocator = new GoogleLocator().initialize(this.driver);
	}
	
	public boolean at() {		
		return googleLocator.searchResult.size() > 0;
	}
		
	public void clickOnResult(){
        googleLocator.searchResult.get(0).click();
    }

}
