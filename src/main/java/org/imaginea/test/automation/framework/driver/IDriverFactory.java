package org.imaginea.test.automation.framework.driver;

import org.openqa.selenium.WebDriver;

/**
 * Driver Factory interface to be used for driver management.
 * <p>Currently custom implementation is not supported by implementing this interface. But going forward it may be supported.
 * @author  Varun Menon
 *
 */
public interface IDriverFactory {
	
	public WebDriver getCurrentDriver(IDriverProvider driverProvider);
	public WebDriver clearCurrentDriver();

}
