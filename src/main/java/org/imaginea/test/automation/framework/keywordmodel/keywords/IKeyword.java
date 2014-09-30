package org.imaginea.test.automation.framework.keywordmodel.keywords;

/**
 * Base interface for the keyword implementation classes.
 * @author  Varun Menon
 *
 */
public interface IKeyword {
	
	/**
	 * Verifies whether the said keyword with the arguments is supported by the implementing class or not.
	 * @param keyword Keyword that needs to be executed
	 * @param args Arguments that needs to be used to execute the said keyword
	 * @return <code>true</code> or <code>false</code> depending upon the condition whether the "keyword" 
	 * with specified no. of arguments is supported or not.
	 */
	public boolean isSupported(String keyword, Object[] args);
	
	/**
	 * Executes the specified keyword with specified given arguments.
	 * @param keyword Keyword to be executed.
	 * @param args Arguments to be used for the keyword execution.
	 */
	public void execute(String keyword, Object[] args);

}
