package org.imaginea.test.automation.framework.keywordmodel.reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.imaginea.test.automation.framework.util.readers.CsvReader;
/**
 * <code>CsvKeywordReader</code> supports reading CSV based keyword files.
 * @author  Varun Menon
 *
 */
public class CsvKeywordReader implements IKeywordReader{

	@Override
	public boolean isSupported(File file) {
		boolean supported = false;
		if(file.getName().endsWith(".csv"))
			supported = true;
		return supported;
	}

	@Override
	public List<IKeywordStore> readFile(File file) {
		CsvReader csvReader = null;
		try {
			csvReader = new CsvReader(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return this.readData(csvReader);
	}

	@Override
	public List<IKeywordStore> readFile(File file, String... args) {
		CsvReader csvReader = null;
		try {
			csvReader = new CsvReader(file,args[0],args[1]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return this.readData(csvReader);
	}
	
	private List<IKeywordStore> readData(CsvReader csvReader) {
		int noOfRows = csvReader.getNoOfRows();
		List<IKeywordStore> keyStores = new ArrayList<IKeywordStore>();

		for (int rowNo = 1; rowNo < noOfRows; rowNo++) {

			String key = csvReader.getData(rowNo, 0);
			List<Object> valueList = new ArrayList<Object>();

			for (int columnNo = 1; columnNo < csvReader.getNoOfColumn(rowNo); columnNo++) {
				String data = csvReader.getData(rowNo, columnNo);
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
