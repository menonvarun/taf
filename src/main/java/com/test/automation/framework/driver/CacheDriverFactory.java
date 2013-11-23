package com.test.automation.framework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import com.test.automation.framework.config.DefaultConfig;

public class CacheDriverFactory {
	
	private static DefaultConfig config;
	private boolean threadBasedDriver = true;
	private static IDriverFactory driverFactory = null;
	private static IDriverProvider driverProvider= null;
	
	public CacheDriverFactory(){
		CacheDriverFactory.config  = DefaultConfig.getDefaultConfig();		
	}
	
	public CacheDriverFactory(DefaultConfig config){
		CacheDriverFactory.config = config;
	}
	
	public boolean getThreadBasedDriver(){
		return this.threadBasedDriver;
	}
	
	public void setThreadBasedDriver(boolean value){
		this.threadBasedDriver = value;
	}	
	
	public WebDriver getDriver(){
		IDriverProvider currentDriverProvider = getDriverProvider();
		IDriverFactory driverFactory = getDriverFactory();
		WebDriver driver = driverFactory.getCurrentDriver(currentDriverProvider);
		return driver;
	}
	
	public WebDriver clearDriver(){
		IDriverFactory driverFactory = getDriverFactory();
		WebDriver driver = driverFactory.clearCurrentDriver();
		return driver;		
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
