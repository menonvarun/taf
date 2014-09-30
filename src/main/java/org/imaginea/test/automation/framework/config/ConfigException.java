package org.imaginea.test.automation.framework.config;


public class ConfigException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4836716765409893138L;

	public ConfigException(String message, Throwable e){
		super(message,e);
	}
	
	public ConfigException(String message){
		super(message);
	}

}
