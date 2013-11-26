package com.test.automation.framework.keywordmodel.reader;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Base Interface for the keyword readers
 * @author  Varun Menon
 *
 */
public interface IKeywordReader {
	
	/**
	 * Should return true or false based on the criteria whether 
	 * the said file is supported or not.
	 * 
	 * @param file - That contains the keywords and respective arguments
	 * @return <code>true</code> or <code>false</code> depending upon whether 
	 * file is supported by the said class or not. 
	 */
	public boolean isSupported(File file);
	
	/**
	 * Parses the particular file and returns a Map containing the keywords and its respective Objects 	
	 * @param file - That contains the keywords and respective arguments
	 * @return Returns a Map containing the keywords and its respective Objects
	 */
	public Map<String, List<Object>> readFile(File file);

}
