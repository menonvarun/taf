package com.test.automation.framework.keywordmodel.suite;

import java.util.List;

public interface ISimpleTest {
	
	public String getTestName();
	
	public void setTestName(String testName);
	
	public String getTestId();
	
	public void setTestId(String testId);
	
	public boolean isEnabled();
	
	public void setEnabled(boolean enabled);
	
	public List<String> getExtraArguments();
	
	public void setExtraArguments(List<String> arguments);
	

}
