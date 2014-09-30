package org.imaginea.test.automation.framework.keywordmodel.suite;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.imaginea.test.automation.framework.util.readers.ExcelReader;



public class ExcelSuiteFileReader implements ISuiteFileReader{

	@Override
	public boolean isSupported(File file) {
		boolean supported = false;
		String fileName = file.getName();
		if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx"))
			supported = true;
		return supported;
	}

	@Override
	public List<ISimpleTest> read(File file, List<String> arguments) {
		ExcelReader excelReader;
		try{
			if(arguments.size()>1)
				excelReader = new ExcelReader(file, arguments.get(0));
			else
				excelReader = new ExcelReader(file);
		} catch(IOException e){
			throw new RuntimeException(e);
		}
		return this.getTests(excelReader);
	}
	
	private List<ISimpleTest> getTests(ExcelReader excelReader){
		List<ISimpleTest> tests = new ArrayList<ISimpleTest>();
		int noOfTestRows = excelReader.getNoOfRows();
		for(int row = 1; row < noOfTestRows; row++){
			int noOfColumn = excelReader.getNoOfColumn(row);
			if(noOfColumn < 4){
			 throw new RuntimeException("Test suite file fhsoul contain a minimum of 4 values for each test."
					 +"These values ar Test-id, Test-Name, Enabled/Disabled and Filepath");				
			}
			String testId = excelReader.getData(row, 0);
			String testName = excelReader.getData(row, 1);
			String enabledValue = excelReader.getData(row, 2);
			boolean enabled = enabledValue.equalsIgnoreCase("Y") ? true : false;
			String filePath = excelReader.getData(row, 3);
			List<String> arguments = new ArrayList<String>();
			if(noOfColumn>4){
				for(int column = 4;column < noOfColumn;column++){
					arguments.add(excelReader.getData(row, column));					
				}
			}
			ISimpleTest simpleTest = new SimpleTest(testName, testId, enabled, filePath, arguments);
			tests.add(simpleTest);
		}
		return tests;
	}


}
