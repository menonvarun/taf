package org.imaginea.test.automation.framework.driver;

import org.openqa.selenium.WebDriver;

/**
 * This interface allow users to provide their own implementation to be used for driver creation.
 * After implementing this interface please set the path of your class to the property <b><i>userdefined.driverclass</i></b> 
 * inside <b><i>taf.properties</i></b> file.
 * 
 * @author  Varun Menon
 *
 */
public interface IDriverProvider {
	
	/**
	 * Creates and returns the driver object at runtime.
	 * 
	 * <p>
	 * <b>Note:</b> While implemnting the interface create and configure your driver inside this method and return it.
	 * @return {@link WebDriver}
	 */
	public WebDriver getDriver();

}
