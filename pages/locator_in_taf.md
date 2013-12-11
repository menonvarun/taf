---
layout: default
title: Locator Management
permalink: /locator_in_taf.html
heading: Locator Management
basicnav: true
---
#Locators Management

TAF mixes the Page Object Factory with the industry common practise. It has extended the Page Object Factory to allow users to provide key instead of locators while using the _**@FindBy**_ annotation of Page Object Factory. It allows users to provide the file to fetch the locators from a file. TAF fetches this locator at runtime from the said file based on the key provided.

Let now write an example which uses TAF customised Page Object Factory and see how it looks.

Following is a _properties_ file which contains the key/locators in it.

<pre class="brush: java;">

search_box=#gbqfq

submit_button=.gbqfb

search_result=h3.r > a

</pre>

Following is the class which will store the WebElements that we will be using in our test.

<pre class="brush: java;">
public class GoogleLocator extends Locator{
	
	public GoogleLocator() {		
		super(new File("src/test/resources/google.properties"));		
	}

	@FindBy(how=How.CSS,using="search_box")
	public WebElement searchField;
	
	@FindBy(how = How.CSS,using="submit_button")
	public WebElement submitButton;
	
	@FindBy(how = How.CSS,using="search_result")
	public List&lt;WebElement&gt; searchResult;
}
</pre>

Following is a sample code where we will use the above locator class.

<pre class="brush: java;">
	@Test
	public void googlePageFactorySearchTest(){
		GoogleLocator googleLocator = new GoogleLocator().initialize(driver);
		
		WebDriver driver = new FirefoxDriver();
		driver.get("www.google.co.in");
		
		googleLocator.searchField.sendKeys("Testing");
		googleLocator.submitButton.click();
		
		googleLocator.searchResult.get(0).click();		
	}
</pre>

As you can see from the above code we will are using the same Page Object Factory with an exception that the locators are stored in a separate _properties_ file name _google.properties_. The said file is provided as argument to the super constructor of the _**Locator **_ class which is being extended by the said _**GoogleLocator**_ class.