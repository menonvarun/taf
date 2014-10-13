---
layout: default
title: Global Data Store
permalink: /global_data_store.html
heading: Global Data Store
toc: true
---
#Global Data Store

Most of the time while doing automation we need to share some test-data across different tests. This data can be across the whole test execution or on a particular test thread context when tests are getting executed in parallel. TAF have an in-built utility which allow users to share any data across the whole execution or on a particular thread while doing parallel execution.

The data in the data-store is represented by a key which is a string where as the value can be any class object. While retrieving the data from the data-store the util methods will automatically convert the stored data to respective object type where the retrieved data is going to be stored.

<a name="across_all_tests"> </a>
##Sharing data across all tests

To share a particular data across all threads just get the instance of the [_**GlobalDataStore**_]() class and call the method _**putStaticData(key, dataObjet)**_.
Look at the example below:

<pre class="brush: java;">
	GlobalDataStore dataStore = GlobalDataStore.getInstance();
	dataStore.putStaticData("browser.name","firefox");
</pre>

Getting the data from the data store:

<pre class="brush: java;">
	GlobalDataStore dataStore = GlobalDataStore.getInstance();
	String browserType = dataStore.getStaticData("browser.name");
</pre>

<a name="across_a_thread"> </a>
##Sharing data across a thread while doing parallel execution

To share a particular data across a thread while doing parallel execution just get the instance of the [_**GlobalDataStore**_]() class and call the method _**putThreadData(key, dataObjet)**_.
Look at the example below:

<pre class="brush: java;">
	GlobalDataStore dataStore = GlobalDataStore.getInstance();
	dataStore.putThreadData("browser.name","firefox");
</pre>

Getting the data from the data store:

<pre class="brush: java;">
	GlobalDataStore dataStore = GlobalDataStore.getInstance();
	String browserType = dataStore.getThreadData("browser.name");
</pre>

Note: The above _putThreadData_ and _getThreadData_ will only work for a particular active thread and should be used accordingly. For ex. In case your are execute your tests in TestNg with parallel execution as _"methods"_ this utility is of no use as TestNg invokes a new thread for each test execution. But this utility can be used when you want to execute tests with parallel option "tests", "class" , "groups" etc.

