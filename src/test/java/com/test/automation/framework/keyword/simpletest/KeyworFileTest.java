package com.test.automation.framework.keyword.simpletest;

import java.io.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.automation.framework.config.DefaultConfig;
import com.test.automation.framework.keywordmodel.executor.KeywordExecutor;

public class KeyworFileTest {
	@BeforeClass
	public void setListner(){
		DefaultConfig config = DefaultConfig.getDefaultConfig();
		config.setConfigValue("listeners", "com.test.automation.framework.keyword.simpletest.BaseKeywordTesting");
	}
	
	@Test
	public void testMethod(){
		File file = new File("src/test/resources/keyword/simpletest/test-keyword.csv");
		KeywordExecutor keyExecutor = new KeywordExecutor(file);
		keyExecutor.execute();
	}

}
