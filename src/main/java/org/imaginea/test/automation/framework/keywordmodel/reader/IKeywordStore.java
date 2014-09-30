package org.imaginea.test.automation.framework.keywordmodel.reader;

import java.util.List;

/**
 * Basic interface to store the keyword and its arguments.
 * @author  Varun Menon
 *
 */
public interface IKeywordStore {
	
	/**
	 * Sets the keyword name
	 * @param keyword - The keyword to be stored
	 */
	public void setKeyword(String keyword);
	
	/**
	 * Get the stored keyword name
	 * @return Actual keyword stored or an empty string if no value is stored
	 */
	public String getKeyword();
	
	/**
	 * Sets the keyword arguments
	 * @param args - Keyword arguments as {@link List List} of {@link Object Object}
	 */
	public void setArguments(List<Object> args);
	
	/**
	 * Returns the stored list of arguments as {@link List List} of {@link Object Object}.
	 * @return The stored list of arguments or an empty list is no arguments is being stored.
	 */
	public List<Object> getArguments();	

}
