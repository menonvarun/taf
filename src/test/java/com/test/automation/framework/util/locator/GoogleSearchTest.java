package com.test.automation.framework.util.locator;


import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.automation.framework.locator.CustomPageFactory;
import com.test.automation.framework.util.Utilities;


public class GoogleSearchTest {

	WebDriver driver;
	@BeforeMethod
	public void beforeMethod(){
		driver = new FirefoxDriver();
	}
	
	@Test
	public void googleTest() throws InterruptedException{
		File file = new File("src/test/resources/google.properties");
		GoogleLocator googleSearch = CustomPageFactory.initElements(driver, GoogleLocator.class, file);
		Utilities util = new Utilities();
		
		driver.get("http://www.google.co.in");
		googleSearch.searchField.sendKeys("Testing");
		googleSearch.submitButton.click();
		util.waitForElementPresent(driver,googleSearch.searchResult);
		String text = googleSearch.searchResult.get(0).getText();
		System.out.println(text);		
	}
	
	@AfterMethod
	public void afterMethod(){
		driver.quit();
	}
	

}
