package org.imaginea.test.automation.framework.keywordmodel;

import org.imaginea.test.automation.framework.config.DefaultConfig;
import org.imaginea.test.automation.framework.keywordmodel.executor.KeywordExecutor;
import org.imaginea.test.automation.framework.keywordmodel.suite.ISimpleTest;
import org.imaginea.test.automation.framework.keywordmodel.suite.TestSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;


public class DataDrivenSuiteTest {
	@BeforeClass
	public void setListener(){
		DefaultConfig config = DefaultConfig.getDefaultConfig();
		config.setConfigValue("listeners", "org.imaginea.test.automation.framework.keywordmodel.TestKeywordDefinitionClass");
	}
	
	@Test(dataProvider="CsvData")
	public void testExecutorUsingCsvSuiteInput(ISimpleTest simpleTest){
		File file = new File(simpleTest.getTestFilePath());
		KeywordExecutor keyExecutor = new KeywordExecutor(file);
		keyExecutor.execute();		
	}

    @Test(dataProvider="XlsData")
    public void testExecutorUsingXlsSuiteInput(ISimpleTest simpleTest){
        File file = new File(simpleTest.getTestFilePath());
        KeywordExecutor keyExecutor = new KeywordExecutor(file);
        keyExecutor.execute();
    }
	
	@DataProvider(name="CsvData")
	public Object[][] getTestDataUsingCsv(){
		File file = new File("src/test/resources/keyword/simpletest/suite-csv.csv");
		TestSuite suiteReader = new TestSuite(file, new ArrayList<String>());
		return suiteReader.getTobeExecutedTests();
	}

    @DataProvider(name="XlsData")
    public Object[][] getTestDataUsingXlsx(){
        File file = new File("src/test/resources/keyword/simpletest/suite-xls.xlsx");
        TestSuite suiteReader = new TestSuite(file, new ArrayList<String>());
        return suiteReader.getTobeExecutedTests();
    }

}
