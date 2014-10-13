---
layout: default
title: Driver Management
permalink: /webdriver_in_taf.html
heading: Driver Management
toc: true
---

#WebDriver Creation/Management

TAF have an in-built driver creation and management system in place. Following are the features available by the said API:
- Default driver creation based on the provided driver name ex. _**firefox**_ , _**chrome**_ , _**ie**_ , etc.
- Flexibility to define custom driver creation and easy plugging in to the framework.
- Multi-threaded/Non multi-threaded driver management.

<a name="default_driver_creation"> </a>
##Default WebDriver creation

As mentioned earlier in the features TAF provide a default implementation for driver creation. 
In case you need a particular driver for your tests you can use the existing API provided by TAF for it. Just mention the type of driver you need by providing a value to the property _**driver.name**_ in side the TAF properties file.

Look at the following example for better understanding.

Configuration in the properties file:
<pre class="brush: plain;">
driver.name = firefox
</pre>

Following is the sample test where it uses the driver creation class [_**CacheDriverFactory**_]() for creation of driver.

<pre class="brush: java;">
	@Test
	public void inbuildDriveCreationTest(){
		DefaultConfig config = DefaultConfig.getDefaultConfig();
		config.setConfigValue("driver.name", "firefox");
		
		CacheDriverFactory driverFactory = new CacheDriverFactory();
		WebDriver driver = driverFactory.getDriver();
		driver.get("http://www.google.com");		
	}
</pre>

<a name="multi_threaded_driver_creation"> </a>
##Configuring Multi-threaded driver management
TAF have an in-build driver management which allow users to manage their driver in a way that the same driver instance can be available across all tests or 
each thread execution can have its own instance of driver object for their tests. This can be done by setting the value of **thread.based.driver** to **true** in taf.properties file.


<a name="user_defined_driver_creation"> </a>
##User defined Driver instance

It also provides the flexibility to provide your own driver creation logic by writing a class that implments the [_**IDriverProvider**_]() interface.
The path of the said class then has to be set to the property _**userdefined.driverclass**_ inside the _**taf.properties**_ file.

Let's look at the following example:

Following is the customized class:

<pre class="brush: java;">
class UserDefinedDriverProvider implements IDriverProvider{
	
	DefaultConfig config = DefaultConfig.getDefaultConfig();

	@Override
	public WebDriver getDriver() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		return driver;
	}
}
</pre>

Following is the property that is set inside the **taf.properties** file:

<pre class="brush: plain;">
userdefined.driverclass = com.test.automation.framework.driver.UserDefinedDriverProvider
</pre>
