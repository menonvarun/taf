package org.imaginea.test.automation.framework.datadrive;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassParser {
	
	public <T> Object[][] getTestngData(Class<?> type, Map<String, List<String>> dataMap) throws ClassParserException,DataNotAvailableException{
		List<T> dataObject = this.getClassObjectList(type, dataMap);
		int dataObjectSize = dataObject.size();
		Object[][] testngData = new Object[dataObjectSize][1];
		for(int i = 0 ; i < dataObjectSize; i++){
			testngData[i][0] = dataObject.get(i);
		}
		return testngData;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getClassObjectList(Class<?> type, Map<String, List<String>> dataMap) throws ClassParserException,DataNotAvailableException{
		
		boolean dataNotAvailable = false;
		List<String> notAvialableFieldData = new ArrayList<String>();
		List<T> dataObject = new ArrayList<T>();
		try {
			Field[] fields=type.getDeclaredFields();
			List<String> tempKeys = new ArrayList<String>(dataMap.keySet());
			int noOfDataSets = dataMap.get(tempKeys.get(0)).size();
			for (int i = 0; i < noOfDataSets; i++) {
				Object cls = type.newInstance();
				for(Field field: fields){
					if(field.getModifiers()==Modifier.PUBLIC){
					String fieldName = field.getName();
					if(dataMap.containsKey(fieldName)){
						List<String> dataValues = dataMap.get(fieldName);
						String dataValue = dataValues.get(i);
						this.setValue(field, cls, dataValue);						
					} else {
						dataNotAvailable = true;
						notAvialableFieldData.add(fieldName);
					}
					}
				}
				dataObject.add((T)cls);
			}
			if(dataNotAvailable){
				throw new DataNotAvailableException("Data for the fields :"+ notAvialableFieldData +"is not available in the provided map.");				
			}
			
			
		} catch (Exception e) {
			throw new DataNotAvailableException(e.toString());
		}	
		return dataObject;
	}
	
	public void setValue(Field field, Object obj, String value) throws ClassParserException{
		Class<?> fieldType = field.getType();
		try {
			Object data = null;
			if (fieldType == int.class || fieldType == Integer.class) {				
				data = value.length() == 0 ? null : Integer.parseInt(value);
			} else if (fieldType == String.class) {
				data = value.length() == 0 ? null : value;
			} else if (fieldType == Boolean.class || fieldType == boolean.class) {
				data = value.length() == 0 ? null : Boolean.parseBoolean(value);
			} else if (fieldType == float.class || fieldType == Float.class) {
				data = value.length() == 0 ? null : Float.parseFloat(value);
			} else if (fieldType == long.class || fieldType == Long.class) {
				data = value.length() == 0 ? null : Long.parseLong(value);
			} else if (fieldType == double.class || fieldType == Double.class) {
				data = value.length() == 0 ? null : Double.parseDouble(value);
			} else if (fieldType == short.class || fieldType == Short.class) {
				data = value.length() == 0 ? null : Short.parseShort(value);
			} else if (fieldType == char.class || fieldType == Character.class) {
				data = value.length() == 0 ? null : value.charAt(0);
			} else if (fieldType == byte.class || fieldType == Byte.class) {
				data = value.length() == 0 ? null : Byte.valueOf(value);
			} else{
				throw new ClassParserException("Unable to convert the value:" + value + "to required class:" + fieldType);				
			}
			field.set(obj, data);
		} catch (Exception e) {
			throw new ClassParserException("Unable to convert the value:" + value + "to required class:" + fieldType);
		}

	}
	
	@SuppressWarnings("serial")
	public class ClassParserException extends Exception{
		
		public ClassParserException(String message, Throwable cause){
			super(message, cause);
		}
		
		public ClassParserException(String message){
			this(message,null);
		}
		
	}
	
	@SuppressWarnings("serial")
	public class DataNotAvailableException extends Exception{
		public DataNotAvailableException(String message, Throwable e){
			super(message, e);
		}
		
		public DataNotAvailableException(String message){
			super(message);
		}
	}
}
