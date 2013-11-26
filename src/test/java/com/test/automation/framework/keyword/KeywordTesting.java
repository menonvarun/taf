package com.test.automation.framework.keyword;

import org.testng.annotations.Test;

import com.test.automation.framework.keywordmodel.executor.KeywordFactory;

public class KeywordTesting {
	
	@Test
	public void test(){
		KeywordFactory fact = new KeywordFactory(null);
		Object[] obj = new Object[1];
		obj[0] = "1L";
		//obj[1] = "2";
		
		fact.executeKeyword("testing", obj);
	}

}
