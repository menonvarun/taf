package com.test.automation.framework.keyword.google;

import java.io.File;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.test.automation.framework.locator.Locator;

public class GoogleKeywordLocator extends Locator{
	
	public GoogleKeywordLocator() {
		super(new File("src/test/resources/google.properties"));
	}
	
	@FindBy(how=How.CSS,using="search_box")
	public WebElement searchField;
	
	@FindBy(how = How.CSS,using="submit_button")
	public WebElement submitButton;
	
	@FindBy(how = How.CSS,using="search_result")
	public List<WebElement> searchResult;
	
	@FindBy(how = How.CSS,using="search_result_text")
	public WebElement searchResultText;

}
