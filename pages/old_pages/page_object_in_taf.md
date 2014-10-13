---
layout: default
title: Page Object Model
permalink: /page_object_model_in_taf_old.html
heading: Page Object Model
basicnav: true
---

#Page Object Model

TAF got inspired from the implementation of Page Object Model in [_**GEB**_](http://www.gebish.org) and thought 
to provide the similar kind of features to the users of Java.
TAF provides certain in-built utils which will allow users to write Frameworks using Page Object Model with ease.

Look at the following example written using the TAF Page Object Model.
Following is the TAF properties file.

<pre class="brush: plain;">

base_url=http://www.google.com

</pre>

Following is the class representing the Google home page.

<pre class="brush: java;">
public class GoogleHomePage extends PageClass{
	
	GoogleLocator googleLocator;
	
	@Override
	public String toUrl() {
		return "";
	}
	
	@Override
	public boolean at() {
		return getDriver().getTitle().contentEquals("Google");		
	}
	public void searchForString(String searchString){
		googleLocator.searchField.sendKeys(searchString);
        googleLocator.submitButton.click();
    }

	@Override
	public void init() {
		googleLocator = new GoogleLocator().initialize(this.browser);
	}
}
</pre>

Following is the class representing the Google results page.

<pre class="brush: java;">
public class GoogleResultsPage extends PageClass{
	GoogleLocator googleLocator;
	
	@Override
	public String toUrl() {
		return "";
	}

	@Override
	public boolean at() {		
		return googleLocator.searchResult.size() > 0;
	}
	
	@Override
	public void init() {
		googleLocator = new GoogleLocator().initialize(this.browser);
	}
	
	public void clickOnResult(){
        googleLocator.searchResult.get(0).click();
    }
}
</pre>

And following is a simple test which goes to the google home page and search for a keyword and click on the result.

<pre class="brush: java;">
	@Test
	public void googleTest(){
		GoogleHomePage homePage = to(GoogleHomePage.class);
		
		homePage.searchForString("Testing");
		
		util.waitForPage(browser, GoogleResultsPage.class);		
		
		GoogleResultsPage resultsPage = at(GoogleResultsPage.class);
		
		resultsPage.clickOnResult();
		
		WebDriverWait wait = new WebDriverWait(browser.getDriver(), 60);
		wait.until(ExpectedConditions.titleContains("Software testing"));
	}
</pre>

If you look at the above example you will notice that there is no manual object creation required for the Page Object classes.
TAF automatically creates the Page Objects of the classes that are passed as parameter. 

It provides certain util methods like [_**to**_]() and [_**at**_]() that will help in navigating your pages and also verifying 
that you are on the said page. The _**to**_ method automatically navigate your browser to a specific url defined in the said page class after appending it to the base url mentioned in the **taf.properties** file. It also verifies that the page is loaded or available based on the _**at**_ method defined in the said Page class.

