package org.imaginea.test.automation.framework.keywordmodel.suite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility to read and parse test-suites and return {@link ISimpleTest} object of the tests present in the suite file.
 * @author  Varun Menon
 *
 */
public class TestSuite {
	
	private List<ISuiteFileReader> suiteReaders = new ArrayList<ISuiteFileReader>();
	private File file;
	private List<String> arguments;
	private ISuiteFileReader suiteReader;
	/**
	 * Constructor to create {@link TestSuite} object.
	 * @param file - {@link File} object of the test-suite file.
	 * @param arguments - Any extra arguments required for reading the suite file like sheetname, sheetindex, delimiter, separator.
	 */
	public TestSuite(File file, List<String> arguments){
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
	 * the suite file and return {@link ISimpleTest} objects for all the tests present in the suite file. 
	 * @return {@link List} or {@link ISimpleTest} for all the tests present inside the said test-suite file.
	 */
	public List<ISimpleTest> getAllTestsAsList(){
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
	 * the suite file and return {@link ISimpleTest} objects for all the tests present in the suite file.
	 * This method is mainly used as a Data provider for the TestNG data driven methods. 
	 * @return {@link Object}[][] containing object of {@link ISimpleTest} for all the tests present inside the said test-suite file.
	 */
	public Object[][] getAllTestsForDataDrive(){
		List<ISimpleTest> tests = this.getAllTestsAsList();
		Object[][] returnData = new Object[tests.size()][1];
		for(int i = 0; i< tests.size();i++){
			returnData[i][0] = tests.get(i);
		}
		
		return returnData;
	}
	
	/**
	 * This method internally identifies the supported {@link ISuiteFileReader} implementation in the framework and accordingly parses 
	 * the suite file and return {@link ISimpleTest} objects of only those tests that are enabled in the suite file.
	 * This method is mainly used as a Data provider for the TestNG data driven methods. 
	 * @return {@link Object}[][] containing object of {@link ISimpleTest} for the enabled tests present inside the said test-suite file.
	 */
	public Object[][] getTobeExecutedTests(){
		List<ISimpleTest> tests = this.getAllTestsAsList();
		List<ISimpleTest> enabledTests = new ArrayList<ISimpleTest>();
		
		for(int i = 0; i< tests.size();i++){
			ISimpleTest test = tests.get(i);
			if(test.isEnabled())
				enabledTests.add(test);
		}
		
		Object[][] returnData = new Object[enabledTests.size()][1];
		for(int i = 0; i< enabledTests.size();i++){
			returnData[i][0] = enabledTests.get(i);			
		}
		
		return returnData;
	}

}
