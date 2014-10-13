---
layout: default
title: Enhanced WebElement
permalink: /enhanced_webelement.html
heading: Enhanced WebElement
toc: true
---

#Enhanced WebElement

While doing automation many a times we need to do different operations on a WebElemnt object like finding siblings, finding a child, moving to the parent etc. Writing code for such operations may be very cumbersome and time consuming. To solve this TAF comes with an in-built [_**EWebElement**_]() class which contains certain _**JQuery**_ like methods in it this allows user to easily do webelement related operations like _**prev, next, parent, child, siblings**_ etc.

There are multiple ways of using the extended webelement:
- Using Page Object Factory
- Object initialization

<a name ="using_page_object_factory"> </a>

##Using Page Object Factory
To use TAF extended webelement with Page Object Factory initialization just define your elements with type _**EWebElement**_ instead of _**WebElement**_. During the usage of _**CustomPageFactory**_ class page factory initialization these elements will automatically be initialized. Look at the example below.

<pre class="brush: java;">
public class GoogleLocator{
	
	@FindBy(how=How.CSS,using="#gbqfq")
	public EWebElement searchField;
	
	@FindBy(how = How.CSS,using=".gbqfb")
	public EWebElement submitButton;
	
	@FindBy(how = How.CSS,using="h3.r > a")
	public EWebElement searchResult;
}
</pre>
_**EWebElement**_ class by default store all the elements identified by a give locator in its instance, its similar to doing _driver.findElements(By.id("selector"))_.
In case you need to get a particular element for the extended webelement object you can use certain given methods like _**firstElement, lastElement, nthWebElement,**_ etc. 
You can also get the extended webelement object similar to webelement.

<a name ="using_object_intialization"> </a>
##Using Object Initialization

You can create instances of extended webelement class object by passing a single or a list of _**WebElement**_ object to the constructor of _**EWebElement**_ class
Look at the example below:

<pre class="brush: java;">
EWebElement eWebElement1 = new EWebElement(driver.findElement(By.id("selector"));

EWebElement eWebElement2 = new EWebElement(driver.findElements(By.id("selector"));
</pre>
