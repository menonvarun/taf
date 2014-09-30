package org.imaginea.test.automation.framework.datadrive;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.imaginea.test.automation.framework.util.readers.CsvReader;


public class CsvDataDrive extends BaseDataDrive{
	
	CsvReader csvReader = null;
	public CsvDataDrive(String filePath){
		this(new File(filePath));		
	}
	
	public CsvDataDrive(File file){
		try {
			csvReader = new CsvReader(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public CsvDataDrive(String filePath, char separator,char quotecharacter){
		this(new File(filePath),separator,quotecharacter);		
	}
	
	public CsvDataDrive(File file, char separator,char quotecharacter){
		try {
			csvReader = new CsvReader(file, separator, quotecharacter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected Map<String, List<String>> getData() {
		
		HashMap<String, List<String>> data = new HashMap<String, List<String>>();
		int rowCount = csvReader.getNoOfRows();
		int zeroRowColumns = csvReader.getNoOfColumn();
		
		for (int column = 0; column < zeroRowColumns; column++) {
			String key = csvReader.getData(0, column);

			List<String> valueList = new ArrayList<String>();
			
			for (int row = 1; row < rowCount; row++) {
				String value = csvReader.getData(row, column);
				valueList.add(value);
			}
			data.put(key, valueList);
		}
		// TODO Auto-generated method stub
		return data;
	}

}
