package com.test.automation.framework.keyword;

import java.io.File;

import org.testng.annotations.Test;

import com.test.automation.framework.keywordmodel.executor.KeywordExecutor;

public class KeyworFileTest {
	
	@Test
	public void testMethod(){
		File file = new File("src/test/test-keyword.csv");
		KeywordExecutor keyExecutor = new KeywordExecutor(null, file,(String[]) null);
		keyExecutor.execute();
	}

}
