package org.imaginea.test.automation.framework.util;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.imaginea.test.automation.framework.dom.EWebElement;
import org.imaginea.test.automation.framework.pagemodel.Browser;
import org.imaginea.test.automation.framework.pagemodel.PageClass;
import org.imaginea.test.automation.framework.util.selenium.ui.TafExpectedConditions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

/**
 * Util class containing some util methods to be used while automation.
 * @author  Varun Menon
 *
 */
public class Utilities {
	private static int DEFAULT_TIMEOUT =60; /*in seconds*/
	private static int DEFAULT_SLEEP_INTERVAL =500; /*in milliseconds*/

	/**
	 * Util method to wait for the element to be present on the page.
	 * This method should be used while using Page Object Factory model of locating strategy provided by the framework.
	 * This method will wait for the default timeout of 60sec
	 * @param driver {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class 
	 * needs it, it have to be provided.
	 * @param element  {@link WebElement} object of the element that we have to wait for to be present. 
	 */
	public void waitForElementPresent(WebDriver driver, WebElement element){
		this.waitForElementPresent(driver, element,DEFAULT_TIMEOUT);
	}
	
	/**
	 * Util method to wait for the element to be present on the page.
	 * This method should be used while using Page Object Factory model of locating strategy provided by the framework.
	 * @param driver  {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class 
	 * needs it, it have to be provided.
	 * @param element  {@link WebElement} object of the element that we have to wait for to be present. 
	 * @param timeout  Time to wait for.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default NoSuchElementException is ignored for this method.
	 */
	public void waitForElementPresent(WebDriver driver, WebElement element, long timeout, Class<? extends Throwable>... ignoreExceptions){
        waitFor(driver, timeout, TafExpectedConditions.presenceOfElement(element), ignoreExceptions);
	}

    /**
     * Util method to wait for the element to be present on the page.
     * This method should be used while using Page Object Factory model of locating strategy provided by the framework.
     * @param driver  {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class
     * needs it, it have to be provided.
     * @param element  {@link WebElement} object of the element that we have to wait for to be present.
     * @param timeout  Time to wait for.
     * @param sleepInterval The duration in milliseconds to sleep between polls.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default NoSuchElementException is ignored for this method.
     */
    public void waitForElementPresent(WebDriver driver, WebElement element, long timeout, long sleepInterval, Class<? extends Throwable>... ignoreExceptions){
        waitFor(driver, timeout, sleepInterval, TafExpectedConditions.presenceOfElement(element), ignoreExceptions);
    }

    /**
     * Util method to wait for the element to be present on the page.
     * This method should be used while using Page Object Factory model of locating strategy provided by the framework.
     * This method will wait for the default timeout of 60sec
     * @param driver {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class
     * needs it, it have to be provided.
     * @param eWebElement  {@link EWebElement} object of the element that we have to wait for to be present.
     */
    public void waitForElementPresent(WebDriver driver, EWebElement eWebElement){
        waitForElementPresent(driver, eWebElement, DEFAULT_TIMEOUT);
    }

    /**
     * Util method to wait for the element to be present on the page.
     * This method should be used while using Page Object Factory model of locating strategy provided by the framework.
     * @param driver  {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class
     * needs it, it have to be provided.
     * @param eWebElement  {@link EWebElement} object of the element that we have to wait for to be present.
     * @param timeout  Time to wait for.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default NoSuchElementException is ignored for this method.
     */
    public void waitForElementPresent(WebDriver driver, EWebElement eWebElement, long timeout, Class<? extends Throwable>... ignoreExceptions){
        waitForElementPresent(driver, eWebElement, timeout, DEFAULT_SLEEP_INTERVAL, ignoreExceptions);
    }

    /**
     * Util method to wait for the element to be present on the page.
     * This method should be used while using Page Object Factory model of locating strategy provided by the framework.
     * @param driver  {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class
     * needs it, it have to be provided.
     * @param eWebElement  {@link EWebElement} object of the element that we have to wait for to be present.
     * @param timeout  Time to wait for.
     * @param sleepInterval The duration in milliseconds to sleep between polls.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default NoSuchElementException is ignored for this method.
     */
    public void waitForElementPresent(WebDriver driver, EWebElement eWebElement, long timeout, long sleepInterval, Class<? extends Throwable>... ignoreExceptions){
        waitFor(driver, timeout, sleepInterval, TafExpectedConditions.presenceOfElement(eWebElement), ignoreExceptions);
    }

