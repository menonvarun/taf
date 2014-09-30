package org.imaginea.test.automation.framework.datadrive;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.imaginea.test.automation.framework.util.readers.ExcelReader;


public class ExcelDataDrive extends BaseDataDrive{
	
	ExcelReader excelReader = null;
	public ExcelDataDrive(String filePath){
		this(new File(filePath));		
	}
	
	public ExcelDataDrive(File file){
		try {
			excelReader = new ExcelReader(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public ExcelDataDrive(String filePath, int sheetNo){
		this(new File(filePath),sheetNo);		
	}
	
	public ExcelDataDrive(File file, int sheetNo){
		try {
			excelReader = new ExcelReader(file, sheetNo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected Map<String, List<String>> getData() {
		HashMap<String, List<String>> data = new HashMap<String, List<String>>();
		int rowCount = excelReader.getNoOfRows();
		int zeroRowColumns = excelReader.getNoOfColumn();
		
		for (int column = 0; column < zeroRowColumns; column++) {
			String key = excelReader.getData(0, column);

			List<String> valueList = new ArrayList<String>();
			
			for (int row = 1; row < rowCount; row++) {
				String value = excelReader.getData(row, column);
				valueList.add(value);
			}
			data.put(key, valueList);
		}		
		// TODO Auto-generated method stub
		return data;
	}

}
