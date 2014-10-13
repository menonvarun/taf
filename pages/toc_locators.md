---
layout: default
title: Enhanced Page Object Factory
permalink: /locator_in_taf.html
heading: Enhanced Page Object Factory
toc: true
---

#Enhanced Page Object Factory

You must be aware of the Page Object Factory provided as part of selenium webdriver apis. The advantage of using Page Object Factory is that make the test code or page code more readable. You can define page elements as Webdriver _**WebElement**_ class objects and then use them in your code. This way you will be getting rid of the repeated code of _driver.findElementBy(By.id("locator"))_.

One of the problem that most of the people have with Page Object Factory is that the locators for the web-elements have to be mentioned in the _FindBy_ annotation itself. 
TAF mixes the Page Object Factory with the industry common practise. It provides an extended Page Object Factory implementation of selenium which allow users to provide a web-elements locators in a properties file. User can store represent different web-elements as keys and mention their selectors as value in properties file. TAF automatically load the file and then read/use the respective selector for identifying a element on the page.
 
Users can initialize the said class using the extended _**CustomPageFactory**_ class of TAF by providing the file containing the said key and its respective selector as a method argument. 
TAF fetches this locator at runtime from the said file based on the key provided to Page factory annotations. Following is an example:

**Conventional Page Object Factory implementation:**

<pre class="brush: java;">
public class GoogleLocator{
	
	@FindBy(how=How.CSS,using="#gbqfq")
	public WebElement searchField;
	
	@FindBy(how = How.CSS,using=".gbqfb")
	public WebElement submitButton;
	
	@FindBy(how = How.CSS,using="h3.r > a")
	public List&lt;WebElement&gt; searchResult;
}
</pre>

Initialization in conventional way:

<pre class="brush: java;">
GoogleLocator locator = PageFactory.initElements(driver,
		GoogleLocator.class); 
</pre>

**Using the enhanced Page Object Factory:**

<pre class="brush: java;">
public class GoogleLocator{
	
	@FindBy(how=How.CSS,using="search_box")
	public WebElement searchField;
	
	@FindBy(how = How.CSS,using="submit_button")
	public WebElement submitButton;
	
	@FindBy(how = How.CSS,using="search_result")
	public List&lt;WebElement&gt; searchResult;
}
</pre>
The properties file:

<pre class="brush: java;">

search_box=#gbqfq

submit_button=.gbqfb

search_result=h3.r > a
</pre>

Initialization while using the enhanced Page Factory:

<pre class="brush: java;">

GoogleLocator locator = CustomPageFactory.initElements(driver, 
		GoogleLocator.class,"googlelocator.properties"); 
</pre>

In the above example you can see that an extra argument is passed to the _initElements_ of the _CustomPageFactory_ class. This argument is the path of the properties file that contains the locator information. There extra overloading methods that are available in _CustompageFactory_ class that allow users to pass a _File_ object too.

<a name="default_file"> </a>

##Providing default locator file
Users have the provision to provide a default file for each of his locator class if required. The enhanced Page factory of TAF automatically will load the file during initialization. To do so users just have to define a method named _**getLocaterFile**_ which returns a _**File**_ class object in the respective class where the page factory based elements are defined. Look at the example below:

<pre class="brush: java;">
public class GoogleLocator{
	
	@FindBy(how=How.CSS,using="search_box")
	public WebElement searchField;
	
	@FindBy(how = How.CSS,using="submit_button")
	public WebElement submitButton;
	
	@FindBy(how = How.CSS,using="search_result")
	public List&lt;WebElement&gt; searchResult;
	
	@Override
	public File getLocatorFile(){
		File file = new File("googlelocator.properties");
		return file;		
	}
}
</pre>

The default locator file will only be considered if the user haven't explicitly provided a different file object or file path when using _**CustomPageFactory**_  _**initElements**_ method. In case no file is provided while initializing the class or as default the values provided to _using_ attribute of _@FindBy_ annotation will considered as selector value like the original webdriver page factory.

<a name="runtime_file"> </a>

##Providing a locator file while navigating or verifying a Page	

The above mentioned initialization options is also available to users when they are navigating or validating a page class using the _**to**_ or _**at**_ methods. The file can be provided as an argument by passing either the _**File**_ object or the file-path while using the respective methods: Look at the following example:

<pre class="brush: java;">
@Test
public void googlePageLocatorFileBasedTest(){
	File file = new File("googlelocator.properties");
		
	GoogleHomePage homePage = to(GoogleHomePage.class,file);
		
	homePage.searchForString("Testing");
	
	GoogleResultsPage resultsPage = at(GoogleResultsPage.class,file);
}
</pre>

Storing locators onto a file rather code provides with different advantages:
- In case of locator change the locator can just be modified in the file and the user don't have to change the code in anyway.
- The said utility can be used to run same functionality on different version of application where the locators may change between different versions.

