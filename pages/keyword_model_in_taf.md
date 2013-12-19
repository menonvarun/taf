---
layout: default
title: Keyword Model
permalink: /keyword_model_in_taf.html
heading: Keyword Model
basicnav: true
---

#Plug-n-Play Keyword Driven Framework

TAF have an in-build support for keyword driven automation framework. You can easily write your keyword driven tests in csv and excel format using TAF.
Just define your keyword as public methods in a class by extending the [_**KeywordBase**_]() class or the [_**IKeyword**_]() interface. Once your keyword class has been defined just add the path of the class to the **"listener"** property in the **taf.properties** file.

TAF will automatically load the class at runtime on execution and considers all its public methods as keywrords for your test files. TAF also provides common utility to read your suite files and test files using a common format commonly used across the industry. 
Look at the following example of the google search page to get more idea on the same.

Following is my Keyword definition file:

<pre class="brush: java;">
public class GoogleKeyword extends KeywordBase{
	
	private WebDriver driver;
	private GoogleKeywordLocator googleLocator;
	
	public GoogleKeyword(WebDriver driver){
		this.driver = driver;
		googleLocator = new GoogleKeywordLocator().initialize(this.driver);		
	}
	/**
	 * Navigate to a particular url
	 * @param url
	 */
	public void navigateToUrl(String url){
		this.driver.get(url);
	}
	
	/**
	 * Searches for a string on google home page
	 * @param searchString
	 */
	public void searchForString(String searchString){
		googleLocator.searchField.sendKeys(searchString);
        googleLocator.submitButton.click();
    }
	
	/**
	 * Clicks on the first result in the google results list.
	 */
	public void clickOnResult(){
        clickOnResult(0);
    }
	
	/**
	 * Clicks on a particular item number in the list.
	 * @param index
	 */
	public void clickOnResult(int index){
        googleLocator.searchResult.get(index).click();
    }
	
	/**
	 * Waits for the results to be displayed.
	 */
	public void waitForResultsToDisplay(){
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(new ExpectedCondition&lt;Boolean&gt;() {
			@Override
			public Boolean apply(WebDriver driver) {
				
				return googleLocator.searchResult.size()>0;
			}

			@Override
			public String toString() {
				return "Condition not fullfilled";
			}
		} 
		);
	}
	
	/**
	 * Wait for the said title to appear on the page.
	 * @param title
	 */
	public void waitForTitle(String title){
		WebDriverWait wait = new WebDriverWait(this.driver, 60);
		wait.until(ExpectedConditions.titleContains("Software testing"));
	}
}
</pre>

I will add the the above defined Keyword class to my list of listners inside **taf.properties** file as follows.

<pre class="brush: plain;">
listeners=com.test.automation.framework.keyword.google.GoogleKeyword
</pre>

Following is my keyword test which is written in excel sheet.

<img src="{{site.base_url}}/images/GoogleKeywordTest-xls.png"/>

And finally following is my sample test which will make use of the said keyword file.

<pre class="brush: java;">
public class GoogleKeywordTest {
		
	@Test
	public void googleSearchKeywordTest(){
		WebDriver driver = new FirefoxDriver();
		File file = new File("src/test/resources/GoogleKeywordTest.xls");
		KeywordExecutor keyExecutor = new KeywordExecutor(driver, file,(String[]) null);
		keyExecutor.execute();
		driver.quit();
	}
}
</pre>