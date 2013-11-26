package com.test.automation.framework.keyword;

import org.testng.annotations.Test;

import com.test.automation.framework.keywordmodel.KeywordFactory;

public class KeywordTesting {
	
	@Test
	public void test(){
		KeywordFactory fact = new KeywordFactory();
		Object[] obj = new Object[1];
		obj[0] = "1.1";
		//obj[1] = "2";
		
		fact.executeKeyword("testing", obj);
	}

}
