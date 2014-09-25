package com.menonvarun.test.automation.framework.dom;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EWebElement {

	List<WebElement> elements;
	
	public EWebElement() {
		this.elements = new ArrayList<WebElement>();
	}

	public EWebElement(List<WebElement> list) {
		this.elements = list;
	}

	public EWebElement(WebElement element) {
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
	 * Type onto the said element if its an input box
	 * 
	 * @param value
	 */
	public void type(String value) {
		firstElement().sendKeys(value);
	}

	/**
	 * Get the first web element
	 */
	public WebElement firstElement() throws NoEWebElementException {
		if (elements.size() > 0)
			return elements.get(0);
		throw new NoEWebElementException("No element was found in the given EWebElement");
	}

	/**
	 * Get first extended eWeblement
	 * 
	 * @return
	 */
	public EWebElement firsteElement() {
		if (elements.size() > 0)
			return new EWebElement(elements.get(0));
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
	public EWebElement lasteElement() {
		if (elements.size() > 0) {
			return new EWebElement(elements.get(elements.size() - 1));
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
	public EWebElement ntheWebElement(int index) {
		if (elements.size() >= index) {
			return new EWebElement(elements.get(index));
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
	public EWebElement geteWebElement(WebElement webelement) {
		EWebElement eWebElement = new EWebElement(webelement);
		return eWebElement;
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
	public EWebElement nextAll() {

		EWebElement eWebElements = findElements(By
				.xpath("following-sibling::*"));
		return eWebElements;
	}

	/**
	 * Get all preceding siblings of each element in the set of matched
	 * elements. * @return
	 */
	public EWebElement prevAll() {
		EWebElement eWebElement = findElements(By.xpath("preceding-sibling::*"));
		return eWebElement;
	}

	/**
	 * Get the immediately preceding sibling.
	 * 
	 * @return
	 */
	public EWebElement prev() {
		EWebElement eWebElement = new EWebElement(firstElement().findElement(
				By.xpath("preceding-sibling::*[1]")));
		return eWebElement;
	}

	/**
	 * Get the immediately following sibling.
	 * 
	 * @return
	 */
	public EWebElement next() {
		EWebElement eWebElement = new EWebElement(firstElement().findElement(
				By.xpath("following-sibling::*[1]")));
		return eWebElement;
	}

	/**
	 * Get all following siblings of each element up to .
	 * 
	 * @param xpathExpression
	 * @return
	 */
	public EWebElement nextUntil(String xpathExpression) {
		EWebElement eWebElement = findElements(By
				.xpath("following-sibling::*[following-sibling::"
						+ xpathExpression + "]"));
		return eWebElement;
	}

	/**
	 * Get all preceding siblings of each element up to .
	 * 
	 * @param xpathExpression
	 * @return
	 */
	public EWebElement prevUntil(String xpathExpression) {
		EWebElement eWebElement = findElements(By
				.xpath("preceding-sibling::*[preceding-sibling::"
						+ xpathExpression + "]"));
		return eWebElement;
	}

	/**
	 * Get the descendants of each element in the current set of matched
	 * elements.
	 * 
	 * @param Cssselector
	 * @return
	 */
	public EWebElement find(String Cssselector) {
		EWebElement eWebElement = findElements(By.cssSelector(Cssselector));
		return eWebElement;
	}

	/**
	 * Get all sibling of current element .
	 * 
	 * @return
	 */
	public EWebElement siblings() {
		return prevAll().nextAll();
	}

	/**
	 * Get immediate parent of current web element
	 * 
	 * @return
	 */
	public EWebElement parent() {

		EWebElement eWebElement = new EWebElement(firstElement().findElement(
				By.xpath("..")));
		return eWebElement;
	}

	/**
	 * Get the ancestors of the current elements, up to the matched element.
	 * 
	 * @param Xpath
	 * @return
	 */
	public EWebElement parentsUntil(String xpathExpression) {
		EWebElement eWebElement = findElements(By
				.xpath("ancestor-or-self::*[ancestor::" + xpathExpression + "]"));
		return eWebElement;
	}

	/**
	 * Get the all parents of current node in list .
	 * 
	 * @return
	 */
	public EWebElement parents() {
		EWebElement eWebElement = findElements(By.xpath("ancestor-or-self::*"));
		return eWebElement;
	}

	/**
	 * For each element in the set, get the first element that matches the
	 * selector by testing the element itself and traversing up through its
	 * ancestors in the DOM tree.
	 * 
	 * @param Xpath
	 * @return
	 */
	public EWebElement closet(String Xpath) {
		EWebElement eWebElement = new EWebElement(firstElement().findElement(
				By.xpath("ancestor-or-self::*" + Xpath + "[1]")));
		return eWebElement;
	}

	/**
	 * Get immediate child of current web element
	 * 
	 * @return
	 */
	public EWebElement child() {

		EWebElement eWebElement = new EWebElement(firstElement().findElement(
				By.xpath("*[1]")));
		return eWebElement;
	}

	/**
	 * Get immediate immediate of current web element
	 * 
	 * @return
	 */
	public EWebElement immediatechildren() {
		EWebElement eWebElement = new EWebElement(firstElement().findElement(
				By.xpath("*")));
		return eWebElement;
	}

	/**
	 * Get the children of the current elements, up to the matched element.
	 * 
	 * @param Xpath
	 * @return
	 */
	public EWebElement childrenUntil(String xpathExpression) {
		EWebElement eWebElement = findElements(By
				.xpath("descendant-or-self::*[descendant::" + xpathExpression
						+ "]"));

		return eWebElement;
	}

	/**
	 * Get the children of current node in list .
	 * 
	 * @return
	 */
	public EWebElement children() {
		EWebElement eWebElement = findElements(By
				.xpath("descendant-or-self::*"));
		return eWebElement;
	}

	/**
	 * get list of elements matched by .
	 * 
	 * @param by
	 * @return
	 */
	public EWebElement findElements(By by) {

		List<WebElement> webelements = firstElement().findElements(by);
		return new EWebElement(webelements);
	}

}
