package org.imaginea.test.automation.framework.keywordmodel.reader;

import java.util.ArrayList;
import java.util.List;

/**
 * TAF implementation of the interface <code>IKeywordStore</code>
 * @author  Varun Menon
 *
 */
public class TafKeywordStore implements IKeywordStore{
	private String keyword = "";
	private List<Object> arguments = new ArrayList<Object>();
	
	@Override
	public void setKeyword(String keyword) {
		this.keyword = keyword;		
	}

	@Override
	public String getKeyword() {
		return this.keyword;
	}

	@Override
	public void setArguments(List<Object> args) {
		this.arguments = args;
	}

	@Override
	public List<Object> getArguments() {
		return this.arguments;
	}

}
