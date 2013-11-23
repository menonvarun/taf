package com.test.automation.framework.pagemodel.locator;

import java.io.File;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.test.automation.framework.locator.Locator;

public class GoogleLocator extends Locator{
	
	public GoogleLocator() {
		super(new File("H:/opt/bitbucket/testautomationframework/src/test/resources/google.properties"));
	}
	
	@FindBy(how=How.CSS,using="search_box")
	WebElement searchField;
	
	@FindBy(how = How.CSS,using="submit_button")
	WebElement submitButton;
	
	@FindBy(how = How.CSS,using="search_result")
	List<WebElement> searchResult;
	
	@FindBy(how = How.CSS,using="search_result_text")
	WebElement searchResultText;

}
