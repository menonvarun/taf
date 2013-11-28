package com.test.automation.framework.keywordmodel.suite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SuiteReader {
	
	private List<ISuiteFileReader> suiteReaders = new ArrayList<ISuiteFileReader>();
	private File file;
	private List<String> arguments;
	private ISuiteFileReader suiteReader;
	public SuiteReader(File file, List<String> arguments){
		this.file = file;
		this.arguments = arguments;
		init();
	}
	
	private void init(){
		suiteReaders.add(new CsvSuiteFileReader());
		suiteReaders.add(new ExcelSuiteFileReader());
	}
	
	public List<ISimpleTest> getTestList(){
		boolean fileSupported = false;
		for(ISuiteFileReader suiteReader : suiteReaders){
			if(suiteReader.isSupported(file)){
				fileSupported = true;
				this.suiteReader = suiteReader;
				break;
			}
		}
		if(!fileSupported)
			throw new RuntimeException("None of the existing suite reader " +
					"supports the given file: "+file.getName());
		
		return this.suiteReader.read(this.file, this.arguments);
	}
	
	public Object[][] getTestNgDataDriveTestList(){
		List<ISimpleTest> tests = this.getTestList();
		Object[][] returnData = new Object[tests.size()][1];
		for(int i = 0; i< tests.size();i++){
			returnData[i][0] = tests.get(i);
		}
		
		return returnData;
	}

}
