package com.test.automation.framework.keywordmodel.executor;

import org.openqa.selenium.WebDriver;

public class KeywordExecutor {
	
	WebDriver driver;
	KeywordFactory keyfactory;
	
	public KeywordExecutor(WebDriver driver){
		this.driver = driver;
		keyfactory = new KeywordFactory(driver);		
	}
	
	

}
