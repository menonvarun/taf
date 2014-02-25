---
layout: default
title: Home
permalink: /index.html
heading: Home
home: true
---

TAF is an automation framework api with some in-built features that provides solutions to some common automation problems. 
It consists of an Enhanced Page Object Model and a Plug-n-play Keyword driven model of Automation. 
It helps in easy maintaince of your test code and make them more readable.

##Why TAF?
While developing web automation framework there are a lot of common problems that is faced by every automation engineer. These common problems include:

<ul>
<li> <b> Managing and storing locators </b> :- 
<br/>
TAF solves this issue by enhancing the Page Object Factory implmentation of Selenium to allow users to store locators in a file rather than a Java file. Usage of Page Object Factory implemenation also helps in making the tests more readable and easy write.
<br/>
<a class="hover" href="#" child-selector="#locator" url-value="{{site.base_url}}/pages/locator_short_desc.html">Hover here to see the difference</a>
<h3 id="locator" ></h3></li><br/>
<li><b>Developing and Running a Keyword driven framework</b> :- 
<br/>
TAF have an in-built plug-n-play keyword driven framework.
Users can simply define there keywords in a single/multiple class files and add it to a TAF's property file.
Once done users are good to go ahead and define their keyword based test and test-suite files( excel or csv).
These test-suite files can then be executed using the DataDriven feature of TestNG using the internal utilities provided by the framework.
</li><br/>
<li><b>Reporting in a Keyword Driven Frameowrk:</b>
<br/>

While implementing a Keyword driven framework the common problem is Test execution reporting. 
Majority automation engineers implement their own execution and reporting framework to solve this issue.
This increases complexity of the framework and hence affecting the scalibity. 
TAF customizes the reporting of the TestNG and the execution reports conatins the actual test description of the test mentioned in the test-suite rather than some method name.
<br/><a class="hover" href="#" child-selector="#keyword-report" url-value="{{site.base_url}}/pages/keyword_report.html">Hover here to see a sample report</a>
<h3 id="keyword-report" ></h3>
</li><br/>
<li><b>Driver Initiliazation and management:-</b><br/>
Most of the frameworks have to handle the driver initialization and its management which may bring some complexity into execution if not properly handled.
TAF have an inbuilt driver management which will provide following features:-
<ul>
<li>Default driver creation based on the provided driver name ex. firefox , chrome , ie , etc.</li>
<li>Flexibility to define custom driver creation and easy plugging it into the framework.</li>
<li>Multi-threaded/Non-multithreaded driver management.</li>
</ul>
</li>
<li><b>Parsing/Reading different files for your DataDriven tests:-</b><br/>
Every automation engineer face this issue of fetching data from different input source like csv, excel and other data sources for their Data-driven tests.
Open file, look for applicable data, iterate over data and then create a single data object to be given as input your tests.
TAF comes with a utility which will parse the file and give in return List of class objects containing the data for use of single iteration.
<br/>
<a class="hover" href="#" child-selector="#dataDrivenUtil" url-value="{{site.base_url}}/pages/data_driven_short.html">Hover here to see an example</a>
<h3 id="dataDrivenUtil" ></h3>
</li><br/>
<li><b>Implementing Page Object Model:-</b><br/>
As we all know Page Object Model is a very good model when the application is big and we have huge number of test-cases.
The model helps in easily managing our utility code and in writing tests, but still there are few things that are good to have features in Page Object Model.
Few of the things that TAF felt are good to have are:-
<ul>
<li>A "to" method which will navigate your browser to specified page and verify that the page is loaded.</li>
<li>A "at" verification method which will verify that the broser is on a said Page.</li>
<li> A explicit wait utility method which will wait until a specified page is loaded when you take an action on a page.</li>
</ul><a class="hover" href="#" child-selector="#pageObjectExample" url-value="{{site.base_url}}/pages/page_object_example.html">Hover here to see an example</a>
<h3 id="pageObjectExample" ></h3>
</li><br/>
<li><b>Utilities to read Excel or CSV files:-</b><br/>
Many of the autoamtion frameworks uses excel or csv file for storing your test data. To read these files different utilities have to written and developed.
TAF comes with inbuilt utilities to read Excel and CSV files.
</li><br/>
</ul>

The above utils solves lot of issues commonly faced while doing web automation. To know more details of the above features please click on the link shown under the navigation section.


