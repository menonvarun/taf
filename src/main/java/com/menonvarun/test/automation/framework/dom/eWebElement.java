package com.menonvarun.test.automation.framework.dom;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class eWebElement {

	List<WebElement> elements;

	public eWebElement(List<WebElement> list) {
		this.elements = list;
	}

	public eWebElement(WebElement element) {
		this.elements = new ArrayList<WebElement>();
		elements.add(element);
	}

	/**
	 * Click on web element
	 */
	public void click() {
		firstElement().click();
	}

	/**
	 * Click on all matched web element
	 */
	public void clickAll() {

		for (WebElement element : elements) {

			element.click();

		}
	}

	/**
	 * type on input box
	 * 
	 * @param value
	 */
	public void type(String value) {
		firstElement().sendKeys(value);
	}

	/**
	 * Get the first web element
	 */
	public WebElement firstElement() {
		if (elements.size() > 0)
			return elements.get(0);
		return null;
	}

	/**
	 * Get first extended eWeblement
	 * 
	 * @return
	 */
	public eWebElement firsteElement() {
		if (elements.size() > 0)
			return new eWebElement(elements.get(0));
		return null;
	}

	/**
	 * Get last web element
	 * 
	 * @return
	 */
	public WebElement lastElement() {
		if (elements.size() > 0) {

			return elements.get(elements.size() - 1);
		}
		return null;
	}

	/**
	 * Get last extended web element
	 * 
	 * @return
	 */
	public eWebElement lasteElement() {
		if (elements.size() > 0) {
			return new eWebElement(elements.get(elements.size() - 1));
		}
		return null;
	}

	/**
	 * Get nth element
	 * 
	 * @param index
	 * @return
	 */
	public WebElement nthWebElement(int index) {
		if (elements.size() >= index)
			return elements.get(index);
		return null;
	}

	/**
	 * Get nth extended web element
	 * 
	 * @param index
	 * @return
	 */
	public eWebElement ntheWebElement(int index) {
		if (elements.size() >= index) {
			return new eWebElement(elements.get(index));
		}
		return null;
	}

	/**
	 * Get list of web elements
	 * 
	 * @return
	 */
	public List<WebElement> getWebElements() {
		return elements;
	}

	/**
	 * Convert web element to extended web element.
	 * 
	 * @param webelement
	 * @return
	 */
	public eWebElement geteWebElement(WebElement webelement) {
		eWebElement ewebElement = new eWebElement(webelement);
		return ewebElement;
	}

	/**
	 * Get the size of web elements
	 * 
	 * @return
	 */
	public int size() {
		return elements.size();
	}

	/**
	 * Get all following siblings of each element in the set of matched
	 * elements.
	 * 
	 * @return
	 */
	public eWebElement nextAll() {

		eWebElement eWebElements = findElements(By
				.xpath("following-sibling::*"));
		return eWebElements;
	}

	/**
	 * Get all preceding siblings of each element in the set of matched
	 * elements. * @return
	 */
	public eWebElement prevAll() {
		eWebElement ewebElement = findElements(By.xpath("preceding-sibling::*"));
		return ewebElement;
	}

	/**
	 * Get the immediately preceding sibling.
	 * 
	 * @return
	 */
	public eWebElement prev() {
		eWebElement ewebElement = new eWebElement(firstElement().findElement(
				By.xpath("preceding-sibling::*[1]")));
		return ewebElement;
	}

	/**
	 * Get the immediately following sibling.
	 * 
	 * @return
	 */
	public eWebElement next() {
		eWebElement ewebElement = new eWebElement(firstElement().findElement(
				By.xpath("following-sibling::*[1]")));
		return ewebElement;
	}

	/**
	 * Get all following siblings of each element up to .
	 * 
	 * @param xpathExpression
	 * @return
	 */
	public eWebElement nextUntil(String xpathExpression) {
		eWebElement ewebElement = findElements(By
				.xpath("following-sibling::*[following-sibling::"
						+ xpathExpression + "]"));
		return ewebElement;
	}

	/**
	 * Get all preceding siblings of each element up to .
	 * 
	 * @param xpathExpression
	 * @return
	 */
	public eWebElement prevUntil(String xpathExpression) {
		eWebElement ewebElement = findElements(By
				.xpath("preceding-sibling::*[preceding-sibling::"
						+ xpathExpression + "]"));
		return ewebElement;
	}

	/**
	 * Get the descendants of each element in the current set of matched
	 * elements.
	 * 
	 * @param Cssselector
	 * @return
	 */
	public eWebElement find(String Cssselector) {
		eWebElement ewebElement = findElements(By.cssSelector(Cssselector));
		return ewebElement;
	}

	/**
	 * Get all sibling of current element .
	 * 
	 * @return
	 */
	public eWebElement siblings() {
		return prevAll().nextAll();
	}

	/**
	 * Get immediate parent of current web element
	 * 
	 * @return
	 */
	public eWebElement parent() {

		eWebElement ewebElement = new eWebElement(firstElement().findElement(
				By.xpath("..")));
		return ewebElement;
	}

	/**
	 * Get the ancestors of the current elements, up to the matched element.
	 * 
	 * @param Xpath
	 * @return
	 */
	public eWebElement parentsUntil(String xpathExpression) {
		eWebElement ewebElement = findElements(By
				.xpath("ancestor-or-self::*[ancestor::" + xpathExpression + "]"));
		return ewebElement;
	}

	/**
	 * Get the all parents of current node in list .
	 * 
	 * @return
	 */
	public eWebElement parents() {
		eWebElement ewebElement = findElements(By.xpath("ancestor-or-self::*"));
		return ewebElement;
	}

	/**
	 * For each element in the set, get the first element that matches the
	 * selector by testing the element itself and traversing up through its
	 * ancestors in the DOM tree.
	 * 
	 * @param Xpath
	 * @return
	 */
	public eWebElement closet(String Xpath) {
		eWebElement ewebElement = new eWebElement(firstElement().findElement(
				By.xpath("ancestor-or-self::*" + Xpath + "[1]")));
		return ewebElement;
	}

	/**
	 * Get immediate child of current web element
	 * 
	 * @return
	 */
	public eWebElement child() {

		eWebElement ewebElement = new eWebElement(firstElement().findElement(
				By.xpath("*[1]")));
		return ewebElement;
	}

	/**
	 * Get immediate immediate of current web element
	 * 
	 * @return
	 */
	public eWebElement immediatechildren() {
		eWebElement ewebElement = new eWebElement(firstElement().findElement(
				By.xpath("*")));
		return ewebElement;
	}

	/**
	 * Get the children of the current elements, up to the matched element.
	 * 
	 * @param Xpath
	 * @return
	 */
	public eWebElement childrenUntil(String xpathExpression) {
		eWebElement ewebElement = findElements(By
				.xpath("descendant-or-self::*[descendant::" + xpathExpression
						+ "]"));

		return ewebElement;
	}

	/**
	 * Get the children of current node in list .
	 * 
	 * @return
	 */
	public eWebElement children() {
		eWebElement ewebElement = findElements(By
				.xpath("descendant-or-self::*"));
		return ewebElement;
	}

	/**
	 * get list of elements matched by .
	 * 
	 * @param by
	 * @return
	 */
	public eWebElement findElements(By by) {

		List<WebElement> webelements = firstElement().findElements(by);
		return new eWebElement(webelements);
	}

}
