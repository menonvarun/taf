---
layout: content
title: Page Object Example
permalink: /page_object_example.html
heading: Page Object Example
---
#Page Object Example

Following is a simple test which goes to the google home page and search for a keyword and click on the result.

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

It provide certain util methods like [_**to**_]() and [_**at**_]() that will help in navigating your pages and also verifying that you are on the said page. The _**to**_ method automatically navigate your browser to a specific url defined in the said page class after appending it to the base url mentioned in the **taf.properties** file. It also verifies that the page is loaded or available based on the _**at**_ method defined in the said Page class.

