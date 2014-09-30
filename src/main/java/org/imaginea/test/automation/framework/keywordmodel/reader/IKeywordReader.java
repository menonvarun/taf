package org.imaginea.test.automation.framework.keywordmodel.reader;

import java.io.File;
import java.util.List;

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
	 * Parses the particular file and returns a List containing the {@link IKeywordStore IKeywordStore} object 	
	 * @param file - That contains the keywords and respective arguments
	 * @return Returns a {@link List List} containing the <code>IKeywordStore</code> object.
	 */
	public List<IKeywordStore> readFile(File file);
	
	/**
	 * Parses the particular file and returns a List containing the {@link IKeywordStore IKeywordStore} object 	
	 * @param file - That contains the keywords and respective arguments
	 * @param args - Any extra arguments that may be passed as part of the reading task.
	 * @return Returns a {@link List List} containing the {@link IKeywordStore IKeywordStore} object.
	 */
	public List<IKeywordStore> readFile(File file, String... args);

}
