package org.imaginea.test.automation.framework.pagemodel;

public class PageException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2467276407146491151L;

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
