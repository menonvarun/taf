package com.test.automation.framework.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DefaultConfig {
	
	Properties configData = null;
	
	public DefaultConfig() {
		this.loadConfig();	
	}
	
	public String getConfigValue(String configuration){
		return configData.getProperty(configuration,"");		
	}
	
	public void setConfigValue(String configuration,String value){
		configData.setProperty(configuration, value);
	}
	
	public void loadConfig() {
		String path = System.getProperty("taf.config");
		configData = new Properties();
		if (path == null) {
			String rootDir = new File("").getAbsolutePath();
			path = rootDir
					+ "/src/test/resources/taf.properties".replace("/",
							File.separator);

		}
		File configFile = new File(path);
		if (configFile.exists() && configFile.isFile()) {
			try {
				configData.load(new FileInputStream(configFile));
			} catch (IOException e) {
				throw new ConfigException(
						"Unable to load default properties file." +
						"Please make sure that the propertis taf.properties is deifned under src/test/resources folder of your project.\n " +
						"Or the path to the properties is set to the property 'taf.config'.");
			} catch (Exception e) {
				throw new ConfigException("Unable to load file because of: "
						+ e.getMessage());
			}
		}else{
			throw new ConfigException(
					"Unable to load default properties file." +
					"Please make sure that the propertis taf.properties is deifned under src/test/resources folder of your project.\n " +
					"Or the path to the properties is set to the property 'taf.config'.");
		}
	}	
	

}
