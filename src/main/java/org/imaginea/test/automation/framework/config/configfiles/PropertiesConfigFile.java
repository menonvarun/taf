package org.imaginea.test.automation.framework.config.configfiles;

import org.imaginea.test.automation.framework.config.ConfigException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by vijayb on 11-03-2015.
 */
public class PropertiesConfigFile implements IConfigFile {
	Properties prop;

	@Override
	public String getConfigFor(String keyword) {
		String configValue = prop.getProperty(keyword);
		if (configValue == null)
			throw new ConfigException("Unable to find the configuration value for keyword: " + keyword);
		return configValue;
	}

	@Override
	public void loadFile(File file) {
		this.prop = new Properties();
		try {
			prop.load(new FileInputStream(file));
		} catch (IOException e) {
			throw new ConfigException("An Exception has occurred while loading Properties Config file", e);
		}
	}
}

