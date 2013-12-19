package com.test.automation.framework.samplepagemodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest {
	
	@Test
	public void googleSearchTest(){
		WebDriver driver = new FirefoxDriver();
		driver.get("www.google.com");
		GoogleHomePage homePage = new GoogleHomePage(driver);
		Assert.assertTrue(homePage.at());
		
		homePage.searchForString("Testing");
		
		GoogleResultsPage resultsPage = new GoogleResultsPage(driver);
		Assert.assertTrue(resultsPage.at());
		resultsPage.clickOnResult();
	}

}
