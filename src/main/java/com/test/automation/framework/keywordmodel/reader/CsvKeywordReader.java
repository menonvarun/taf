package com.test.automation.framework.keywordmodel.reader;

import java.io.File;
import java.util.List;
import java.util.Map;
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
	public Map<String, List<Object>> readFile(File file) {
		// TODO Auto-generated method stub
		return null;
	}

}
