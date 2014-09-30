package org.imaginea.test.automation.framework.locator.locatorfiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.imaginea.test.automation.framework.locator.LocatorException;


public class PropertiesLocatorFile implements ILocatorFile{
	
	Properties prop;	
	
	@Override
	public String getLocatorFor(String keyword) {
		String locatorValue = prop.getProperty(keyword);
		if(locatorValue==null)
			throw new LocatorException("Unable to find the locator value for keyword: " + keyword);
		return locatorValue;
	}

	@Override
	public boolean isSupported(File file) {
		boolean supported = false;
		if(file.getName().endsWith(".properties"))
			supported = true;
		return supported;
	}

	@Override
	public void loadFile(File file) {
		this.prop = new Properties();
		try {
			prop.load(new FileInputStream(file));
		} catch (IOException e) {
			throw new LocatorException(e);
		}		
	}

}
