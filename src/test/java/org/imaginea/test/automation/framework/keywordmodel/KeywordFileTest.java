package org.imaginea.test.automation.framework.keywordmodel;

import org.imaginea.test.automation.framework.config.DefaultConfig;
import org.imaginea.test.automation.framework.keywordmodel.executor.KeywordExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;


public class KeywordFileTest {

    @BeforeClass
	public void setListener(){
		DefaultConfig config = DefaultConfig.getDefaultConfig();
		config.setConfigValue("listeners", "org.imaginea.test.automation.framework.keywordmodel.TestKeywordDefinitionClass");
	}
	
	@Test
	public void testMethod(){
		File file = new File("src/test/resources/keyword/simpletest/test-keyword.csv");
		KeywordExecutor keyExecutor = new KeywordExecutor(file);
		keyExecutor.execute();
	}

}
