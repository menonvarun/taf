package org.imaginea.test.automation.framework.keywordmodel;

public class KeywordException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7148208171169997215L;

	public KeywordException(){
		super();
	}
	
	public KeywordException(String message){
		super(message);
	}
	
	public KeywordException(Throwable cause){
		super(cause);
	}
	
	public KeywordException(String message,Throwable cause){
		super(message, cause);
	}

}
