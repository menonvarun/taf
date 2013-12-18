package com.test.automation.framework.pagemodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
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
	
	@Test
	public void googleSearchTest(){
		WebDriver driver = new FirefoxDriver();
		driver.get("www.google.co.in");
		
		driver.findElement(By.cssSelector("#gbqfq")).sendKeys("Testing");
		driver.findElement(By.cssSelector(".gbqfb")).click();
		
		driver.findElement(By.cssSelector("h3.r > a")).click();
		
	}
}
