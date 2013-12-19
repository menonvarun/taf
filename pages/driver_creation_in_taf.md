---
layout: default
title: WebDriver in TAF
permalink: /webdriver_in_taf.html
heading: WebDriver Creation/Management
basicnav: true
---

#WebDriver Creation/Management

TAF have an inbuild driver creation and management system in place. Following are teh features available by the said API:
- Default driver creation based on the provided driver name ex. _**firefox**_ , _**chrome**_ , _**ie**_ , etc.
- Flexibility to define custom driver creation and easy plugging in to the framework.
- Multi-threaded/Non-multithreaded driver management.

##Default WebDriver creation

As mentioned earlier in the features TAF provide a defaut implementation for driver creation. In case you need a particular driver for your tests you can use the existing API provided by TAF for it. Just mention the type of driver you need by providing a value to the property _**driver.name**_ in side the TAF properties file.

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

##Configuring Multi-threaded driver management
TAF have an in-build driver management to allow users to manage there driver in a way that the same driver instance is available across all tests or each thread execution have its own instance of tests. object for there tests


##User defined Driver instance

It also provides the flexibility to provide your own driver creation logic by writing a class that implments the [_**IDriverProvider**_]() interface. Once implemented jsut set the path of the class to the properperty _**userdefined.driverclass**_ in the _**taf.properties**_ file.

Lets look at the following example:

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