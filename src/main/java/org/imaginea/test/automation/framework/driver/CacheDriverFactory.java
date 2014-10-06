package org.imaginea.test.automation.framework.driver;

import org.imaginea.test.automation.framework.config.DefaultConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;


/**
 * <code>CacheDriverFactory</code> provides a utility for creating  and maintaining Selenium <code>WebDriver</code> instance.
 * <h2>Driver Creation</h2>
 * <p>The framework supports an in-build implementation of driver creation as well as provide the flexibility to allow user 
 * to provide his own driver implementation.</p>
 * 
 * In-build driver supports <i>firefox</i>,<i>ie</i>,<i>chrome</i>,<i>htmlunit</i>. Thes values can be set to the property <b><i>test.name</i></b>
 *  inside <b><i>taf.properties</i></b> file.
 * For user defined driver, User have to implement a class implementing the {@link IDriverProvider} interface and add the path to the said class onto
 * the property <b><i>userdefined.driverclass</i></b> inside <b><i>taf.properties</i></b> file.
 * 
 * <h2>Driver Management</h2>
 * <p> The class provides two different implementation of driver factory:
 * <ol>
 * <li>Simple Driver Instance - In this driver instance management mode the driver object will be same across all the threads.</li>
 * <li>Threaded Driver Instance - In this driver instance management mode the driver will be different for each thread instance 
 * that is getting executed.</li>
 * </ol>
 * 
 * By default "Simple Driver Instance" management is chosen by the framework. But in case you need a "Threaded Driver Instance" 
 * management you can configure it by setting property <b><i>thread.based.driver</i></b> to <b>true</b> inside <b><i>taf.properties</i></b>
 * file.
 *   
 * @author  Varun Menon
 *
 */
public class CacheDriverFactory {
	
	private static DefaultConfig config;
	private boolean threadBasedDriver = true;
	private static IDriverFactory driverFactory = null;
	private static IDriverProvider driverProvider= null;
	
	/**
	 * Default Constructor which will create the CacheDriverFactory object and uses the default config class object.
	 */
	public CacheDriverFactory(){
		CacheDriverFactory.config  = DefaultConfig.getDefaultConfig();		
	}
	
	/**
	 * Default Constructor which will create the CacheDriverFactory object using the {@link DefaultConfig} class object provided.
	 * @param config {@link DefaultConfig} class object that needs to be used by this class.
	 */
	public CacheDriverFactory(DefaultConfig config){
		CacheDriverFactory.config = config;
	}
	
	/**
	 * Return true or false based on the condition whether the driver object is thread based or not
	 * @return true or false
	 */
	public boolean getThreadBasedDriver(){
		return this.threadBasedDriver;
	}
	
	/**
	 * Sets the thread based driver configuration with the given value.
	 * @param value
	 */
	public void setThreadBasedDriver(boolean value){
		this.threadBasedDriver = value;
	}	
	
	/**
	 * Create or return the current driver based on the configuration provided inside the <b>taf.properties</b> file.
	 * @return {@link WebDriver} object
	 */
	public WebDriver getDriver(){
		IDriverProvider currentDriverProvider = getDriverProvider();
		IDriverFactory driverFactory = getDriverFactory();
		WebDriver driver = driverFactory.getCurrentDriver(currentDriverProvider);
		return driver;
	}
	
	/**
	 * Clears the current driver from the factory and return the old driver back
	 * @return {@link WebDriver} object
	 */
	public WebDriver clearDriver(){
		IDriverFactory driverFactory = getDriverFactory();
		WebDriver driver = driverFactory.clearCurrentDriver();
		return driver;		
	}
	
