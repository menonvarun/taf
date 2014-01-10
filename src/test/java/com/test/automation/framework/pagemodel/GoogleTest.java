package com.test.automation.framework.pagemodel;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.test.automation.framework.pagemodel.pages.GoogleHomePage;
import com.test.automation.framework.pagemodel.pages.GoogleResultsPage;

public class GoogleTest extends TestClass{

	@Test
	public void googleTest(){
		GoogleHomePage homePage = to(GoogleHomePage.class);
		
		homePage.searchForString("Testing");
		
		util.waitForPage(browser, GoogleResultsPage.class);		
		
		GoogleResultsPage resultsPage = at(GoogleResultsPage.class);
		
		resultsPage.clickOnResult();
		
		WebDriverWait wait = new WebDriverWait(browser.getDriver(), 60);
		wait.until(ExpectedConditions.titleContains("Software testing"));
	}
	
}
