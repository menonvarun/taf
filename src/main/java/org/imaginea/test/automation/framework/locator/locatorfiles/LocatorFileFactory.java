package org.imaginea.test.automation.framework.locator.locatorfiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.imaginea.test.automation.framework.locator.LocatorException;


/**
 * Locator file factory for identifying the supported locator storage files/types
 * and parsing the respective {@link ILocatorFile} implementation.
 * 
 * @author  Varun Menon
 *
 */
public class LocatorFileFactory {
	List<ILocatorFile> locators = new ArrayList<ILocatorFile>();
	
	public LocatorFileFactory(){
		locators.add(new PropertiesLocatorFile());
	}
	
	/**
	 * Identifies the supported {@link ILocatorFile} implementation for the passed {@link File} object of the locator file passed. 
	 * @param file {@link File} object of the locator file
	 * @return {@link ILocatorFile} object of the supported implementing locator class if supported else fails with {@link LocatorException} 
	 * 
	 */
	public ILocatorFile getLocatorFile(File file){
		
		for(ILocatorFile lFile : locators){
			if(lFile.isSupported(file)){
				lFile.loadFile(file);
				return lFile;
			}
				
		}
		throw new LocatorException("File: "+file.getName()+" is not currently supported.");		
	}

}
