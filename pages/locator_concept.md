---
layout: default
title: Locator Concept
permalink: /locator_concept.html
heading: Locators Concept
conceptnav: true
---

#Locator

While doing automation the common practice in the industry for maintaining element locators is to keep them out of the code and into different file types like _.properties_ , _excel_, _xml_, _csv_ etc.

On the other hand Selenium have another great inbuilt uitlity named _**Page Object Factory**_ that gives user the ability to define simple Java variables for your web elements. These elements are evaluated at runtime when you try to use them in your code. Use of Page Object Factory reduces lot of redundant code that normally engineers end up writing while doing automation, this helps in increasing the verbosity of the code.

Lets consider the following example to understand the advantage of Page Object Factory:

In this example we will be automating the Google search page and will automate the search scenario. Our test method will look something like below:

Look at the following code without the use of Page Object factory. Here Im giving an example of a test-method but the same will be applicable if you use a Page Object Model or if you define methods for common functionalities.

<pre class="brush: java;">
    @Test
	public void googleSearchTest(){
		WebDriver driver = new FirefoxDriver();
		driver.get("www.google.co.in");
		
		driver.findElement(By.cssSelector("#gbqfq")).sendKeys("Testing");
		driver.findElement(By.cssSelector(".gbqfb")).click();
		
		driver.findElement(By.cssSelector("h3.r > a")).click();
		
	}
</pre>

But if we use Page Object Factory the code will look something like below:

<pre class="brush: java;">
	@FindBy(how=How.CSS,using="#gbqfq")
	public WebElement searchField;
	
	@FindBy(how = How.CSS,using=".gbqfb")
	public WebElement submitButton;
	
	@FindBy(how = How.CSS,using="h3.r > a")
	public List&lt;WebElement&gt; searchResult;
	
	@Test
	public void googlePageFactorySearchTest(){
		
		WebDriver driver = new FirefoxDriver();
		driver.get("www.google.co.in");
		
		searchField.sendKeys("Testing");
		submitButton.click();
		
		searchResult.get(0).click();
		
	}
</pre>

Here _**@FindBy**_ is the annotation provided Selenium library itself. If you look at the test-method code it is much more cleaner and more readable compared to earlier code.