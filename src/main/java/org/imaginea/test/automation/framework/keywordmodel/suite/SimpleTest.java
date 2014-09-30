package org.imaginea.test.automation.framework.keywordmodel.suite;

import java.util.List;

public class SimpleTest implements ISimpleTest{
	
	private String testName;
	private String testId;
	private boolean enabled;
	private String testFilePath;
	private List<String> arguments;

	public SimpleTest(String testName, String testId, boolean enabled,String testFilePath, List<String> arguments){
		this.testName = testName;
		this.testId = testId;
		this.enabled = enabled;
		this.testFilePath = testFilePath;
		this.arguments = arguments;		
	}
	
	@Override
	public String getTestName() {
		return this.testName;
	}

	@Override
	public void setTestName(String testName) {
		this.testName = testName;
		
	}

	@Override
	public String getTestId() {
		return this.testId;
	}

	@Override
	public void setTestId(String testId) {
		this.testId = testId;		
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;		
	}

	@Override
	public List<String> getExtraArguments() {
		return this.arguments;
	}

	@Override
	public void setExtraArguments(List<String> arguments) {
		this.arguments = arguments;		
	}
	
	@Override
	public String getTestFilePath() {
		return testFilePath;
	}

	@Override
	public void setTestFilePath(String testFilePath) {
		this.testFilePath = testFilePath;
	}
	
	@Override
	public String toString(){
		String testN = "Test name: " + testName;
		String testI = "Test ID: " + testId;
		String enbld = "Enabled: " + enabled;
		String filePath = "FilePath: " + testFilePath;
		String args = "Arguments: " + arguments.toString();
		return testN +","+testI+","+enbld+","+filePath+","+args;
		
	}

}
