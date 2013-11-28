package com.test.automation.framework.keywordmodel.suite;

import java.io.File;
import java.util.List;


public interface ISuiteFileReader {
	
	public boolean isSupported(File file);
	
	public List<ISimpleTest> read(File file, List<String> arguments);

}
