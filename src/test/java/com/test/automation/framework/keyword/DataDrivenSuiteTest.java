package com.test.automation.framework.keyword;

import java.io.File;
import java.util.ArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.framework.keywordmodel.executor.KeywordExecutor;
import com.test.automation.framework.keywordmodel.suite.ISimpleTest;
import com.test.automation.framework.keywordmodel.suite.TestSuite;

public class DataDrivenSuiteTest {
	
	@Test(dataProvider="Data")
	public void testExcutor(ISimpleTest simpleTest){
		File file = new File(simpleTest.getExtraArguments().get(0));
		KeywordExecutor keyExecutor = new KeywordExecutor(file);
		keyExecutor.execute();		
	}
	
	@DataProvider(name="Data")
	public Object[][] getTestData(){
		File file = new File("src/test/suite-csv.csv");
		TestSuite suiteReader = new TestSuite(file, new ArrayList<String>());
		return suiteReader.getTestNgDataDriveTestList();
	}

}
