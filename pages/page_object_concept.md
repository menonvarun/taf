---
layout: default
title: Page Object Model Concept
permalink: /page_object_model_concept.html
heading: Page Object Concept
conceptnav: true
---

#Page Object Model

Page Object Model is a very widely used model in the field of automation and solves one of the biggest problem that any automation framework face i.e 
- Managing utils
- Managing methods 
- Reducing code duplicity etc. 

It solves this by suggesting certain methodology for managing and modelling the code.
Some of the main points that it suggests are:

1. Model your application UI pages as classes in your framework. (There is no hard-and-fast rule that the whole page on the UI should be represented by one class, you can also create classes for the sections of the page if they are used in other UI pages or sections.)
2. Each class will contain methods that perform the actual functionality provided on the page or section that the said class represents.
3. Each method in the Page class should return the object of the same page class or another page class if the said method/functionality navigates the UI to a new page.
4. Method names inside the page class should represent the actual functionality that it performs.

A normal Page Object Model implementation in Java will be as follows:

We will take the same Google search scenario and converting it to a Page Object Model. Following is the class representing Google home page

<pre class="brush: java;">
public class GoogleHomePage{
	
	GoogleLocator googleLocator;
	WebDriver driver;
	
	public GoogleHomePage(WebDriver driver){
		this.driver=driver;
		googleLocator = new GoogleLocator().initialize(this.driver);
	}
	
	public boolean at() {
		return this.driver.getTitle().contentEquals("Google");		
	}
	
	public void searchForString(String searchString){
		googleLocator.searchField.sendKeys(searchString);
        googleLocator.submitButton.click();
    }
}
</pre>

Following is the class representing the Google results page.

<pre class="brush: java;">
public class GoogleResultsPage{
	GoogleLocator googleLocator;
	WebDriver driver;
	public GoogleResultsPage(WebDriver driver){
		this.driver = driver;
		googleLocator = new GoogleLocator().initialize(this.driver);
	}
	
	public boolean at() {		
		return googleLocator.searchResult.size() > 0;
	}
		
	public void clickOnResult(){
        googleLocator.searchResult.get(0).click();
    }
}
</pre>

Following is a Page Object Model based Test which uses the above defined page classes.

<pre class="brush: java;">
    @Test
	public void googleSearchTest(){
		WebDriver driver = new FirefoxDriver();
		driver.get("www.google.com");
		GoogleHomePage homePage = new GoogleHomePage(driver);
		Assert.assertTrue(homePage.at());
		
		homePage.searchForString("Testing");
		
		GoogleResultsPage resultsPage = new GoogleResultsPage(driver);
		Assert.assertTrue(resultsPage.at());
		resultsPage.clickOnResult();
	}

</pre>