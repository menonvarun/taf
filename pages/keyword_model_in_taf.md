---
layout: default
title: Keyword Model
permalink: /keyword_model_in_taf.html
heading: Keyword Model
basicnav: true
---

#Plug-n-Play Keyword Driven Framework

TAF consists of an in-build plug-n-play keyword driven automation framework. You can simply define your keywords definitions and let know TAF about it. Once done you can simply write your keyword driven tests in csv and excel format and start using them with TAF.
<ul>
<li><a href="#example">Simple keyword based test-case</a></li>
<li><a href="#testsuite">Test Suite Support</a></li>
<li><a href="#testreporting">Reporting</a></li>
</ul>
<a name="example"> </a>
##Simple Keyword Based Test-case

Just define your keyword as public methods in a class by extending the [_**KeywordBase**_]() class or the [_**IKeyword**_]() interface. 
Once your keyword class has been defined just add the path of the class to the **"listener"** property inside the **taf.properties** file.

TAF will automatically load the class at runtime on execution and considers all its public methods as keywords for your test files. 
TAF also provides common utility to read your suite files and test files using a common format commonly used across the industry. 
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

I will add the above defined Keyword class to my list of listeners inside **taf.properties** file as follows.

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

<a name="testsuite"> </a>
##Test Suite Support

TAF supports test suite file for identification and execution of keyword based tests.
The test suite is defined on the basis of commonly used test-suite format across the industry.

Take a look at a sample suite file as shown below:

<img src="{{site.base_url}}/images/GoogleKeywordSuite.png"/>

The above suite defines different tests with Test id, Test name, Enabled, File path and any extra arguments that may be required. 
The test files metioned for the test-cases are similar to those as mentioned in the above example of <a href="#example">Simple keyword based test-case</a>. 
To run the test suite TAF makes use of the data-driven technique provided with TestNG.
TAF provides in-built utilities that will parse the suite file and data-driven test object for execution as simple keyword test.
Take a look at the following test for better understanding:

<pre class="brush: java;">
public class GoogleKeywordTestSuite {
	private WebDriver driver;
	
	@BeforeClass
	public void setListner(){
		DefaultConfig config = DefaultConfig.getDefaultConfig();
		config.setConfigValue("listeners", "com.test.automation.framework.keyword.webdriverbased.google.GoogleKeyword");
	}
	
	@BeforeMethod
	public void init(){
		driver = new FirefoxDriver();
	}
	
	@AfterMethod
	public void cleanup(){
		driver.quit();
	}
	
	
	@Test(dataProvider="Data")
	public void googleSearchTestSuite(ISimpleTest simpleTest){
		File file = new File(simpleTest.getTestFilePath());
		KeywordExecutor keyExecutor = new KeywordExecutor(driver,file);
		keyExecutor.execute();		
	}
	
	@DataProvider(name="Data")
	public Object[][] getTestData(){
		File file = new File("src/test/resources/keyword/webdriverbased/GoogleTestSuite.xls");
		TestSuite suiteReader = new TestSuite(file, new ArrayList&lt;String&gt;());
		return suiteReader.getTobeExecutedTests();
	}

}
</pre>

The above test contains TestNG based before and after method that creates and quits WebDriver object before & after each test-method execution. 
It also contains a DataProvider annotated test-method that parses the test-suite file "GoogleTestSuite.xls" and returns a Double Object array containing objects of "ISimpleTest" for all the tests that are enabled inside the said suite. Each of the "ISimpleTest" object represents one test in the said suite file and contains details about the said test.

This array of Object is used by the test-method "googleSearchTestSuite" for data-driven test execution. 
The said method accepts the "ISimpleTest" object as argument which then used for a simple keyword executor test as shown above.

<a name="testreporting"> </a>
##Test Reporting

Reporting is another challenge with keyword driven framework when used with TestNG as the default report will contain the data-driven test-method name in the report rather than the Test case detail from the suite file. Normally in the industry an custom report is generated for any of the keyword driven framework. 

TAF solves this issue of report generation by customizing the name that TestNG considers for test-report generation. The default reports that gets generated will containing the test-case name of the test-case as mentioned in the test-suite file. This customization can be enabled by just adding a in-built TestNG listener class **"com.test.automation.framework.testng.listener.MethodInvokerListener"**  to your execution.

Take a look at following screen shots to see the customization:

**Original Report of TestNG**

<img src="{{site.base_url}}/images/original-test-report.png"/>


**Custom Report of TestNG**

<img src="{{site.base_url}}/images/custom-test-report.png"/>


**Original Report of ReportNG**

<img src="{{site.base_url}}/images/reportng-original-test-report.png"/>

**Custom Report of ReportNG**

<img src="{{site.base_url}}/images/reportng-custom-test-report.png"/>

If you notice in the above screen shots, the original report shows the test-method name "googleSearchTestSuite" in the reports. 
Whereas in the custom reports the Test-case id and the Test-case name as mentioned in the executed test-suite file is appended and displayed in the report. 
This helps in identifying the exact failure in the report and also saves a lot of time in developing and integrating custom test-reports for keyword driven framework.