package org.imaginea.test.automation.framework.keywordmodel.suite;

import java.io.File;
import java.util.List;


/**
 * An interface to represent the Suite file reader for reading and parsing the test-suite files.
 * @author  Varun Menon
 *
 */
public interface ISuiteFileReader {
	
	/**
	 * Methods to identify whether the said suite file is supported by the implementing class or not.
	 * @param file - File object of the test-suite file.
	 * @return true or false based on whether file is supported or not.
	 */
	public boolean isSupported(File file);
	
	/**
	 * Method to parse and return the test instances of the test present inside the said suite file.
	 * @param file {@link File} object of the test-suite file.
	 * @param arguments - {@link List} of {@link String} required to read or parse the said test-suite file.
	 * These arguments are mainly related to the path of suite file, sheetname(in case of excel), delimiters (in case of csv), separators(in case of csv).
	 * @return {@link List} of {@link ISimpleTest} after parsing the test-suite file.
	 */
	public List<ISimpleTest> read(File file, List<String> arguments);

}
