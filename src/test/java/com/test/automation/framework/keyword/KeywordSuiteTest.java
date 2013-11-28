package com.test.automation.framework.keyword;

import java.io.File;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.test.automation.framework.keywordmodel.suite.SuiteReader;

public class KeywordSuiteTest {
	@Test
	public void readSuiteTestFile(){
		File file = new File("src/test/suite-csv.csv");
		SuiteReader suiteReader = new SuiteReader(file, new ArrayList<String>());
		System.out.println(suiteReader.getTestList());
	}

}
