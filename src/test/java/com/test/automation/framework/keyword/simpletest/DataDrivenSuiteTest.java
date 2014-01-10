package com.test.automation.framework.keyword.simpletest;

import java.io.File;
import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.framework.config.DefaultConfig;
import com.test.automation.framework.keywordmodel.executor.KeywordExecutor;
import com.test.automation.framework.keywordmodel.suite.ISimpleTest;
import com.test.automation.framework.keywordmodel.suite.TestSuite;

public class DataDrivenSuiteTest {
	@BeforeClass
	public void setListner(){
		DefaultConfig config = DefaultConfig.getDefaultConfig();
		config.setConfigValue("listeners", "com.test.automation.framework.keyword.simpletest.BaseKeywordTesting");
	}
	
	@Test(dataProvider="Data")
	public void testExcutor(ISimpleTest simpleTest){
		File file = new File(simpleTest.getTestFilePath());
		KeywordExecutor keyExecutor = new KeywordExecutor(file);
		keyExecutor.execute();		
	}
	
	@DataProvider(name="Data")
	public Object[][] getTestData(){
		File file = new File("src/test/resources/keyword/simpletest/suite-csv.csv");
		TestSuite suiteReader = new TestSuite(file, new ArrayList<String>());
		return suiteReader.getTobeExecutedTests();
	}

}
