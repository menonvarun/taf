package org.imaginea.test.automation.framework.dom;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import org.imaginea.test.automation.framework.dom.filter.Filter;
import org.imaginea.test.automation.framework.dom.filter.FilterType;
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
	 * Click on first web element represented by the said extended webelement
	 */
	public void click() {
		firstElement().click();
	}

    /**
	 * Click on all webelement represented by the said extended webelement
	 */
	public void clickAll() {
		for (WebElement element : elements) {
			element.click();
		}
	}

	/**
	 * Type onto the first element if its an input box represented by the said extended webelement.
	 * 
	 * @param value that needs to be typed.
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
		throw new NoSuchElementException("No element was found in the given EWebElement");
	}

	/**
	 * Gets first extended eWeblement
	 * 
	 * @return The {@link EWebElement} for the first webelement  
	 */
	public EWebElement firstEWebElement() {
		if (elements.size() > 0)
			return new EWebElement(elements.get(0));
		throw new NoSuchElementException("No element was found in the given EWebElement");
	}

	/**
	 * Get last web element
	 * 
	 * @return the last {@link WebElement}
	 */
	public WebElement lastElement() {
		if (elements.size() > 0) {
			return elements.get(elements.size() - 1);
		}
		 throw new NoSuchElementException("No element was found in the given EWebElement");
	}

	/**
	 * Return the extended webelement for the last webelement in the said EWebelement
	 * 
	 * @return {@link EWebElement} for the last {@link WebElement}
	 */
	public EWebElement lastEWebElement() {
		if (elements.size() > 0) {
			return new EWebElement(elements.get(elements.size() - 1));
		}
		throw new NoEWebElementException("No element was found in the given EWebElement");
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
		throw new NoSuchElementException("No element was found at index "+ index +"in the given EWebElement");
	}

	/**
	 * Get extended webelement for the webelement present at the given index
	 * 
	 * @param index position of the webelement for which you need to get the extended webelement
	 * @return {@link EWebElement} for the webelement preset at the said index.
	 */
	public EWebElement nthEWebElement(int index) {
		if (elements.size() >= index) {
			return new EWebElement(elements.get(index));
		}
		return null;
	}

	/**
	 * Return the list of all webelements represented by this extended webelement
	 * 
	 * @return List of {@link WebElement}
	 */
	public List<WebElement> getWebElements() {
		return elements;
	}


	/**
	 * Get the size of webelements represented by the said extended webelement
	 * 
	 * @return {@link Integer}
	 */
	public int size() {
		return elements.size();
	}

	/**
	 * Get all following siblings of each element in the set of matched
	 * elements.
	 * 
	 * @return {@link EWebElement} representing all sibling of the first element
	 */
	public EWebElement nextAll() {

		EWebElement eWebElements = find(By
                .xpath("following-sibling::*"));
		return eWebElements;
	}

	/**
	 * Get all preceding siblings of each element in the set of matched
	 * elements. * @return
	 */
	public EWebElement prevAll() {
		EWebElement eWebElement = find(By.xpath("preceding-sibling::*"));
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
		EWebElement eWebElement = find(By
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
		EWebElement eWebElement = find(By
                .xpath("preceding-sibling::*[preceding-sibling::"
                        + xpathExpression + "]"));
		return eWebElement;
	}

	/**
	 * Get the descendants of each element in the current set of matched
	 * elements.
	 * 
	 * @param cssSelector
	 * @return
	 */
	public EWebElement find(String cssSelector) {
		EWebElement eWebElement = find(By.cssSelector(cssSelector));
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
	 * @param xpathExpression
	 * @return
	 */
	public EWebElement parentsUntil(String xpathExpression) {
		EWebElement eWebElement = find(By
                .xpath("ancestor-or-self::*[ancestor::" + xpathExpression + "]"));
		return eWebElement;
	}

	/**
	 * Get the all parents of current node in list .
	 * 
	 * @return
	 */
	public EWebElement parents() {
		EWebElement eWebElement = find(By.xpath("ancestor-or-self::*"));
		return eWebElement;
	}

	/**
	 * For each element in the set, get the first element that matches the
	 * selector by testing the element itself and traversing up through its
	 * ancestors in the DOM tree.
	 * 
	 * @param xpathSelector
	 * @return
	 */
	public EWebElement closest(String xpathSelector) {
		EWebElement eWebElement = new EWebElement(firstElement().findElement(
				By.xpath("ancestor-or-self::*" + xpathSelector + "[1]")));
		return eWebElement;
	}

	/**
	 * Get immediate child of current web element
	 * 
	 * @return EWebElement object of the child element
	 */
	public EWebElement child() {
		EWebElement eWebElement = new EWebElement(firstElement().findElement(
				By.xpath("*[1]")));
		return eWebElement;
	}

	/**
	 * Get immediate children of first WebElement stored in the EWebElement
	 * 
	 * @return EWebElement object of the immediate child element
	 */
	public EWebElement immediateChildren() {
		EWebElement eWebElement = new EWebElement(firstElement().findElement(
				By.xpath("*")));
		return eWebElement;
	}

	/**
	 * Get the children of the current elements, up to the matched element.
	 * 
	 * @param xpathExpression
	 * @return
	 */
	public EWebElement childrenUntil(String xpathExpression) {
		EWebElement eWebElement = find(By
                .xpath("descendant-or-self::*[descendant::" + xpathExpression
                        + "]"));

		return eWebElement;
	}

	/**
	 * Get the children of current node in list .
	 * 
	 * @return EWebElement object containing the child nodes.
	 */
	public EWebElement children() {
		EWebElement eWebElement = find(By
                .xpath("descendant-or-self::*"));
		return eWebElement;
	}

    /**
     * Get the descendants of each element in the current set of matched
     * elements using the given By locator.
     *
     * @param by By locator
     * @return
     */
	public EWebElement find(By by) {
    	List<WebElement> webElements = firstElement().findElements(by);
		return new EWebElement(webElements);
	}

    /**
     * Executes the submit action on the first element in the elements list stored inside.
     */
    public void submit() {
        firstElement().submit();
    }

    /**
     * Executes the send keys command on the first element in the elements list stored inside.
     * @param keysToSend
     */
    public void sendKeys(CharSequence... keysToSend) {
        firstElement().sendKeys();
    }

    /**
     * Executes the clear keys command on the first element in the elements list stored inside.
     */
    public void clear() {
        firstElement().clear();
    }

    /**
     * Returns the tag name of the first element in the elements list stored inside.
     * @return Tag name
     */
    public String getTagName() {
        return firstElement().getTagName();
    }

    /**
     * Returns the attribute value of the first element in the elements list stored inside.
     * @param name Name of the attribute for which the value has to be fetched.
     * @return The said attribute value if it exists or null
     */
    public String getAttribute(String name) {
        return firstElement().getAttribute(name);
    }

    /**
     * Executes the isSelected command on the first element in the elements list stored inside.
     * @return true if the element is selected else false.
     */
    public boolean isSelected() {
        return firstElement().isSelected();
    }

    /**
     * Executes the isEnabled command on the first element in the elements list stored inside.
     * @return true if the element is enabled else false.
     */
    public boolean isEnabled() {
        return firstElement().isEnabled();
    }

    /**
     * Returns the text of the first element in the elements list stored inside.
     * @return Text if it exists
     */
    public String getText() {
        return firstElement().getText();
    }

    /**
     * Executes the isDisplayed command on the first element in the elements list stored inside.
     * @return true if the element is displayed else false.
     */
    public boolean isDisplayed() {
        return firstElement().isDisplayed();
    }

    /**
     * Executes the isDisplayed command on all the elements list stored inside.
     * @return true if all the elements are displayed else false.
     */
    public boolean isAllDisplayed() {
        boolean displayed = true;
        for(WebElement element:elements){
            if(!element.isDisplayed()) {
                displayed = false;
                break;
            }
        }
        return displayed;
    }

    /**
     * Filters the elements stored inside this EWebElement based on given filters
     * @param filters An array filters that needs to be applied to the said list.
     * @return The new EWebElement based on the filtered elements else an empty EWebElement object.
     */
    public EWebElement filter(Filter... filters){
        List<WebElement> filteredElements = new ArrayList<>();
        for (WebElement element:elements){
            boolean elementMatches = true;
            for (Filter filter:filters) {
                FilterType filterType = filter.getFilterType();
                switch(filterType){
                    case ID:
                        String idValue = element.getAttribute("id");
                        if(idValue==null || !filter.getTextMatcher().matches(idValue)){
                            elementMatches = false;
                        }
                    break;
                    case NAME:
                        String nameValue = element.getAttribute("name");
                        if(nameValue==null || !filter.getTextMatcher().matches(nameValue)){
                            elementMatches = false;
                        }
                        break;
                    case CLASS:
                        String classValue = element.getAttribute("class");
                        if(classValue==null || !filter.getTextMatcher().matches(classValue)){
                            elementMatches = false;
                        }
                        break;
                    case ATTRIBUTE:
                        String attributeValue = element.getAttribute(filter.getAttributeName());
                        if(attributeValue==null || !filter.getTextMatcher().matches(attributeValue)){
                            elementMatches = false;
                        }
                        break;
                    case TEXT:
                        String textValue = element.getText();
                        if(textValue==null || !filter.getTextMatcher().matches(textValue)){
                            elementMatches = false;
                        }
                        break;
                }
                if(!elementMatches)
                    break;
            }
            if (elementMatches)
                filteredElements.add(element);
        }
        return new EWebElement(filteredElements);
    }
}
