package com.test.automation.framework.pagemodel;

import org.testng.annotations.Test;

import com.test.automation.framework.pagemodel.pages.GoogleHomePage;
import com.test.automation.framework.pagemodel.pages.GoogleResultsPage;
import com.test.automation.framework.util.TestClass;

public class GoogleTest extends TestClass{
	
	@Test
	public void googleTest(){
		GoogleHomePage homePage = to(GoogleHomePage.class);
		homePage.searchForString("Testing");
		
		util.waitForPage(browser, GoogleResultsPage.class);		
		GoogleResultsPage resultsPage = at(GoogleResultsPage.class);
		resultsPage.clickOnResult();
		try {
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getId());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void googleTest1(){
		GoogleHomePage homePage = to(GoogleHomePage.class);
		homePage.searchForString("Testing");
		
		util.waitForPage(browser, GoogleResultsPage.class);		
		GoogleResultsPage resultsPage = at(GoogleResultsPage.class);
		resultsPage.clickOnResult();
		try {
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getId());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
