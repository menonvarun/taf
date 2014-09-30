package org.imaginea.test.automation.framework.locator.locatorfiles;

import java.io.File;
/**
 * Base Locator file interface for referring to the locator file 
 * @author  Varun Menon
 *
 */

public interface ILocatorFile {
	
	/**
	 * Gets the respective locator for the keyword from the locator file.
	 * @param keyword For which the locator have to be fetched
	 * @return Respective locator for "keyword" from the file. May return <b>null</b> or
	 * an empty string depending upon the implementation in the implementing class.
	 */
	public String getLocatorFor(String keyword);
	
	/**
	 * Utility to know whether the said type of locator storing file is supported 
	 * by the particular implementation or not.
	 * @param file File object of the file which have to checked whether its supported or not.
	 * @return <b>true</b> or <b>false</b> depending upon the condition 
	 * whether the file is supported or not.
	 */
	public boolean isSupported(File file);
	
	/**
	 * Load the respective file for parsing if the implementation class supports 
	 * the particular file.
	 * @param file File object of the locator file that have to be loaded.
	 */
	public void loadFile(File file);

}
