package org.imaginea.test.automation.framework.config.configfiles;

import java.io.File;

/**
 * Created by vijayb on 11-03-2015.
 */
public class ConfigFileFactory {
	IConfigFile defaultConfig;

	public ConfigFileFactory(File file) {
		if (file.getName().endsWith(".properties")) {
			PropertiesConfigFile propertiesConfigFile = new PropertiesConfigFile();
			propertiesConfigFile.loadFile(file);
			defaultConfig = (IConfigFile) propertiesConfigFile.prop;
		} else if (file.getName().endsWith(".json")) {
			JsonConfigFile jsonConfigFile = new JsonConfigFile();
			jsonConfigFile.loadFile(file);
			defaultConfig = (IConfigFile) jsonConfigFile.jsonObject;
		}
	}

	public String getConfigValue(String configuration) {
		return defaultConfig.getConfigFor(configuration);
	}
}
