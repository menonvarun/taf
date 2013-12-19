package com.test.automation.framework.datadrive;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.framework.datadrive.ExcelDataDrive;
import com.test.automation.framework.datadrive.ClassParser.ClassParserException;
import com.test.automation.framework.datadrive.ClassParser.DataNotAvailableException;
import com.test.automation.framework.pagemodel.TestClass;
import com.test.automation.framework.pagemodel.pages.GoogleHomePage;
import com.test.automation.framework.pagemodel.pages.GoogleResultsPage;

public class GoogleSearchDataTest extends TestClass{
	
	@Test(dataProvider="googleSearchData")
	public void googleTest(GoogleSearchData data){
		GoogleHomePage homePage = to(GoogleHomePage.class);
		
		homePage.searchForString(data.searchtext);
		
		util.waitForPage(browser, GoogleResultsPage.class);		
		
		GoogleResultsPage resultsPage = at(GoogleResultsPage.class);
		
		resultsPage.clickOnResult();
		
		WebDriverWait wait = new WebDriverWait(browser.getDriver(), 60);
		wait.until(ExpectedConditions.titleContains(data.titleToWait));
	}
	
	@DataProvider(name="googleSearchData")
	public Object[][] googleSearchData() throws ClassParserException, DataNotAvailableException{
		ExcelDataDrive excelData = 
				new ExcelDataDrive("src/test/resources/datadrive/GoogleSearchData.xls");
		return excelData.getTestngData(GoogleSearchData.class);
	}
}
