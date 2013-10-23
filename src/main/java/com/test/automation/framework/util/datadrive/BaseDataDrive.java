package com.test.automation.framework.util.datadrive;

import java.util.List;
import java.util.Map;

import com.test.automation.framework.util.datadrive.ClassParser.ClassParserException;
import com.test.automation.framework.util.datadrive.ClassParser.DataNotAvailableException;

public abstract class BaseDataDrive {
	
	private ClassParser clsParser = new ClassParser();
	protected abstract Map<String, List<String>> getData();
	
	public <T> Object[][] getTestngData(Class<?> type) throws ClassParserException,DataNotAvailableException{
		Map<String, List<String>> dataMap = this.getData();
		return clsParser.getTestngData(type, dataMap);
	}
	
	public <T> List<T> getClassObjectList(Class<?> type) throws ClassParserException,DataNotAvailableException{
		Map<String, List<String>> dataMap = this.getData();
		return clsParser.getClassObjectList(type, dataMap);
	}

}
