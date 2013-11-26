package com.test.automation.framework.keywordmodel.reader;

import java.io.File;
import java.util.List;
import java.util.Map;
/**
 * <code>ExcelKeywordReader</code> supports reading Excel based keyword files.
 * @author  Varun Menon
 *
 */
public class ExcelKeywordReader implements IKeywordReader{

	@Override
	public boolean isSupported(File file) {
		boolean supported = false;
		String fileName = file.getName();
		if(fileName.endsWith(".xls") || fileName.endsWith(".xlsx"))
			supported = true;
		return supported;
	}

	@Override
	public Map<String, List<Object>> readFile(File file) {
		// TODO Auto-generated method stub
		return null;
	}

}
