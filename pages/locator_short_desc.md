---
layout: content
title: Managing and storing locators
permalink: /locator_short_desc.html
heading: Managing and storing locators
---
#Managing and storing locators

TAF mixes the Page Object Factory with the industry common practise. It has extended the Page Object Factory to allow users to provide a locator key instead of actual locators 
while using the <em><strong>@FindBy</strong></em> annotation of Page Object Factory. 

Consider that the locators in your framework is added to a _properties_ file. 

Following is the normal usage of webdriver where you directly used the said properties file to get your webelement selectors:</p>

<pre class='brush: java;'>
String locator = poperties.getProperty("google_search_field");

driver.findElement(By.css(locator)).sendKeys("Testing");

</pre>

Following is a sample code where the same file will be used by TAF along the Selenium Page Object Factory

<pre class='brush: java;'>

	@FindBy(how=How.CSS,using="search_box")
    public WebElement searchField;
	
	searchField.sendKeys("Testing");
</pre>