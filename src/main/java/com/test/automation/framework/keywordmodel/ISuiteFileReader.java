package com.test.automation.framework.keywordmodel;

import java.util.List;

public interface ISuiteFileReader {
	
	public List<ISimpleTest> getSimpleTests();
	
	public List<IDatadrivenTest> getDatadrivenTests();

}
