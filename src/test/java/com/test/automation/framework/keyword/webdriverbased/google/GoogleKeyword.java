package com.test.automation.framework.keyword.webdriverbased.google;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.automation.framework.keywordmodel.keywords.KeywordBase;
import com.test.automation.framework.locator.CustomPageFactory;

public class GoogleKeyword extends KeywordBase{
	
	private WebDriver driver;
	private GoogleKeywordLocator googleLocator;
	
	public GoogleKeyword(WebDriver driver){
		this.driver = driver;
		File file = new File("src/test/resources/google.properties");
		googleLocator = CustomPageFactory.initElements(driver, GoogleKeywordLocator.class, file);		
	}
	/**
	 * Navigate to a particular url
	 * @param url
	 */
	public void navigateToUrl(String url){
		this.driver.get(url);
	}
	
	/**
	 * Searches for a string on google home page
	 * @param searchString
	 */
	public void searchForString(String searchString){
		googleLocator.searchField.sendKeys(searchString);
        googleLocator.submitButton.click();
    }
	
	/**
	 * Clicks on the first result in the google results list.
	 */
	public void clickOnResult(){
        clickOnResult(0);
    }
	
	/**
	 * Clicks on a particular item number in the list.
	 * @param index
	 */
	public void clickOnResult(int index){
        googleLocator.searchResult.get(index).click();
    }
	
	/**
	 * Waits for the results to be displayed.
	 */
	public void waitForResultsToDisplay(){
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				
				return googleLocator.searchResult.size()>0;
			}

			@Override
			public String toString() {
				return "Condition not fullfilled";
			}
		} 
		);
	}
	
	/**
	 * Wait for the said title to appear on the page.
	 * @param title
	 */
	public void waitForTitle(String title){
		WebDriverWait wait = new WebDriverWait(this.driver, 60);
		wait.until(ExpectedConditions.titleContains("Software testing"));
	}
}
