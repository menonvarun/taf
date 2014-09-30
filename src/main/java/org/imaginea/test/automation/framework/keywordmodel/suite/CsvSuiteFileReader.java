package org.imaginea.test.automation.framework.keywordmodel.suite;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.imaginea.test.automation.framework.util.readers.CsvReader;


public class CsvSuiteFileReader implements ISuiteFileReader{

	@Override
	public boolean isSupported(File file) {
		boolean supported = false;
		if(file.getName().endsWith(".csv"))
			supported = true;
		return supported;
	}

	@Override
	public List<ISimpleTest> read(File file, List<String> arguments) {
		CsvReader csvReader;
		try{
			if(arguments.size()>2)
				csvReader = new CsvReader(file, arguments.get(0), arguments.get(1));
			else
				csvReader = new CsvReader(file);
		} catch(IOException e){
			throw new RuntimeException(e);
		}
		return this.getTests(csvReader);
	}
	
	private List<ISimpleTest> getTests(CsvReader csvReader){
		List<ISimpleTest> tests = new ArrayList<ISimpleTest>();
		int noOfTestRows = csvReader.getNoOfRows();
		for(int row = 1; row < noOfTestRows; row++){
			int noOfColumn = csvReader.getNoOfColumn(row);
			if(noOfColumn < 4){
			 throw new RuntimeException("Test suite file fhsoul contain a minimum of 4 values for each test."
					 +"These values ar Test-id, Test-Name, Enabled/Disabled and Filepath");				
			}
			String testId = csvReader.getData(row, 0);
			String testName = csvReader.getData(row, 1);
			String enabledValue = csvReader.getData(row, 2);
			boolean enabled = enabledValue.equalsIgnoreCase("Y") ? true : false;
			String filePath = csvReader.getData(row, 3);
			List<String> arguments = new ArrayList<String>();
			
			if(noOfColumn>4){
				for(int column = 4;column < noOfColumn;column++){
					arguments.add(csvReader.getData(row, column));					
				}
			}
			ISimpleTest simpleTest = new SimpleTest(testName, testId, enabled, filePath, arguments);
			tests.add(simpleTest);
		}
		return tests;
	}

}
