package org.imaginea.test.automation.framework.keywordmodel.reader;

public class KeywordReaderException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1482142907389535385L;

	public KeywordReaderException(){
		super();
	}
	
	public KeywordReaderException(String message){
		super(message);
	}
	
	public KeywordReaderException(Throwable cause){
		super(cause);
	}
	
	public KeywordReaderException(String message,Throwable cause){
		super(message, cause);
	}

}
