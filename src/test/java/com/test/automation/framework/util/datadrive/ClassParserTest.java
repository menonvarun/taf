package com.test.automation.framework.util.datadrive;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.test.automation.framework.util.datadrive.ClassParser.ClassParserException;
import com.test.automation.framework.util.datadrive.ClassParser.DataNotAvailableException;

public class ClassParserTest {
	
	@Test
	public void verifyClassParserListMethod(){
		
		ClassParser clsP = new ClassParser();
		List<String> data1 = Arrays.asList(new String[]{"testing1","testing2","testing3"});
		List<String> data2 = Arrays.asList(new String[]{"101","102","103"});
		List<String> data3 = Arrays.asList(new String[]{"10.201f","10.202f","10.203f"});
		Map<String, List<String>> testData = new HashMap<String, List<String>>();
		testData.put("integerValue", data1);
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

}
