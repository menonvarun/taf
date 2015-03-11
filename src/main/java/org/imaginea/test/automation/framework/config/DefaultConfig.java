package org.imaginea.test.automation.framework.config;

import org.apache.commons.io.FileUtils;
import org.imaginea.test.automation.framework.config.configfiles.ConfigFileFactory;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Default Config file which loads the base configuraion required for the framework.
 * <p>It looks for the system property <code>"taf.config"</code> for getting the configuration file path.
 * If the property returns <code>null</code>, the file try to load the configuration from the default path
 * <code>"src/test/resources/taf.properties"</code>.
 * <p/>
 * <p> This is a singleton class so incase you need to create an object of this class
 * please call the static method <code>getDefaultConfig()</code>.
 *
 * @author Varun Menon
 */
public class DefaultConfig {
	private static DefaultConfig defaultConfig = null;
	ConfigFileFactory configData = null;
	File configFile = null;

	private DefaultConfig() {
		configFile = getConfigFile();
		configData = new ConfigFileFactory(configFile);
	}

	/**
	 * Returns the singleton object of the <code>DefaultConfig</code> class.
	 *
	 * @return <code>DefaultConfig</code> class object.
	 */
	public static DefaultConfig getDefaultConfig() {
		if (defaultConfig == null) {
			defaultConfig = new DefaultConfig();
		}
		return defaultConfig;
	}

	/**
	 * Returns the value of the passed configuration name from the loaded config file.
	 *
	 * @param configuration - whose value is required from the config file.
	 * @return Configuration value of the said configuration or empty string
	 * if the value is not found.
	 */
	public String getConfigValue(String configuration) {
		return configData.getConfigValue(configuration);
	}

	private File getConfigFile() {
		String path = System.getProperty("taf.config");
		if (path == null) {
			URL propFileURL = this.getClass().getClassLoader().getResource("taf.properties");
			URL jsonFileURL = this.getClass().getClassLoader().getResource("taf.json");
			if (propFileURL == null && jsonFileURL == null) {
				throw new ConfigException(
						"Unable to load default configuration file from path: " + path +
								".Please make sure that the either taf.properties or taf.json is defined under src/test/resources folder of your project.\n " +
								"Or the path to the configuration file is set to the property 'taf.config'.");
			} else if (jsonFileURL != null && propFileURL != null) {
				throw new ConfigException(
						"Unable to load default configuration due to multiple configuration files, Please make sure that the either taf.properties or taf.json is defined under src/test/resources folder of your project.\n");
			} else if (jsonFileURL != null) {
				configFile = FileUtils.toFile(jsonFileURL);
			} else if (propFileURL != null) {
				configFile = FileUtils.toFile(propFileURL);
			}

		} else {
			configFile = new File(path);
		}
		return configFile;
	}
}
