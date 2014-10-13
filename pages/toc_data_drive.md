---
layout: default
title: Data Drive Util
permalink: /data_driven_util_in_taf.html
heading: Data Drive Util
toc: true
---

#Data Driven Util

A common problem that people face while developing a data-driven test is opening, fetching, iterating and returning back the data from different data sources like excel file, csv file, text file, databases etc.

TAF have an in-built utility that allow users to automatically fetch a required set of data from excel and csv files and return it as class objects.

Lets take an example where you need to fetch some set of data from an excel file. The common approach will be to go through each row, fetch data, store it in some variable and then return it for each iteration. In TAF you just create data file in excel or csv format with certain logical column names mentioned in the columns of the first row.
Subsequent rows will contain specific data set that needs to used for each iteration of you Data-driven test.
Following is an example of my data-set.

<img src="{{site.base_url}}/images/GoogleSearchData-xls.png" />

Now to fetch the data as Class objects you just need to create a class in your code with certain variables as required by your tests with the same name as mentioned in you column name of your data file. Following is an example.

<pre class="brush: java;">
public class GoogleSearchData {
	
	public String searchtext;
	
	public String titleToWait;

}
</pre>

Once the excel/csv file and respective data class is defined users can use the in-built two classes _**ExcelDataDrive**_ & _**CsvDataDrive**_ (depending upon the file type) and then use then to get a list of respective class objects. The variable in the said class object will contain value from each row of the data file that represent and individual iteration. Following is an example of the data-drive utility usage.:

<pre class="brush: java;">
	ExcelDataDrive excelData = 
				new ExcelDataDrive("src/test/resources/datadrive/GoogleSearchData.xls");
	/*Following method call returns a List of respective class object*/	
	List&lt;GoogleSearchData&gt; dataList = excelData.getClassObjectList(GoogleSearchData.class);
	
	/*Following method call returns a Double Object array of respective class object to be used with TestNg*/
	Object[][] dataObject = excelData.getTestngData(GoogleSearchData.class);
</pre>


Following is an example of a test where we are making use of the in-build data driven util. 


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
		/*
		Following method call will parse the above mentioned Excel file and 
		create objects of the provided "GoogleSearchData" class.
		It will return a Object[][] containing object for each iteration. 
		The utility also have a method to return a List object. 
		*/
		return excelData.getTestngData(GoogleSearchData.class);
	}
}
</pre>


