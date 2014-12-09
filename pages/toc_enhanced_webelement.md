---
layout: default
title: Enhanced WebElement
permalink: /enhanced_webelement.html
heading: Enhanced WebElement
toc: true
---

#Enhanced WebElement

As the name suggests is an Enhanced form of WebElement feature provided by the WebDriver. It gives following advantages.
- Multiple filtering options on a particular set of WebElement's
- Support dynamic element identification in Page Object Factory implementation.
- Support for Jquery like features. For ex. parent, child, siblings etc.


#Creating/Using Enhanced WebElement

There are multiple ways of using the enhanced webelement:
- Using Page Object Factory
- Object initialization

<a name ="using_page_object_factory"> </a>

##Using Page Object Factory
To use TAF enhanced webelement with Page Object Factory initialization just define your elements with type _**EWebElement**_ instead of _**WebElement**_. During the usage of _**CustomPageFactory**_ class page factory initialization these elements will automatically be initialized. Look at the example below.

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
In case you need to get a particular element for the enhanced webelement object you can use certain given methods like _**firstElement, lastElement, nthWebElement,**_ etc. 
You can also get the enhanced webelement object similar to webelement.

<a name ="using_object_intialization"> </a>
##Using Object Initialization

You can create instances of enhanced webelement class object by passing a single or a list of _**WebElement**_ object to the constructor of _**EWebElement**_ class
Look at the example below:

<pre class="brush: java;">
EWebElement eWebElement1 = new EWebElement(driver.findElement(By.id("selector"));

EWebElement eWebElement2 = new EWebElement(driver.findElements(By.id("selector"));
</pre>

<a name ="filtering_with_enhanced_webelement"> </a>
#Filtering with Enhanced WebElement

We all know that while using Page Object Factory we wont be able to identify WebElements with dynamic values as the annotations only accepts static values.
Also in case we want to select only a set of elements based on certain criteria we have to write our own logic to do so. To overcome these issues the enhanced webelement comes with a filtering option. This allows users to filter a set of webelements using multiple filter criteria. For ex.

Consider I have a page with following html content:

<pre class="brush: xml;">
 
<span id="location">Pharmacy</span>
<span class="small" id="id" name="name">Small 1</span>
<span class="large" id="id2" name="exact_name_value">Small 2</span>
<span class="small" id="id3" name="ends_with_name">Small 3</span>
<span class="large" id="id4" name="name_starts_with">Small 4</span>
 
</pre>

If I want to select an element having tag *"span"* and which starts-with value *"name"* and have a class value *"large"*, I can simple do that as follows:

<pre class="brush: java;">
@FindBy(tagName = "span")
EWebElement elements;

EWebElement filteredElements = elements.filter( whereClass(equalsValue("large")), 
													whereName(startsWith("name")));

  
</pre>

There are lot of options available for Filtering and text-matching for available methods just take a look at the Java documentation of following.

- [Filter Options] ({{site.base_url}}/javadoc/org/imaginea/test/automation/framework/dom/filter/Filter.html)
- [Test Matching Options] ({{site.base_url}}/javadoc/org/imaginea/test/automation/framework/dom/filter/TextMatching.html)

For simplicity all the util methods under Filter and TextMatching class are static methods, so in case you need better readability you can simply do a static import of these classes in your Util classes as mentioned below:

<pre class="brush: java;">

import static org.imaginea.test.automation.framework.dom.filter.Filter.*;
import static org.imaginea.test.automation.framework.dom.filter.TextMatching.*;
 
</pre>

<a name ="jquery_features"> </a>
## Jquery Features
While doing automation many a times we need to do different operations on a WebElemnt object like finding siblings, finding a child, moving to the parent etc. Writing code for such operations may be very cumbersome and time consuming. To solve this enhanced webelement contains certain _**JQuery**_ like methods in it which allows user to easily do webelement related operations like _**prev, next, parent, child, siblings**_ etc.

To get the list of all the methods available just look at the [EWebElement documentation]({{site.base_url}}/javadoc/org/imaginea/test/automation/framework/dom/EWebElement.html)