	/**
	 * Util method to wait for the List of elements to be present on the page.
	 * This method should be used while using Page Object Factory model of locating strategy provided by the framework.
	 * This method will wait for the default timeout of 60sec
	 * @param driver  {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class 
	 * needs it, it have to be provided.
	 * @param elements  {@link List} of {@link WebElement} object of elements that we have to wait for to be present.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default NoSuchElementException is ignored for this method.
	 */
	public void waitForElementPresent(WebDriver driver, List<WebElement> elements, Class<? extends Throwable>... ignoreExceptions){
		this.waitForElementPresent(driver, elements,DEFAULT_TIMEOUT, ignoreExceptions);
	}
	
	/**
	 * Util method to wait for the List of elements to be present on the page.
	 * This method should be used while using Page Object Factory model of locating strategy provided by the framework.
	 * @param driver  {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class 
	 * needs it, it have to be provided.
	 * @param elements  {@link List} of {@link WebElement} object of elements that we have to wait for to be present. 
	 * @param timeout  Time to wait for.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default NoSuchElementException is ignored for this method.
	 */
    public void waitForElementPresent(WebDriver driver, List<WebElement> elements, long timeout, Class<? extends Throwable>... ignoreExceptions){
        waitFor(driver,timeout,TafExpectedConditions.presenceOfElements(elements),ignoreExceptions);
    }

    /**
     * Util method to wait for the List of elements to be present on the page.
     * This method should be used while using Page Object Factory model of locating strategy provided by the framework.
     * @param driver  {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class
     * needs it, it have to be provided.
     * @param elements  {@link List} of {@link WebElement} object of elements that we have to wait for to be present.
     * @param timeout  Time to wait for.
     * @param sleepInterval The duration in milliseconds to sleep between polls.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default NoSuchElementException is ignored for this method.
     */
    public void waitForElementPresent(WebDriver driver, List<WebElement> elements,long timeout, long sleepInterval, Class<? extends Throwable>... ignoreExceptions){
        waitFor(driver, timeout, sleepInterval, TafExpectedConditions.presenceOfElements(elements), ignoreExceptions);
    }

    /**
     * Util method to wait for the element to be visible on the page.
     * This method should be used while using Page Object Factory model of locating strategy provided by the framework.
     * This method will wait for the default timeout of 60sec
     * @param driver  {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class
     * needs it, it have to be provided.
     * @param element {@link WebElement} object that we have to wait for to be visible.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default NoSuchElementException is ignored for this method.
     */
    public void waitForElementToBeVisible(WebDriver driver, WebElement element, Class<? extends Throwable>... ignoreExceptions){
        waitForElementToBeVisible(driver, element, DEFAULT_TIMEOUT, ignoreExceptions);
    }

    /**
     * Util method to wait for the element to be visible on the page.
     * This method should be used while using Page Object Factory model of locating strategy provided by the framework.
     * This method will wait for the default timeout of 60sec
     * @param driver  {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class
     * needs it, it have to be provided.
     * @param element {@link WebElement} object that we have to wait for to be visible.
     * @param timeout  Time to wait for.
     */
    public void waitForElementToBeVisible(WebDriver driver, WebElement element, long timeout, Class<? extends Throwable>... ignoreExceptions){
        waitForElementToBeVisible(driver, element, timeout, DEFAULT_SLEEP_INTERVAL, ignoreExceptions);
    }

    /**
     * Util method to wait for the element to be visible on the page.
     * This method should be used while using Page Object Factory model of locating strategy provided by the framework.
     * This method will wait for the default timeout of 60sec
     * @param driver  {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class
     * needs it, it have to be provided.
     * @param element {@link WebElement} object that we have to wait for to be visible.
     * @param timeout  Time to wait for.
     * @param sleepInterval The duration in milliseconds to sleep between polls.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default NoSuchElementException is ignored for this method.
     */
    public void waitForElementToBeVisible(WebDriver driver, WebElement element, long timeout, long sleepInterval, Class<? extends Throwable>... ignoreExceptions){
        waitFor(driver, timeout, sleepInterval, ExpectedConditions.visibilityOf(element), addExceptions(ignoreExceptions, NoSuchElementException.class));
    }

    /**
     * Util method to wait for the element to be visible on the page.
     * This method should be used while using Page Object Factory model of locating strategy provided by the framework.
     * This method will wait for the default timeout of 60sec
     * @param driver  {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class
     * needs it, it have to be provided.
     * @param eWebElement {@link EWebElement} object that we have to wait for to be visible.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default NoSuchElementException is ignored for this method.
     */
    public void waitForElementToBeVisible(WebDriver driver, EWebElement eWebElement, Class<? extends Throwable>... ignoreExceptions){
        waitForElementToBeVisible(driver, eWebElement, DEFAULT_TIMEOUT, ignoreExceptions);
    }

