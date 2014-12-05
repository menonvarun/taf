package org.imaginea.test.automation.framework.datadrive;

import org.imaginea.test.automation.framework.TafTestClass;
import org.imaginea.test.automation.framework.datadrive.ClassParser.ClassParserException;
import org.imaginea.test.automation.framework.datadrive.ClassParser.DataNotAvailableException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClassParserTest extends TafTestClass {
	
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
			Assert.assertEquals(test.size(),3);
            TestDataClass testObject = test.get(0);
            Assert.assertEquals(testObject.stringValue,"testing1");
            Assert.assertEquals(testObject.integerValue,101);
            Assert.assertEquals(testObject.byteValue,101);
            Assert.assertEquals(testObject.floatValue,10.201f);
		} catch (ClassParserException e) {
            Assert.fail("Class parser test failed",e);
		} catch (DataNotAvailableException e) {
            Assert.fail("Class parser test failed",e);
		}
	}
	
	@Test
	public void csvDataTest(){
		CsvDataDrive csD = new CsvDataDrive(getFilePathFromResource("DataDriveTest.csv"));
		try {
            List<TestDataClass> test = csD.getClassObjectList(TestDataClass.class);
            Assert.assertEquals(test.size(),4);
            TestDataClass testObject = test.get(0);
            Assert.assertEquals(testObject.stringValue,"testing1");
            Assert.assertEquals(testObject.integerValue,1);
            Assert.assertEquals(testObject.byteValue,100);
            Assert.assertEquals(testObject.floatValue,10.201f);
		} catch (ClassParserException e) {
            Assert.fail("parsing a csv file failed",e);
		} catch (DataNotAvailableException e) {
            Assert.fail("parsing a csv file failed",e);
		}
	}
	
	@Test
	public void excelDataTest(){
		ExcelDataDrive esD = new ExcelDataDrive(getFilePathFromResource("DataDriveTest.xls"));
        try {
            List<TestDataClass> test = esD.getClassObjectList(TestDataClass.class);
            Assert.assertEquals(test.size(),4);
            TestDataClass testObject = test.get(0);
            Assert.assertEquals(testObject.stringValue,"testing1");
            Assert.assertEquals(testObject.integerValue,1);
            Assert.assertEquals(testObject.byteValue,100);
            Assert.assertEquals(testObject.floatValue,10.201f);
        } catch (ClassParserException e) {
            Assert.fail("parsing a xls file failed",e);
        } catch (DataNotAvailableException e) {
            Assert.fail("parsing a xls file failed",e);
        }
	}
	
	@Test
	public void verifyTestNgDataReturnedByTheCSVDataDriveUtility() throws ClassParserException, DataNotAvailableException{
		CsvDataDrive csD = new CsvDataDrive(getFilePathFromResource("DataDriveTest.csv"));
		Object[][] returnData = csD.getTestngData(TestDataClass.class);
        Assert.assertEquals(returnData.length,4);
        Object[] object = returnData[0];
        Assert.assertEquals(object.length,1);
		Assert.assertTrue(object[0]  instanceof TestDataClass);
        TestDataClass testObject = (TestDataClass)object[0];
        Assert.assertEquals(testObject.stringValue,"testing1");
        Assert.assertEquals(testObject.integerValue,1);
        Assert.assertEquals(testObject.byteValue,100);
        Assert.assertEquals(testObject.floatValue,10.201f);
	}
	
	@Test
	public void verifyTestNgDataReturnedByTheExcelDataDriveUtility() throws ClassParserException, DataNotAvailableException{
		ExcelDataDrive csD = new ExcelDataDrive(getFilePathFromResource("DataDriveTest.xls"));
        Object[][] returnData = csD.getTestngData(TestDataClass.class);
        Object[] object = returnData[0];
        Assert.assertEquals(object.length,1);
        Assert.assertTrue(object[0]  instanceof TestDataClass);
        TestDataClass testObject = (TestDataClass)object[0];
        Assert.assertEquals(testObject.stringValue,"testing1");
        Assert.assertEquals(testObject.integerValue,1);
        Assert.assertEquals(testObject.byteValue,100);
        Assert.assertEquals(testObject.floatValue,10.201f);
	}
}