	/**
	 * Clears the current driver from the factory and then quits the said driver object
	 */
	public static void clearCacheAndQuitDriver(){
		if(CacheDriverFactory.driverFactory!=null){
			WebDriver driver = CacheDriverFactory.driverFactory.clearCurrentDriver();
			if(driver != null)
				driver.quit();
		}
	}
	
	
	private IDriverFactory getDriverFactory(){
		if(CacheDriverFactory.driverFactory==null){
			String threadBasedDriverValue = config.getConfigValue("thread.based.driver");
			if(Boolean.parseBoolean(threadBasedDriverValue)){
				CacheDriverFactory.driverFactory = new ThreadedDriver();
			} else
				CacheDriverFactory.driverFactory =  new SimpleDriver();
		}
		return CacheDriverFactory.driverFactory;
		
	}
	
	private static class ThreadedDriver implements IDriverFactory{
		private static final ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<WebDriver>();
		
		@Override
		synchronized public WebDriver getCurrentDriver(IDriverProvider driverProvider) {
			WebDriver driver = threadedDriver.get();
			if(driver==null){
				driver = driverProvider.getDriver();
				threadedDriver.set(driver);
				addShutdownHookToDriver(driver);
			}			
			return driver;
		}

		@Override
		synchronized public WebDriver clearCurrentDriver() {
			WebDriver oldDriver = threadedDriver.get();
			threadedDriver.set(null);
			return oldDriver;			
		}
	}
	
	private static class SimpleDriver implements IDriverFactory{
		
		WebDriver driver = null;

		@Override
		public WebDriver getCurrentDriver(IDriverProvider driverProvider) {
			if(driver==null){
				driver = driverProvider.getDriver();
				addShutdownHookToDriver(driver);
			}
			return driver;
		}

		@Override
		public WebDriver clearCurrentDriver() {
			WebDriver oldDriver = driver;
			driver = null;
			return oldDriver;
		}
	}
	
	/**
	 * Returns the Driver Provider class object of the class that has been provided by the user for Driver creation
	 * @return {@link IDriverProvider} object or null if no class have been defined.
	 */
	public IDriverProvider getUserDefinedDriverProvider(){
		String userDefinedClassName =config.getConfigValue("userdefined.driverclass");
		IDriverProvider userDefinedClassObject = null;
		if(!userDefinedClassName.contentEquals("")){
			ClassLoader clsLoader = this.getClass().getClassLoader();
			try {
				Class<?> userDefinedClass = clsLoader.loadClass(userDefinedClassName);
				if(IDriverProvider.class.isAssignableFrom(userDefinedClass)){
					userDefinedClassObject = (IDriverProvider)userDefinedClass.newInstance();										
				}else{
					throw new RuntimeException("The defined userdefined driver class does not extend the interface 'IDriverProvider'.");
				}
			} catch (IllegalAccessException|InstantiationException|ClassNotFoundException e) {
				throw new RuntimeException(e);			
			} 
		}
		return userDefinedClassObject;
	}
	
	/**
	 * Returns the driver provider to be used for the execution. In case a user defined driver provider is available the 
	 * said class object is returned else it will return the {@link InbuiltDriverProvider} class instance.  
	 * @return {@link IDriverProvider} object of the driver provider class.
	 */
	public IDriverProvider getDriverProvider(){
		if(CacheDriverFactory.driverProvider == null){
			IDriverProvider driverProvider = getUserDefinedDriverProvider();
			if(driverProvider == null){
				driverProvider = new InbuiltDriverProvider(); 
			}
			CacheDriverFactory.driverProvider = driverProvider;
		}
		
		return CacheDriverFactory.driverProvider;
		
	}
	
	private synchronized static void addShutdownHookToDriver(WebDriver driver){
		CacheDriverFactory driverfactory = new CacheDriverFactory();
		Runtime.getRuntime().addShutdownHook(driverfactory.new DriverShutdownHook(driver));
	}
	
	private class DriverShutdownHook extends Thread{
		WebDriver driver;
		public DriverShutdownHook(WebDriver driver){
			this.driver = driver;
		}
		@Override
		public void run(){
			try{
				driver.quit();
			}catch(UnreachableBrowserException e){
				
			}
		}
		
	}

	

}
