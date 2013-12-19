---
layout: default
title: Data Driven Util
permalink: /data_driven_util_in_taf.html
heading: Data Driven Util
basicnav: true
---
#Data Driven Util

The common problem that people face while developing a data-driven test is opening, fetching, iterating and returning back of the data from different sources.
These sources can be of different types but they are mainly Excel or CSV files.

TAF provide a utility to automaticaly fetch the said data from your files and return you with a list of Class objects that can be easily used in your data driven tests.

Lets take an example where you need to fetch some set of data from an excel file. The common approach will be to go through each row, fetch data, store it in some variable and then return it for each iteration. In TAF you just create data file in excel or csv format with certain logical column names mentioned in the columns of the first row.
Subsequent rows will contain specific data sets that needs to used for each iteration of you Data-driven test.
Following is an example of my data-set.

<img src="{{site.base_url}}/images/GoogleSearchData-xls.png" />

Now to fetch the data as Class objects you just need to create a class in your code with certain variables as required by your tests with the same name as mentioned in you column name of your data file. Following is an example.

<pre class="brush: java;">
public class GoogleSearchData {
	
	public String searchtext;
	
	public String titleToWait;

}
</pre>

Following is a test where we will be making use of the in-build data driven util which will automatically parse the data file and return back the list or double object array of the objects of the specified data representing class created earlier. The variable in the said class object will contain value from each row of teh data file that represent and individual iteration.

<pre class="brush: java;">
public class GoogleSearchDataTest extends TestClass{
	
	@Test(dataProvider="googleSearchData")
	public void googleTest(GoogleSearchData data){
		GoogleHomePage homePage = to(GoogleHomePage.class);
		
		homePage.searchForString(data.searchtext);
		
		util.waitForPage(browser, GoogleResultsPage.class);		
		
		GoogleResultsPage resultsPage = at(GoogleResultsPage.class);
		
		resultsPage.clickOnResult();
		
		WebDriverWait wait = new WebDriverWait(browser.getDriver(), 60);
		wait.until(ExpectedConditions.titleContains(data.titleToWait));
	}
	
	@DataProvider(name="googleSearchData")
	public Object[][] googleSearchData() throws ClassParserException, DataNotAvailableException{
		ExcelDataDrive excelData = 
				new ExcelDataDrive("src/test/resources/datadrive/GoogleSearchData.xls");
		return excelData.getTestngData(GoogleSearchData.class);
	}
}
</pre>
