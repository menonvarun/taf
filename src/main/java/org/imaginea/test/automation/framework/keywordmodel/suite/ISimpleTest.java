package org.imaginea.test.automation.framework.keywordmodel.suite;

import java.util.List;
/**
 * Interface to represent a single test in the suite file.
 * @author  Varun Menon
 *
 */
public interface ISimpleTest {
	/**
	 * Get the test name that is set i the suite file
	 * @return
	 */
	public String getTestName();
	
	/**
	 * Set the test name for the said test	
	 * @param testName
	 */
	public void setTestName(String testName);
	
	/**
	 * Gets the test-id mentioned in the suite file for the specified the test
	 * @return
	 */
	public String getTestId();
	
	/**
	 * Sets the test-id mentioned in the suite file for the specified the test 
	 */
	public void setTestId(String testId);
	
	/**
	 * Return true or false based on the condition whether the test is enabled for execution or not.
	 * @return true or false
	 */
	public boolean isEnabled();
	
	/**
	 * Sets the test to enabled or disabled state.
	 * @param enabled boolean value true or false
	 */
	public void setEnabled(boolean enabled);
	
	/**
	 * Gets the test file path for the test
	 * @return Path of the file which contains the keywords for the said test.
	 */
	public String getTestFilePath();

	/**
	 * Sets the file path of the said test.
	 * @param testFilePath
	 */
	public void setTestFilePath(String testFilePath);
	
	/**
	 * Returns any extra arguments that is provided in the suite file for the specified test as List
	 * These arguments are mainly related the path of suite file, sheetname(in case of excel), delimiters (in case of csv), separators(in case of csv).  
	 * @return List<String> of the extra arguments provided in the suite file for the said test. 
	 * This list will be empty if no extra arguments have been provided.
	 */
	public List<String> getExtraArguments();
	
	/**
	 * Sets any extra argument that may be required for the said test.
	 * These arguments are mainly related the path of suite file, sheetname(in case of excel), delimiters (in case of csv), separators(in case of csv).
	 * @param arguments List<String> of the arguments that needs to be set.
	 */
	public void setExtraArguments(List<String> arguments);
	

}
