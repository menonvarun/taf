---
layout: default
title: Page Object Model
permalink: /page_object_model_in_taf.html
heading: Page Object Model
toc: true
---

#Page Object Model

As said earlier TAF got inspired from the implementation of Page Object Model in [_**GEB**_](http://www.gebish.org) and thought 
to provide the similar kind of features to the users of Java.

<a name="page_classes"> </a>
##Page Classes
Pages in TAF have to extend [_**PageClass**_] provided by the framework. Once your page classes extend the said class different page object model utilities and features will become available to your page classes.

<a name="page_navigation"> </a>
##Page Navigation - the "to" method
A common problem while using a Page Object model based automation framework is that the users have to manually handle the page navigation, class object initialization and page verification in the tests or in utils.
TAF provides utility to do all at the same time and without much hassles. TAF allows the user to set a base url for the application under test.
This is done by setting the property _**base_url**_ in the taf configuration. TAF also allows user to provide explicit urls for your page classes. These urls can be extended urls to the defined base url or individual urls if required.

To represent your page class with a url just override method _toUrl_ method of the parent PageClass as shown below.

<pre class="brush: java;">
	@Override
	public String toUrl() {
		return "";
	}
</pre>

To provide an extended url to existing base url just provide the extended url. For ex. Your base url is _www.google.com_ and you have a page class that represent the google mail home page which can be navigated using the url _"www.google.com/mail"_.
Then you will have the _toUrl_ method to return only the extended url i.e. _"/mail"_.
Look at the following example written using the TAF Page Object Model.

<pre class="brush: java;">
public class GoogleMailPage extends PageClass {
	@Override
	public String toUrl() {
		return "/mail";
	}
}
</pre>

In case you want to mention the whole url you can return the whole url too as mentioned below:

<pre class="brush: java;">
public class GoogleMailPage extends PageClass {
	@Override
	public String toUrl() {
		return "www.google.com/mail";
	}
}
</pre>

TAF have util [_**to (&lt;page class&gt;)**_]() method available in your test and page classes that will allow you to navigate between your page classes.
The utility take the Page Class as class object and will automatically navigate your browser to the mentioned page class url returned by the _toUrl_ method of the said page class.
Once navigation is successful the utility will verify the that browser is on the respective page by executing the [_**at**_]() method (covered later in this page) and return the said page class object. Taking the above mentioned _"GoogleMailPage"_ class we can have following code, the said code will navigate the browser to the google mail page as represented the _GoogleMailPage_ class.

<pre class="brush: java;">
GoogleMailPage googleMailPage = to(GoogleMailPage.class);
</pre>

<br>

<a name="page_verification"> </a>
##Page Verification - the "at" method
TAF provides utility to allow user to verify whether a feature/module or the page for which the Page class contains the functional methods is available on the browser or not. This utility allows user to validate before hand if the respective functionality or feature is available on the browser before using any of the functional methods represented in the Page Class. To define validation criteria for a particular Page class user just have to override a method named _"at"_ on his page classes as shown in the example below.

<pre class="brush: java;">
public class GoogleMailPage extends PageClass {
	@Override
	public boolean at() {
		return browser.getDriver.getTitle().contains("Gmail");
	}
}
</pre>

In the above example the _"at"_ method returns true if the browser title contains text as _Gmail_. You can evaluate the condition using any other method as element being available or displayed etc.

To validate a Page you can simply use the _**at**_ method in your page class or test-classes. In case the page validation fails the test will exit with an exception.

<pre class="brush: java;">
GoogleMailPage googleMailPage = at(GoogleMailPage.class);
</pre>

There is another utility named _**isAt**_ which returns **true** or **false** based on page verification.

<pre class="brush: java;">
	if(isAt(GoogleMailPage.class)){
		&lt; do something&gt;
	}
</pre>

**Note: ** Do not use explicit wait conditions of selenium webdriver in _at_ method, this will delay page verification when there is a failure. In case you need to explicitly wait for a particular page class you can do so by using _**waitForPage**_ method available as part of the _**util**_ object defined in the _PageClass_ and _TestClass_ of the framework.

<a name="page_initialization"> </a>
##Page initialization - the "init" method
TAF allows user to call some code when initialising the Page classes. This can be done by overriding the _**init**_ method of _**PageClass**_.

<pre class="brush: java;">
public class GoogleHomePage extends PageClass{
	
	GoogleLocator googleLocator;
	
	@Override
	public void init() {
		googleLocator = CustomPageFactoru.initElements(
							this.browser,GoogleLocator.class);
	}
}
</pre>

In the above example the whenever user calls the _**to**_ , _**at**_ , _**isAt**_ or _**getPageObject**_  method in page or test classes the framework automatically create object of the said class and call the said _**init**_ method.

**Note:** Any kind of locator initialization for page object model should be done inside the _init_ method.

<a name="Test_classes"> </a>
##Test Classes
Test Classes in TAF have to extend [_**TestClass**_] provided by the framework. Once your test classes extend the said class different page object model utilities and features will become available to your test classes.

