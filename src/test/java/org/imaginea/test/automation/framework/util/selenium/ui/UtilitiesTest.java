package org.imaginea.test.automation.framework.util.selenium.ui;

import org.imaginea.test.automation.framework.TafTestClass;
import org.imaginea.test.automation.framework.pagemodel.PageClass;
import org.imaginea.test.automation.framework.util.Utilities;
import static org.mockito.Mockito.*;


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

    @MockitoAnnotations.Mock private WebElement element;
    @MockitoAnnotations.Mock private PageClass page;
    @MockitoAnnotations.Mock private List<WebElement> elements;

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

}
