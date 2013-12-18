package com.test.automation.framework.samplepagemodel;

import org.openqa.selenium.WebDriver;

import com.test.automation.framework.pagemodel.locator.GoogleLocator;

public class GoogleHomePage {
	
	GoogleLocator googleLocator;
	WebDriver driver;
	
	public GoogleHomePage(WebDriver driver){
		this.driver=driver;
		googleLocator = new GoogleLocator().initialize(this.driver);
	}
	
	public boolean at() {
		return this.driver.getTitle().contentEquals("Google");		
	}
	
	public void searchForString(String searchString){
		googleLocator.searchField.sendKeys(searchString);
        googleLocator.submitButton.click();
    }

}
