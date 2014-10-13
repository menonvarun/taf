---
layout: default
title: Locator Management
permalink: /locator_in_taf_old.html
heading: Locator Management
basicnav: true
---
#Locators Management

TAF mixes the Page Object Factory with the industry common practise. 
It has extended the Page Object Factory of selenium to allow users a feature to provide a locator key instead of the actual locator (WebElement selector) 
while using the different annotations provided by the Selenium Page Object Factory implementation. 
Users can initialize the said class using the extended _**CustomPageFactory**_ class of TAF by providing the file containing the said key and its respective selector as a method argument. 
TAF fetches this locator at runtime from the said file based on the key provided to Page factory annotations.

###Advantages
<ul>
<li>Locators/Selectors are stored in a separate file rather than being part of the code.</li>
<li>Code is more readable</li>
<li>Support for runtime change in locators helps in using the same logic but with different selectors as per requirement. 
An example may be when there are 2 versions of the same application but with different selectors and you need to support automation testing for both.</li>
</ul>

Following are two of the examples where you can use the Custom Page Factory implementation:

<ul>
<li><a href="#simplelocator">Simple Page factory</a></li>
<li><a href="#pagebased">With TAF Page Object implementation</a></li>
</ul>
Let now write an example which uses TAF customised Page Object Factory and see how it looks.

<a name="simplelocator"> </a>
##Simple Page factory
Following is an example of use of the TAF provided enhanced Page Factory feature for a normal Page Object model.
<br/>
Following is a _properties_ file which contains the key/locators in it.

<pre class="brush: java;">

search_box=#gbqfq

submit_button=.gbqfb

search_result=h3.r > a

</pre>

Following is the class which will store the WebElements that we will be using in our test.

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

Following is a sample code where we will use the above locator class.

<pre class="brush: java;">
public class GoogleSearchTest {

	WebDriver driver;
	@BeforeMethod
	public void beforeMethod(){
		driver = new FirefoxDriver();
	}
	
	@Test
	public void googleTest() throws InterruptedException{
		File file = new File("src/test/resources/google.properties");
		GoogleLocator googleSearch = CustomPageFactory.initElements(driver, GoogleLocator.class, file);
		Utilities util = new Utilities();
		
		driver.get("http://www.google.co.in");
		googleSearch.searchField.sendKeys("Testing");
		googleSearch.submitButton.click();
		util.waitForElementPresent(driver,googleSearch.searchResult);
		String text = googleSearch.searchResult.get(0).getText();
		Assert.assertTrue(text.contains("Testing");		
	}
	
	@AfterMethod
	public void afterMethod(){
		driver.quit();
	}
}

</pre>

As you can see from the above code we are using the same Page Object Factory feature but with an exception that the locators (webelement selectors) 
are stored in a separate _properties_ file named _google.properties_. 
The said file is provided as argument to the _**initElements**_ of the _**CustomPageFactory**_ implementation of TAF.

<a name="pagebased"> </a>


<br/>
##With TAF Page Object implementation

You can use the same feature as mentioned above while using the TAF provided Page Object Model feature.
You just need to override the method _getLocatorFile_ of **PageClass** while defining your Page classes. 
Tag automatically uses the said file as locator files for any of the Page Factory based WebElements present in the said class. 

You can also provide some other file at runtime for the new set of selectors while using the methods _**to**_ and _**at**_ methods.

Look at the following example

<pre class="brush:java;">
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

Following is the test file:

<pre class="brush: java;">
public class GoogleLocatorPageTest extends TestClass{

	@Test
	public void googlePageLocatorBasedTest(){
		GoogleHomeLocatorPage homePage = to(GoogleHomeLocatorPage.class);
		
		homePage.searchForString("Testing");
		
		util.waitForElementPresent(browser.getDriver(), homePage.searchResult);
		
		homePage.searchResult.get(0).click();
		
		WebDriverWait wait = new WebDriverWait(browser.getDriver(), 60);
		wait.until(ExpectedConditions.titleContains("Software testing"));
	}
	
}
</pre>

Following example shows an example of a test which uses a separate file as locator file at runtime:

<pre class="brush: java;">
public class GoogleLocatorPageTest extends TestClass{

	@Test
	public void googlePageLocatorFileBasedTest(){
		File file = new File("src/test/resources/googlev1.properties");
		
		GoogleHomeLocatorPage homePage = to(GoogleHomeLocatorPage.class,file);
		
		homePage.searchForString("Testing");
		
		util.waitForElementPresent(browser.getDriver(), homePage.searchResult);
		
		homePage.searchResult.get(0).click();
		
		WebDriverWait wait = new WebDriverWait(browser.getDriver(), 60);
		wait.until(ExpectedConditions.titleContains("Software testing"));
	}
}
</pre>

As you can see in the above example a different locator file is used for the getting webelement selectors at runtime. 
This feature helps in changing the selectors at runtime which becomes a need when the logic is same but locators are different.

