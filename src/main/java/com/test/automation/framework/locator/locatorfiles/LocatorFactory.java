package com.test.automation.framework.locator.locatorfiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.test.automation.framework.locator.LocatorException;

public class LocatorFactory {
	List<LocatorFile> locators = new ArrayList<LocatorFile>();
	
	public LocatorFactory(){
		locators.add(new PropertiesLocatorFile());
	}
	
	public LocatorFile getLocatorFile(File file){
		
		for(LocatorFile lFile : locators){
			if(lFile.isSupported(file)){
				lFile.loadFile(file);
				return lFile;
			}
				
		}
		throw new LocatorException("File: "+file.getName()+" is not currently supported.");
		
	}

}
