package org.imaginea.test.automation.framework.config.configfiles;

import java.io.File;

/**
 * Created by vijayb on 11-03-2015.
 */
public interface IConfigFile {

	public String getConfigFor(String keyword);

//	public boolean isFileSupported(File file);

	public void loadFile(File file);
}
