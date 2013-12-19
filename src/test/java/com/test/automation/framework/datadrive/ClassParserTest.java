package com.test.automation.framework.datadrive;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.framework.datadrive.ClassParser;
import com.test.automation.framework.datadrive.CsvDataDrive;
import com.test.automation.framework.datadrive.ExcelDataDrive;
import com.test.automation.framework.datadrive.ClassParser.ClassParserException;
import com.test.automation.framework.datadrive.ClassParser.DataNotAvailableException;

public class ClassParserTest {
	
	@Test
	public void verifyClassParserListMethod(){
		
		ClassParser clsP = new ClassParser();
		List<String> data1 = Arrays.asList(new String[]{"testing1","testing2","testing3"});
		List<String> data2 = Arrays.asList(new String[]{"101","102","103"});
		List<String> data3 = Arrays.asList(new String[]{"10.201f","10.202f","10.203f"});
		Map<String, List<String>> testData = new HashMap<String, List<String>>();
		testData.put("stringValue", data1);
		testData.put("integerValue", data2);
		testData.put("byteValue", data2);
		testData.put("floatValue", data3);
		
		try {
			List<TestDataClass> test = clsP.getClassObjectList(TestDataClass.class, testData);
			System.out.println(test);
		} catch (ClassParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataNotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void csvDataTest(){
		CsvDataDrive csD = new CsvDataDrive("H://opt//bitbucket//testautomationframework//src//test//resources//DataDriveTest.csv");
		try {
			System.out.println(csD.getClassObjectList(TestDataClass.class));
		} catch (ClassParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataNotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void excelDataTest(){
		ExcelDataDrive esD = new ExcelDataDrive("H://opt//bitbucket//testautomationframework//src//test//resources//DataDriveTest.xls");
		try {
			System.out.println(esD.getClassObjectList(TestDataClass.class));
		} catch (ClassParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataNotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@DataProvider(name="csvdata")
	public Object[][] getCsvData() throws ClassParserException, DataNotAvailableException{
		CsvDataDrive csD = new CsvDataDrive("H://opt//bitbucket//testautomationframework//src//test//resources//DataDriveTest.csv");
		return csD.getTestngData(TestDataClass.class);
		
	}
	
	@DataProvider(name="exceldata")
	public Object[][] getExcelData() throws ClassParserException, DataNotAvailableException{
		ExcelDataDrive csD = new ExcelDataDrive("H://opt//bitbucket//testautomationframework//src//test//resources//DataDriveTest.xls");
		return csD.getTestngData(TestDataClass.class);
		
	}
	
	@Test(dataProvider="csvdata")
	public void csvdataDrivenTest(TestDataClass td){
		System.out.println(td);
	}
	
	@Test(dataProvider="exceldata")
	public void exceldataDrivenTest(TestDataClass td){
		System.out.println(td);
	}

}
