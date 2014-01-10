package com.test.automation.framework.keyword.webdriverbased.google;

import java.io.File;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.framework.config.DefaultConfig;
import com.test.automation.framework.keywordmodel.executor.KeywordExecutor;
import com.test.automation.framework.keywordmodel.suite.ISimpleTest;
import com.test.automation.framework.keywordmodel.suite.TestSuite;

public class GoogleKeywordTestSuite {
	private WebDriver driver;
	
	@BeforeClass
	public void setListner(){
		DefaultConfig config = DefaultConfig.getDefaultConfig();
		config.setConfigValue("listeners", "com.test.automation.framework.keyword.webdriverbased.google.GoogleKeyword");
	}
	
	@BeforeMethod
	public void init(){
		driver = new FirefoxDriver();
	}
	
	@AfterMethod
	public void cleanup(){
		driver.quit();
	}
	
	
	@Test(dataProvider="Data")
	public void googleSearchTestSuite(ISimpleTest simpleTest){
		File file = new File(simpleTest.getTestFilePath());
		KeywordExecutor keyExecutor = new KeywordExecutor(driver,file);
		keyExecutor.execute();		
	}
	
	@DataProvider(name="Data")
	public Object[][] getTestData(){
		File file = new File("src/test/resources/keyword/webdriverbased/GoogleTestSuite.xls");
		TestSuite suiteReader = new TestSuite(file, new ArrayList<String>());
		return suiteReader.getTobeExecutedTests();
	}

}
