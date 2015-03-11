package org.imaginea.test.automation.framework.config.configfiles;

import org.imaginea.test.automation.framework.config.ConfigException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by vijayb on 11-03-2015.
 */
public class JsonConfigFile implements IConfigFile {

	JSONObject jsonObject;

	@Override
	public String getConfigFor(String keyword) {
		String configValue = jsonObject.get(keyword).toString();
		if (configValue == null)
			throw new ConfigException("Unable to find the configuration value for keyword: " + keyword);
		return configValue;
	}

	@Override
	public void loadFile(File file) {
		JSONParser jsonParser = new JSONParser();
		try {
			jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));
		} catch (IOException | ParseException e) {
			throw new ConfigException("An Exception has occurred while loading Json Config file", e);
		}
	}

}
