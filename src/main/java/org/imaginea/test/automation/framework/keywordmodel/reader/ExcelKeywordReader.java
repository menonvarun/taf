package org.imaginea.test.automation.framework.keywordmodel.reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.imaginea.test.automation.framework.util.readers.ExcelReader;

/**
 * <code>ExcelKeywordReader</code> supports reading Excel based keyword files.
 * 
 * @author Varun Menon
 * 
 */
public class ExcelKeywordReader implements IKeywordReader {

	@Override
	public boolean isSupported(File file) {
		boolean supported = false;
		String fileName = file.getName();
		if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx"))
			supported = true;
		return supported;
	}

	@Override
	public List<IKeywordStore> readFile(File file) {
		ExcelReader excelReader = null;
		try {
			excelReader = new ExcelReader(file, 0);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return this.readData(excelReader);
	}

	@Override
	public List<IKeywordStore> readFile(File file, String... args) {
		ExcelReader excelReader = null;
		try {
			excelReader = new ExcelReader(file, args[0]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return this.readData(excelReader);
	}

	private List<IKeywordStore> readData(ExcelReader excelReader) {
		int noOfRows = excelReader.getNoOfRows();
		List<IKeywordStore> keyStores = new ArrayList<IKeywordStore>();

		for (int rowNo = 1; rowNo < noOfRows; rowNo++) {

			String key = excelReader.getData(rowNo, 0);
			List<Object> valueList = new ArrayList<Object>();

			for (int columnNo = 1; columnNo < excelReader.getNoOfColumn(rowNo); columnNo++) {
				String data = excelReader.getData(rowNo, columnNo);
				valueList.add(data);
			}

			IKeywordStore keyStore = new TafKeywordStore();
			keyStore.setKeyword(key);
			keyStore.setArguments(valueList);
			keyStores.add(keyStore);
		}
		return keyStores;
	}

}
