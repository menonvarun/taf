package org.imaginea.test.automation.framework.dom;

import java.util.ArrayList;
import java.util.List;

import org.imaginea.test.automation.framework.exceptions.UnexpectedPageException;
import org.imaginea.test.automation.framework.pagemodel.Browser;
import org.imaginea.test.automation.framework.pagemodel.PageClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import org.imaginea.test.automation.framework.dom.filter.Filter;
import org.imaginea.test.automation.framework.dom.filter.FilterType;
import org.openqa.selenium.WebElement;

public class EWebElement {
    private String name = "Extended WebElement";
    private Browser browser;
	private List<WebElement> elements;
    private List<Class<? extends PageClass>> navigablePageClasses = new ArrayList<>();
    boolean required = true;
	
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

    public EWebElement(Browser browser, List<WebElement> list) {
        this.browser = browser;
        this.elements = list;
    }

    public EWebElement(Browser browser, WebElement element) {
        this.elements = new ArrayList<WebElement>();
        elements.add(element);
        this.browser = browser;
    }

        public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public List<Class<? extends PageClass>> getNavigablePageClasses() {
        return navigablePageClasses;
    }

    public void setNavigablePageClasses(List<Class<? extends PageClass>> navigablePageClasses) {
        this.navigablePageClasses = navigablePageClasses;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

	/**
	 * Click on first web element represented by the said extended webelement and validates whether
     * Page gets navigated to any of the expected navigable page classes mentioned as part of the ElementOptions 
     * Annotation or set explicitly using the setNavigablePageClasses() method.
     *
     * @return The page class object for which the validation was successful else null if no navigable page classes were defined.
	 */
	public <T extends PageClass> T click() {
		return click(true);
	}

    /**
     * Click on first web element represented by the said extended webelement and validates whether
     * Page gets navigated to any of the expected navigable page classes mentioned as part of the ElementOptions
     * Annotation or set explicitly using the setNavigablePageClasses() method.
     *
     * @param doPageValidation set it to false in case you dont want any navigable page validation to be done.
     * @return The page class object for which the validation was successful else null if no navigable page classes were defined.
     */
    public <T extends PageClass> T click(boolean doPageValidation) {
        firstElement().click();
        if(doPageValidation)
            return validatePageNavigation(navigablePageClasses);
        return null;
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
	public WebElement firstElement(){
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
		throw new NoEWebElementException("No element was found in the given EWebElement");
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
	 * @return WebElement object at the said index
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
        throw new NoEWebElementException("No element was found at index "+ index +"in the given EWebElement");
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
	 * elements.
     * @return EWebElement object containing all the previous elements
	 */
	public EWebElement prevAll() {
		EWebElement eWebElement = find(By.xpath("preceding-sibling::*"));
		return eWebElement;
	}

	/**
	 * Get the immediately preceding sibling.
	 * 
	 * @return EWebElement object containing the previous element
	 */
	public EWebElement prev() {
		EWebElement eWebElement = new EWebElement(firstElement().findElement(
				By.xpath("preceding-sibling::*[1]")));
		return eWebElement;
	}

	/**
	 * Get the immediately following sibling.
	 * 
	 * @return EWebElement object containing the next element
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
	 * @return EWebElement object containing next elements based on the xpathExpression criteria
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
	 * @return EWebElement object containing previous elements based on the xpathExpression criteria
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
	 * @return EWebElement object containing under the said set of elements that matches the given css criteria
	 */
	public EWebElement find(String cssSelector) {
		EWebElement eWebElement = find(By.cssSelector(cssSelector));
		return eWebElement;
	}

	/**
	 * Get all sibling of current element .
	 * 
	 * @return EWebElement object containing all the siblings
	 */
	public EWebElement siblings() {
		return prevAll().nextAll();
	}

	/**
	 * Get immediate parent of current web element
	 * 
	 * @return EWebElement object containing the parent element
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
	 * @return EWebElement object containing all parents until the given xpathExpression
	 */
	public EWebElement parentsUntil(String xpathExpression) {
		EWebElement eWebElement = find(By
                .xpath("ancestor-or-self::*[ancestor::" + xpathExpression + "]"));
		return eWebElement;
	}

	/**
	 * Get the all parents of current node in list .
	 * 
	 * @return EWebElement object containing parents of all the elements in represented byt this object
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
	 * @return EWebElement object containing elements closest to the given xpathExpression
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
	 * @return EWebElement object containing all the child elements until the give xpathExpression
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
     * @return EWebElement object
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
     * @param keysToSend The text that needs to be entered
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

    private <T extends PageClass> T validatePageNavigation(List<Class<? extends PageClass>> pageClassesToValidate){
        if(this.browser != null) {
            for (Class<? extends PageClass> pageClass : pageClassesToValidate) {
                if (browser.isAt(pageClass))
                    return browser.getPageObject(pageClass);
            }
            if(!pageClassesToValidate.isEmpty()){
                throw new UnexpectedPageException("Browser is not on any of the specified pages: " + pageClassesToValidate);
            }
        }
        return null;
    }
}
