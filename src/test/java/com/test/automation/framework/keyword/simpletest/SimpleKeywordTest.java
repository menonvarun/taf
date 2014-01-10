package com.test.automation.framework.keyword.simpletest;

import org.testng.annotations.Test;

import com.test.automation.framework.keywordmodel.executor.KeywordFactory;

public class SimpleKeywordTest {
	
	@Test
	public void test(){
		KeywordFactory fact = new KeywordFactory(null);
		Object[] obj = new Object[1];
		obj[0] = "42949672961231231231321313131313";		
		fact.executeKeyword("testing", obj);
	}

}
