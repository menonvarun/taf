package com.test.automation.framework.locator.locatorfiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.test.automation.framework.locator.LocatorException;

public class LocatorFileFactory {
	List<ILocatorFile> locators = new ArrayList<ILocatorFile>();
	
	public LocatorFileFactory(){
		locators.add(new PropertiesLocatorFile());
	}
	
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
