package com.test.automation.framework.keywordmodel.suite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility to read and parse test-suites and return {@link ISimpleTest} object of the tests present in the suite file.
 * @author  Varun Menon
 *
 */
public class SuiteReader {
	
	private List<ISuiteFileReader> suiteReaders = new ArrayList<ISuiteFileReader>();
	private File file;
	private List<String> arguments;
	private ISuiteFileReader suiteReader;
	/**
	 * Constructor to create {@link SuiteReader} object.
	 * @param file - {@link File} object of the test-suite file.
	 * @param arguments - Any extra arguments required for reading the suite file like sheetname, sheetindex, delimiter, separator.
	 */
	public SuiteReader(File file, List<String> arguments){
		this.file = file;
		this.arguments = arguments;
		init();
	}
	
	private void init(){
		suiteReaders.add(new CsvSuiteFileReader());
		suiteReaders.add(new ExcelSuiteFileReader());
	}
	
	/**
	 * This method internally identifies the supported {@link ISuiteFileReader} implementation in the framework and accordingly parses 
	 * the suite file and return {@link ISimpleTest} objects for the tests present in the suite file. 
	 * @return {@link List} or {@link ISimpleTest} for the tests present inside the said test-suite file.
	 */
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
	
	/**
	 * This method internally identifies the supported {@link ISuiteFileReader} implementation in the framework and accordingly parses 
	 * the suite file and return {@link ISimpleTest} objects for the tests present in the suite file.
	 * This method is mainly used as a Data provider for the TestNG data driven methods. 
	 * @return {@link Object}[][] containing object of {@link ISimpleTest} for the tests present inside the said test-suite file.
	 */
	public Object[][] getTestNgDataDriveTestList(){
		List<ISimpleTest> tests = this.getTestList();
		Object[][] returnData = new Object[tests.size()][1];
		for(int i = 0; i< tests.size();i++){
			returnData[i][0] = tests.get(i);
		}
		
		return returnData;
	}

}
