---
layout: default
title: Configuration
permalink: /toc_configuration.html
heading: Configuration
toc: true
---
#Configuration

This page will give description about the default configuration file of TAF.

By default at the start of the execution TAF will try to load the file named "taf.properties" from the location "src/test/resources" 
of the project folder from where the execution is being done. In case you need to configure a different configuration file 
you can do so by setting path of the said file as value to the property _**taf.config**_ to the system properties.

Following are the different properties currently used by TAF from the properties file:

* **base_url** - Please mention the base url of your application. This property is used when running the Page Object Model provided by TAF.
The said url is appended to the url mentioned in the respective Page classes for navigation purpose.<br/>

* **driver.name** - This is used by the default driver manager provided by TAF.
This value is used to provide the in-built driver creator and manager. Supported values "firefox", "ie", "chrome" and "htmlunit".
.<br/>
* **thread.based.driver** - Set this value to **true** is you want the WebDriver object provided by the TAF framework driver manager to be per thread.
This will make sure that whenever you call the "getDriver" method on the "CacheDriverFactory" class you get a different WebDriver object for each thread on which the said method is called.
<br/>

* **userdefined.driverclass** - This property is used by the in-build driver manager class to load a user defined class. 
Use this property to set the classpath of the class that implements the _**IDriverProvider**_ interface of the TAF framework.
Classpath of only one class should be added here, you can cannot have multiple driver provider class.
<br/>

* **use.inbuilt.driver** - This property is used while using the in-built Page Object Model implementation. This property is used for enabling and disabling the 
in-build driver provider for the Page Classes. Set this property value to "false" if you want to yourself create the driver object. Once create set the driver object to the browser object of the test-class 
using the "setDriver" method of browser object.
<br/>

* **listeners** - This value is used by the plug-n-play keyword framework of TAF to get the keyword providing classes.
Just define your keyword as public methods in a class by extending the KeywordBase class or the IKeyword interface. 
Once your keyword class has been defined just add the classpath of the said class to this property.
In case you have multiple classes defined please add the path as comma separated. 
TAF will load all the class files and use them for identifying and executing the keywords in your test-files. 
Please make sure while adding the listeners the sequence is the same as you want TAF to look for the keywords in the said files.

