package com.test.automation.framework.locator;

public class LocatorException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocatorException() {
		super("An unknown Locator Exception has been thrown");
	}
	
	public LocatorException(String message){
		super(message);		
	}
	
	public LocatorException(Throwable cause){
		super(cause);
	}

}
