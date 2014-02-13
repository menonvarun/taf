---
layout: content
title: Data driving utility
permalink: /data_driven_short.html
heading: Data driving utility
---
#Data driving utility

In TAF you just create data file in excel or csv format with certain logical column names mentioned in the columns of the first row.
Subsequent rows will contain specific data sets that needs to used for each iteration of you Data-driven test.
Following is an example of my data-set.

<img src="{{site.base_url}}/images/GoogleSearchData-xls.png" />

Now to fetch the data as Class objects you just need to create a class in your code with certain variables as required 
by your tests with the same name as mentioned in the column name of your data file. Following is an example.

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