    /**
     * Util method to wait for the element to be visible on the page.
     * This method should be used while using Page Object Factory model of locating strategy provided by the framework.
     * This method will wait for the default timeout of 60sec
     * @param driver  {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class
     * needs it, it have to be provided.
     * @param eWebElement {@link EWebElement} object that we have to wait for to be visible.
     * @param timeout  Time to wait for.
     */
    public void waitForElementToBeVisible(WebDriver driver, EWebElement eWebElement, long timeout, Class<? extends Throwable>... ignoreExceptions){
        waitForElementToBeVisible(driver, eWebElement, timeout, DEFAULT_SLEEP_INTERVAL, ignoreExceptions);
    }

    /**
     * Util method to wait for the element to be visible on the page.
     * This method should be used while using Page Object Factory model of locating strategy provided by the framework.
     * This method will wait for the default timeout of 60sec
     * @param driver  {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class
     * needs it, it have to be provided.
     * @param eWebElement {@link EWebElement} object that we have to wait for to be visible.
     * @param timeout  Time to wait for.
     * @param sleepInterval The duration in milliseconds to sleep between polls.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default NoSuchElementException is ignored for this method.
     */
    public void waitForElementToBeVisible(WebDriver driver, EWebElement eWebElement, long timeout, long sleepInterval, Class<? extends Throwable>... ignoreExceptions){
        waitFor(driver, timeout, sleepInterval, TafExpectedConditions.visibilityOf(eWebElement), addExceptions(ignoreExceptions, NoSuchElementException.class));
    }

    /**
     * Util method to wait for the given expectedCondition object to return truth condition.
     * @param driver  {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class
     * needs it, it have to be provided.
     * @param expectedCondition {@link org.openqa.selenium.support.ui.ExpectedCondition} implementation object.
     * @param timeout  Time to wait for.
     * @param sleepInterval The duration in milliseconds to sleep between polls.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default no exceptions are ignored for this method.
     */
    public <T> T  waitFor(WebDriver driver, long timeout, long sleepInterval, ExpectedCondition<T> expectedCondition, Class<? extends Throwable>... ignoreExceptions){
        WebDriverWait wait = new WebDriverWait(driver, timeout, sleepInterval);
        wait.ignoreAll(Arrays.asList(ignoreExceptions));
        return (T)wait.until(expectedCondition);
    }

    /**
     * Util method to wait for the given expectedCondition object to return truth condition.
     * This method will by default wait for 60 sec with a polling interval of 500 millisec
     * @param driver  {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class
     * needs it, it have to be provided.
     * @param expectedCondition {@link org.openqa.selenium.support.ui.ExpectedCondition} implementation object.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default no exceptions are ignored for this method.
     */
    public <T> T  waitFor(WebDriver driver, ExpectedCondition<T> expectedCondition, Class<? extends Throwable>... ignoreExceptions){
        return waitFor(driver,DEFAULT_TIMEOUT, DEFAULT_SLEEP_INTERVAL,expectedCondition,ignoreExceptions);
    }

    /**
     * Util method to wait for the given expectedCondition object to return truth condition.
     * This method will by wait for the given timeout in secs with a polling interval of 500 millisecs
     * @param driver  {@link WebDriver} object. This object is not used by the method but as {@link WebDriverWait} class
     * needs it, it have to be provided.
     * @param expectedCondition {@link org.openqa.selenium.support.ui.ExpectedCondition} implementation object.
     * @param timeout  Time to wait for.     *
     * @param ignoreExceptions Array of exceptions that has be ignored. By default no exceptions are ignored for this method.
     */
    public <T> T  waitFor(WebDriver driver, long timeout, ExpectedCondition<T> expectedCondition, Class<? extends Throwable>... ignoreExceptions){
        return waitFor(driver,timeout, DEFAULT_SLEEP_INTERVAL,expectedCondition,ignoreExceptions);
    }

	/**
	 * Util method for waiting for the page object model page to appear or displayed. 
	 * This verification is done on the basis of the {@link PageClass#at()} method implemented by the implementing Page Object class.
	 * @param browser  Browser object of the current page or test class from where this method is call'd
	 * @param pageClass  Page class extending {@link PageClass} for which {@link WebDriver} should wait.
	 * @param timeout  Time to wait for.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default NoSuchElementException is ignored for this method
	 */
	public void waitForPage(Browser browser, Class<?> pageClass, long timeout, Class<? extends Throwable>... ignoreExceptions){
        waitForPage(browser, pageClass, timeout, DEFAULT_SLEEP_INTERVAL, ignoreExceptions);
	}

