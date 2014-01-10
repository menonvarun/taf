package com.test.automation.framework.keyword.webdriverbased.google;

import java.io.File;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.automation.framework.config.DefaultConfig;
import com.test.automation.framework.keywordmodel.executor.KeywordExecutor;

public class GoogleKeywordTest {
	
	@BeforeClass
	public void setListner(){
		DefaultConfig config = DefaultConfig.getDefaultConfig();
		config.setConfigValue("listeners", "com.test.automation.framework.keyword.webdriverbased.google.GoogleKeyword");
	}
	
	@Test
	public void googleSearchKeywordTest(){
		WebDriver driver = new FirefoxDriver();
		File file = new File("src/test/resources/GoogleKeywordTest.xls");
		KeywordExecutor keyExecutor = new KeywordExecutor(driver, file,(String[]) null);
		keyExecutor.execute();
		driver.quit();
	}

}
