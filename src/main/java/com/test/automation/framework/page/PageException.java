package com.test.automation.framework.page;

public class PageException extends RuntimeException{
	
	public PageException(){
		super();
	}
	
	public PageException(String message){
		super(message);
	}
	
	public PageException(Throwable cause){
		super(cause);
	}
	
	public PageException(String message,Throwable cause){
		super(message, cause);
	}

}