    /**
     * Util method for waiting for the page object model page to appear or displayed.
     * This verification is done on the basis of the {@link PageClass#at()} method implemented by the implementing Page Object class.
     * @param browser  Browser object of the current page or test class from where this method is call'd
     * @param pageClass  Page class extending {@link PageClass} for which {@link WebDriver} should wait.
     * @param timeout  Time to wait for.
     * @param sleepInterval The duration in milliseconds to sleep between polls.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default NoSuchElementException is ignored for this method
     */
    public void waitForPage(Browser browser, Class<?> pageClass, long timeout, long sleepInterval, Class<? extends Throwable>... ignoreExceptions){
        waitFor(browser.getDriver(), timeout, sleepInterval, TafExpectedConditions.presenceOfPage(browser, pageClass), addExceptions(ignoreExceptions, NoSuchElementException.class));
    }
	
	/**
	 * Util method for waiting for the page object model page to appear or displayed. 
	 * This verification is done on the basis of the {@link PageClass#at()} method implemented by the implementing Page Object class.
	 * Uses default timeout of 60 sec.
	 * @param browser  Browser object of the current page or test class from where this method is call'd
	 * @param pageClass  Page class extending {@link PageClass} for which {@link WebDriver} should wait.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default NoSuchElementException is ignored for this method
	 */	
	public void waitForPage(Browser browser, Class<?> pageClass, Class<? extends Throwable>... ignoreExceptions ){
		waitForPage(browser, pageClass, DEFAULT_TIMEOUT, DEFAULT_SLEEP_INTERVAL, ignoreExceptions);
	}

    /**
     * Util method for waiting for the page object model page to appear or displayed.
     * This verification is done on the basis of the {@link PageClass#at()} method implemented by the implementing Page Object class.
     * @param browser  Browser object of the current page or test class from where this method is call'd
     * @param pageObject  Page object of the Page class that is extending {@link PageClass} for which {@link WebDriver} should wait.
     * @param timeout  Time to wait for.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default NoSuchElementException is ignored for this method
     */
    public void waitForPage(Browser browser, PageClass pageObject, long timeout, Class<? extends Throwable>... ignoreExceptions){
        waitForPage(browser, pageObject, timeout, DEFAULT_SLEEP_INTERVAL, ignoreExceptions);
    }

    /**
     * Util method for waiting for the page object model page to appear or displayed.
     * This verification is done on the basis of the {@link PageClass#at()} method implemented by the implementing Page Object class.
     * @param browser  Browser object of the current page or test class from where this method is call'd
     * @param pageObject  Page object of the Page class that is extending {@link PageClass} for which {@link WebDriver} should wait.
     * @param timeout  Time to wait for.
     * @param sleepInterval The duration in milliseconds to sleep between polls.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default NoSuchElementException is ignored for this method
     */
    public void waitForPage(Browser browser, PageClass pageObject, long timeout, long sleepInterval, Class<? extends Throwable>... ignoreExceptions){
        waitFor(browser.getDriver(), timeout, sleepInterval, TafExpectedConditions.presenceOfPage(browser, pageObject), addExceptions(ignoreExceptions, NoSuchElementException.class));
    }

    /**
     * Util method for waiting for the page object model page to appear or displayed.
     * This verification is done on the basis of the {@link PageClass#at()} method implemented by the implementing Page Object class.
     * Uses default timeout of 60 sec.
     * @param browser  Browser object of the current page or test class from where this method is call'd
     * @param pageObject  Page object of the Page class that is extending {@link PageClass} for which {@link WebDriver} should wait.
     * @param ignoreExceptions Array of exceptions that has be ignored. By default NoSuchElementException is ignored for this method
     */
    public void waitForPage(Browser browser, PageClass pageObject, Class<? extends Throwable>... ignoreExceptions ){
        waitForPage(browser, pageObject, DEFAULT_TIMEOUT, DEFAULT_SLEEP_INTERVAL, ignoreExceptions);
    }


    /**
     * Utility to load a file from the resource directory
     * @param fileName or filepath of the file under resource directory
     * @return File object of the said file
     */
	public File evaluateFileFromResourceDirectory(String fileName) {
		URL fileStream = this.toString().getClass().getResource(fileName);
		if(fileStream == null)
			return null;
		else
			return new File(fileStream.getPath());
	}

    private Class<? extends Throwable>[] addExceptions(Class<? extends Throwable>[] ignoredExceptions, Class<? extends Throwable>... addExceptions) {
        int ignoredExceptionsLength = ignoredExceptions.length;
        int addExceptionsLength = addExceptions.length;
        Class<? extends Throwable>[] totalExceptions = new Class[ignoredExceptionsLength + addExceptionsLength];
        System.arraycopy(ignoredExceptions, 0, totalExceptions, 0, ignoredExceptionsLength);
        System.arraycopy(addExceptions, 0, totalExceptions, ignoredExceptionsLength, addExceptionsLength);
        return totalExceptions;
    }
}
