---
layout: default
title: Locator Util FAQ
permalink: /locator_faq.html
heading: Locator Util
faq: true
---
#Locator Util FAQ

* Q: Can I mention a default file as locator file for my locator or Page class? <br/>
A: Yes <br/><br/>
* Q: How can I mention a default file as locator file for my locator or Page class? <br/>
A: You can mention a default file containg your locators values for your Locator & Page Classes by defining a method named "getLocatorFile" which returns the 
File object pointing to your default locator/selector containg file. See the example below:

Simple Locator Class:

<pre class="brush: java;">
public class DefaultFileGoogleLocator {
	
	@FindBy(how=How.CSS,using="search_box")
	public WebElement searchField;
	
	@FindBy(how = How.CSS,using="submit_button")
	public WebElement submitButton;
	
	@FindBy(how = How.CSS,using="search_result")
	public List&lt;WebElement&gt; searchResult;
	
	@FindBy(how = How.CSS,using="search_result_text")
	public WebElement searchResultText;

	public File getLocatorFile(){
		File file = new File("src/test/resources/google.properties");
		return file;
	}
}
</pre>

A Page Class:

<pre class="brush: java;">
public class GoogleHomeLocatorPage extends PageClass{
	
	@FindBy(how=How.CSS,using="search_box")
	public WebElement searchField;
	
	@FindBy(how = How.CSS,using="submit_button")
	public WebElement submitButton;
	
	@FindBy(how = How.CSS,using="search_result")
	public List&lt;WebElement&gt; searchResult;
	
	@FindBy(how = How.CSS,using="search_result_text")
	public WebElement searchResultText;

	@Override
	public String toUrl() {
		return "";
	}

	@Override
	public boolean at() {
		return getDriver().getTitle().contentEquals("Google");		
	}
	
	@Override
	public File getLocatorFile(){
		File file = new File("src/test/resources/google.properties");
		return file;
	}
	
	public void searchForString(String searchString){
		searchField.sendKeys(searchString);
        submitButton.click();
    }

	@Override
	public void init() {
		
	}

}
</pre>
<br/>
* Q: Can I override the default file mentioned in my Locator class and use someother file if needed? <br/> 
A: Yes you can do so by just passing the file or filePath as argument to the "initElements" method of CustomPageFactory 
or the "to" and "at" methods which using the TAF provided Page Object Model methods.
<br/><br/>
* Q: Can I use the default Page Object Factory provided by Selenium along with TAF locator util? <br/>
A: Yes, you can use the default Page Object Factory based elelment identification strategy with TAF provided utils. 
TAF tries to find a file for your locators by looking for user provided file, default file provider(if any).
If no file menotioned by the user TAF automatically uses the default Page Factory behavior of selenium and considers the "using" attribute value of Page Factory annotations as selelctors.
<br/><br/>
* Q: Can I have both keys for locators and selelctors being mentioned for locating elements while using locator util? <br/>
A: No. Currently TAF do not support both Default Page Factory behavior and TAF enhanced behavior to be present in a single Locator or Page class.
<br/><br/>
* Q: What kind of files are currently supported for storing the locators? <br/>
A: Currently only "properties" file is supported for storing the element locators/selectors.
<br/><br/>
* Q: Page Factory dont have a explicit wait method to wait for an element, then how can I wait for an element to be avaialble? <br/>
A: TAF provides a _**Utilities**_ class which contains different utilities that have different wait methods that you can use to wait for a particular elelment or a Page(when using Page Object Model).
<br/><br/>
