package org.imaginea.test.automation.framework.util.selenium.ui;

import com.gargoylesoftware.htmlunit.Page;
import org.imaginea.test.automation.framework.TafTestClass;
import org.imaginea.test.automation.framework.dom.EWebElement;
import org.imaginea.test.automation.framework.pagemodel.Browser;
import org.imaginea.test.automation.framework.pagemodel.PageClass;
import org.imaginea.test.automation.framework.testclasses.PassingPageClassA;
import org.imaginea.test.automation.framework.util.Utilities;
import static org.mockito.Mockito.*;


import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by varunm on 24-02-2015.
 */
public class UtilitiesTest extends TafTestClass {

    Utilities utilities = new Utilities();

    @Mock private WebElement element;
    @Mock private EWebElement eWebElement;
    @Mock private PageClass page;
    @Mock private List<WebElement> elements;

    @BeforeMethod
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForElementPresentThrowsTimeoutExceptionWhenElementNotPresent(){
        when(element.getTagName()).thenThrow(NoSuchElementException.class);
        utilities.waitForElementPresent(browser.getDriver(), element, 1);
    }

    @Test(expectedExceptions = {UnreachableBrowserException.class})
    public void testWaitForElementPresentThrowsActualExceptionWhenWaiting(){
        when(element.getTagName()).thenThrow(new UnreachableBrowserException("Testing exception"));
        utilities.waitForElementPresent(browser.getDriver(), element, 1);
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForElementPresentConsumesActualExceptionWhenIgnored(){
        when(element.getTagName()).thenThrow(new UnreachableBrowserException("Testing exception"));
        utilities.waitForElementPresent(browser.getDriver(), element, 1, UnreachableBrowserException.class);
    }

    @Test
    public void testWaitForElementPresentIsWorking(){
        when(element.getTagName()).thenReturn("div");
        utilities.waitForElementPresent(browser.getDriver(), element, 1);
    }

    @Test(expectedExceptions = {UnreachableBrowserException.class})
    public void testWaitForElementVisibleThrowsActualExceptionWhenWaiting(){
        when(element.isDisplayed()).thenThrow(new UnreachableBrowserException("Testing exception"));
        utilities.waitForElementToBeVisible(browser.getDriver(), element, 1);
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForElementVisibleConsumesActualExceptionWhenIgnored(){
        when(element.isDisplayed()).thenThrow(new UnreachableBrowserException("Testing exception"));
        utilities.waitForElementToBeVisible(browser.getDriver(), element, 1, UnreachableBrowserException.class);
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForElementVisibleThrowsTimeoutExceptionWhenElementNotVisible(){
        when(element.isDisplayed()).thenReturn(false);
        utilities.waitForElementToBeVisible(browser.getDriver(), element, 1);
    }

    @Test
    public void testWaitForElementVisibleIsWorking(){
        when(element.isDisplayed()).thenReturn(true);
        utilities.waitForElementToBeVisible(browser.getDriver(), element, 1);
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForListOfElementPresentThrowsTimeoutExceptionWhenElementNotPresent(){
        when(elements.size()).thenReturn(0);
        utilities.waitForElementPresent(browser.getDriver(), elements, 1);
    }

    @Test(expectedExceptions = {UnreachableBrowserException.class})
    public void testWaitForListOfElementPresentThrowsActualExceptionWhenWaiting(){
        when(elements.size()).thenThrow(new UnreachableBrowserException("Testing exception"));
        utilities.waitForElementPresent(browser.getDriver(), elements, 1);
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForListOfElementPresentConsumesActualExceptionWhenIgnored(){
        when(elements.size()).thenThrow(new UnreachableBrowserException("Testing exception"));
        utilities.waitForElementPresent(browser.getDriver(), elements, 1, UnreachableBrowserException.class);
    }

    @Test
    public void testWaitForListOfElementsPresentIsWorking(){
        when(elements.size()).thenReturn(1);
        utilities.waitForElementPresent(browser.getDriver(), elements, 1);
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForEWebElementPresentThrowsTimeoutExceptionWhenElementNotPresent(){
        when(eWebElement.getTagName()).thenThrow(NoSuchElementException.class);
        utilities.waitForElementPresent(browser.getDriver(), eWebElement, 1);
    }

    @Test(expectedExceptions = {UnreachableBrowserException.class})
    public void testWaitForEWebElementPresentThrowsActualExceptionWhenWaiting(){
        when(eWebElement.getTagName()).thenThrow(new UnreachableBrowserException("Testing exception"));
        utilities.waitForElementPresent(browser.getDriver(), eWebElement, 1);
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForEWebElementPresentConsumesActualExceptionWhenIgnored(){
        when(eWebElement.getTagName()).thenThrow(new UnreachableBrowserException("Testing exception"));
        utilities.waitForElementPresent(browser.getDriver(), eWebElement, 1, UnreachableBrowserException.class);
    }

    @Test
    public void testWaitForEWebElementPresentIsWorking(){
        when(eWebElement.getTagName()).thenReturn("div");
        utilities.waitForElementPresent(browser.getDriver(), eWebElement, 1);
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForEWebElementVisibleThrowsTimeoutExceptionWhenElementNotPresent(){
        when(eWebElement.isDisplayed()).thenThrow(new NoSuchElementException("Sample test"));
        utilities.waitForElementToBeVisible(browser.getDriver(), eWebElement, 1);
    }

    @Test(expectedExceptions = {UnreachableBrowserException.class})
    public void testWaitForEWebElementVisibleThrowsActualExceptionWhenWaiting(){
        when(eWebElement.isDisplayed()).thenThrow(new UnreachableBrowserException("Testing exception"));
        utilities.waitForElementToBeVisible(browser.getDriver(), eWebElement, 1);
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForEWebElementVisibleConsumesActualExceptionWhenIgnored(){
        when(eWebElement.isDisplayed()).thenThrow(new UnreachableBrowserException("Testing exception"));
        utilities.waitForElementToBeVisible(browser.getDriver(), eWebElement, 1, UnreachableBrowserException.class);
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForEWebElementVisibleThrowsTimeoutExceptionWhenElementNotVisible(){
        when(eWebElement.isDisplayed()).thenReturn(false);
        utilities.waitForElementToBeVisible(browser.getDriver(), eWebElement, 1);
    }

    @Test
    public void testWaitForEWebElementVisibleIsWorking(){
        when(eWebElement.isDisplayed()).thenReturn(true);
        utilities.waitForElementToBeVisible(browser.getDriver(), eWebElement, 1);
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForPageThrowsTimeoutExceptionWhenAtThrowsNoSuchElementException(){
        when(page.at()).thenThrow(new NoSuchElementException("Sample exception"));
        utilities.waitForPage(browser, page, 1);
    }

    @Test(expectedExceptions = {UnreachableBrowserException.class})
    public void testWaitForPageThrowsActualExceptionWhenWaiting(){
        when(page.at()).thenThrow(new UnreachableBrowserException("Sample exception"));
        utilities.waitForPage(browser, page, 1);
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForPageConsumeActualExceptionWhenIgnored(){
        when(page.at()).thenThrow(new UnreachableBrowserException("Sample test"));
        utilities.waitForPage(browser, page, 1, UnreachableBrowserException.class);
    }

    @Test
    public void testWaitForPageObjectIsWorking() {
        when(page.at()).thenReturn(true);
        utilities.waitForPage(browser, page, 1);
    }


    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForPageClassThrowsTimeoutExceptionWhenAtThrowsNoSuchElementException(){
        Browser mockBrowser = mock(Browser.class);
        Class<? extends PageClass> pageClass = PassingPageClassA.class;
        when(mockBrowser.getDriver()).thenReturn(browser.getDriver());
        when(mockBrowser.isAt(pageClass)).thenThrow(new NoSuchElementException("Sample exception"));

        utilities.waitForPage(mockBrowser, pageClass, 1);
    }

    @Test(expectedExceptions = {UnreachableBrowserException.class})
    public void testWaitForPageClassThrowsActualExceptionWhenWaiting(){
        Browser mockBrowser = mock(Browser.class);
        Class<? extends PageClass> pageClass = PassingPageClassA.class;
        when(mockBrowser.getDriver()).thenReturn(browser.getDriver());
        when(mockBrowser.isAt(pageClass)).thenThrow(new UnreachableBrowserException("Sample exception"));

        utilities.waitForPage(mockBrowser, pageClass, 1);
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForPageClassConsumeActualExceptionWhenIgnored(){
        Browser mockBrowser = mock(Browser.class);
        Class<? extends PageClass> pageClass = PassingPageClassA.class;
        when(mockBrowser.getDriver()).thenReturn(browser.getDriver());
        when(mockBrowser.isAt(pageClass)).thenThrow(new UnreachableBrowserException("Sample exception"));

        utilities.waitForPage(mockBrowser, pageClass, 1, UnreachableBrowserException.class);
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForPageClassThrowsTimeoutExceptionWhenPageNotPresent(){
        Browser mockBrowser = mock(Browser.class);
        Class<? extends PageClass> pageClass = PassingPageClassA.class;
        when(mockBrowser.getDriver()).thenReturn(browser.getDriver());
        when(mockBrowser.isAt(pageClass)).thenReturn(false);

        utilities.waitForPage(mockBrowser, pageClass, 1);
    }

    @Test
    public void testWaitForPageClassObjectIsWorking() {
        Browser mockBrowser = mock(Browser.class);
        Class<? extends PageClass> pageClass = PassingPageClassA.class;
        when(mockBrowser.getDriver()).thenReturn(browser.getDriver());
        when(mockBrowser.isAt(pageClass)).thenReturn(true);

        utilities.waitForPage(mockBrowser, pageClass, 1);
    }



}
