package com.test.automation.framework.keywordmodel;

public interface IKeyword {
	
	public boolean isSupported(String keyWord, Object[] args);
	
	public void execute(String keyWord, Object[] args);

}
