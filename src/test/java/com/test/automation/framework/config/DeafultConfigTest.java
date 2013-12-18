package com.test.automation.framework.config;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DeafultConfigTest {
	
	@Test
	public void testSingletonConfigObject(){
		DefaultConfig configA = DefaultConfig.getDefaultConfig();
		DefaultConfig configB = DefaultConfig.getDefaultConfig();
		
		Assert.assertEquals(configA, configB);
	}

	@Test(description="Verify that the default file gets loaded from src/test/resources when not mentioned")
	public void testDefaultLocationForPropFile(){
		
	}
	
	@Test(description="Verify that the stored value in properties file is returned")
	public void verifyStoredConfigValue(){
		DefaultConfig config = DefaultConfig.getDefaultConfig();
		String value = config.getConfigValue("driver.name");
		 
		Assert.assertEquals(value, "firefox");		
	}
	
	@Test(description="Verify the stored config value can be modified")
	public void verifyModifyingStoredConfigValue(){
		
		DefaultConfig config = DefaultConfig.getDefaultConfig();
		String originalValue = config.getConfigValue("driver.name");
		
		config.setConfigValue("driver.name", "chrome");
		
		String actualValue = config.getConfigValue("driver.name");
		
		config.setConfigValue("driver.name", originalValue);
		
		Assert.assertEquals(actualValue, "chrome");
		
	}
	
	@Test(description="Verify and empty string is returned when the key is not present in config")
	public void verifyEmptyValueForNotAvailableKey(){
		
		DefaultConfig config = DefaultConfig.getDefaultConfig();
		String actualValue = config.getConfigValue("testing.empty");
		
		Assert.assertEquals(actualValue, "");		
	}
	
	
	@Test(description="Verify a value can be stored in the config when the key is not present in config")
	public void verifyStoringValueForNotAvailableKey(){
		
		DefaultConfig config = DefaultConfig.getDefaultConfig();
		config.setConfigValue("testing.empty","testing");
		
		String actualValue = config.getConfigValue("testing.empty");
		
		Assert.assertEquals(actualValue, "testing");		
	}
	
	
}
