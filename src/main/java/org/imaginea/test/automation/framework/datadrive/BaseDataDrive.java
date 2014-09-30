package org.imaginea.test.automation.framework.datadrive;

import java.util.List;
import java.util.Map;

import org.imaginea.test.automation.framework.datadrive.ClassParser.ClassParserException;
import org.imaginea.test.automation.framework.datadrive.ClassParser.DataNotAvailableException;


/**
 * Base Data Drive abstract class providing common methods to get the Parsed data of respective 
 * data files to be used for data drive.
 * @author  Varun Menon
 *
 */
public abstract class BaseDataDrive {
	
	private ClassParser clsParser = new ClassParser();
	
	/**
	 * Method to be implemented by the extending class for providing the parsed Data of the respective data file.
	 * 
	 * @return Map of keys as column names and List of values present in the said column.
	 */
	protected abstract Map<String, List<String>> getData();
	
	/**
	 * Method to be used to parse the data file and to create data objects of the respective passed class.
	 * Mainly used to provide data drive variables for testng data drive methods.
	 * @param type Class type for which the object has to be created for each entry in the row of the data file.
	 * @return {@link Object}[][] containing the objects of the Class - <b>type</b> passed as argument
	 * @throws ClassParserException in case of any issues while parsing the said class
	 * @throws DataNotAvailableException in case no column for the variable present in the class is present in the data file.
	 */
	public <T> Object[][] getTestngData(Class<?> type) throws ClassParserException,DataNotAvailableException{
		Map<String, List<String>> dataMap = this.getData();
		return clsParser.getTestngData(type, dataMap);
	}
	
	/**
	 * Method to be used to parse the data file and to create data objects of the respective passed class.
	 * 
	 * @param type Class type for which the object has to be created for each entry in the row of the data file.
	 * @return {@link List} containing the objects of the Class - <b>type</b> passed as argument
	 * @throws ClassParserException in case of any issues while parsing the said class
	 * @throws DataNotAvailableException in case no column for the variable present in the class is present in the data file.
	 * */
	public <T> List<T> getClassObjectList(Class<?> type) throws ClassParserException,DataNotAvailableException{
		Map<String, List<String>> dataMap = this.getData();
		return clsParser.getClassObjectList(type, dataMap);
	}

}
