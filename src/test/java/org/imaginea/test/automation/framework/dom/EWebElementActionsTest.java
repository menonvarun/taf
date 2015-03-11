package org.imaginea.test.automation.framework.dom;

import org.imaginea.test.automation.framework.exceptions.UnexpectedPageException;
import org.imaginea.test.automation.framework.pagemodel.Browser;
import org.imaginea.test.automation.framework.pagemodel.PageClass;
import org.imaginea.test.automation.framework.testclasses.FailingPageClassA;
import org.imaginea.test.automation.framework.testclasses.FailingPageClassB;
import org.imaginea.test.automation.framework.testclasses.PassingPageClassA;
import org.imaginea.test.automation.framework.testclasses.PassingPageClassB;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by varunm on 26-02-2015.
 */
public class EWebElementActionsTest {

    List<WebElement> elements;
    @Mock WebElement element1;
    @Mock WebElement element2;
    @Mock WebElement element3;
    @Mock WebElement element4;


    @BeforeMethod
    public  void setup(){
        MockitoAnnotations.initMocks(this);
        elements = new ArrayList<>();
        elements.add(element1);
        elements.add(element2);
    }

    @Test
    public void testClickFunction(){
        EWebElement eWebElement = new EWebElement(elements);
        eWebElement.click();
        verify(element1).click();
    }

    @Test
    public void testClickFunctionReturnFirstSuccessfulPageValidation(){
        EWebElement eWebElement = new EWebElement(new Browser(), elements);
        List<Class<? extends PageClass>> navigablePageClassesA = new ArrayList<>();
        navigablePageClassesA.add(PassingPageClassA.class);
        navigablePageClassesA.add(PassingPageClassB.class);

        eWebElement.setNavigablePageClasses(navigablePageClassesA);
        PageClass pageA = eWebElement.click();
        Assert.assertTrue(pageA instanceof PassingPageClassA);


        List<Class<? extends PageClass>> navigablePageClassesB = new ArrayList<>();
        navigablePageClassesB.add(FailingPageClassA.class);
        navigablePageClassesB.add(PassingPageClassB.class);

        eWebElement.setNavigablePageClasses(navigablePageClassesB);
        PageClass pageB = eWebElement.click();
        Assert.assertTrue(pageB instanceof PassingPageClassB);
    }

    @Test(expectedExceptions = {UnexpectedPageException.class}, expectedExceptionsMessageRegExp = "Browser is not on any of the specified pages.*" )
    public void testClickFunctionThrowsExceptionIfPageValidationFails(){
        EWebElement eWebElement = new EWebElement(new Browser(), elements);
        List<Class<? extends PageClass>> navigablePageClasses = new ArrayList<>();
        navigablePageClasses.add(FailingPageClassA.class);
        navigablePageClasses.add(FailingPageClassB.class);

        eWebElement.setNavigablePageClasses(navigablePageClasses);
        eWebElement.click();
    }

    @Test
    public void testClickFunctionPageValidationCanBeDisabledWhileUsingClick(){
        EWebElement eWebElement = new EWebElement(new Browser(), elements);
        List<Class<? extends PageClass>> navigablePageClasses = new ArrayList<>();
        navigablePageClasses.add(PassingPageClassA.class);
        navigablePageClasses.add(PassingPageClassB.class);

        eWebElement.setNavigablePageClasses(navigablePageClasses);
        PageClass page = eWebElement.click(false);
        Assert.assertNull(page, "Expected page object to be null when page navigation validation is disabled but found "
                + page + " object.");
    }

    @Test
    public void testClickAllFunction(){
        EWebElement eWebElement = new EWebElement(elements);
        eWebElement.clickAll();
        verify(element1).click();
        verify(element2).click();
    }

    @Test
    public void testClearFunction(){
        EWebElement eWebElement = new EWebElement(elements);
        eWebElement.clear();
        verify(element1).clear();
    }

    @Test
    public void testFirstElementFunction(){
        EWebElement eWebElement = new EWebElement(elements);
        Assert.assertEquals(eWebElement.firstElement(),element1);
    }

    @Test(expectedExceptions = {NoSuchElementException.class})
    public void testFirstElementFunctionThrowsExceptionWhenNoElementPresent(){
        EWebElement eWebElement = new EWebElement();
        eWebElement.firstElement();
    }

    @Test
    public void testGetAttributeFunction(){
        EWebElement eWebElement = new EWebElement(elements);
        eWebElement.getAttribute("attr");
        verify(element1).getAttribute("attr");
    }

    @Test
    public void testChildFunction(){
        EWebElement eWebElement = new EWebElement(elements);
        eWebElement.child();
        verify(element1).findElement(By.xpath("*[1]"));
    }

    @Test
    public void testChildrenFunction(){
        By selector = By
                .xpath("descendant-or-self::*");
        EWebElement eWebElement = new EWebElement(elements);
        eWebElement.children();
        verify(element1).findElements(selector);
    }

    @Test
    public void testTypeFunction(){
        EWebElement eWebElement = new EWebElement(elements);
        eWebElement.type("test");
        verify(element1).sendKeys("test");
    }

    @Test
    public void testFirstEWebElementFunction(){
        EWebElement eWebElement = new EWebElement(elements);
        EWebElement firstEWebElement = eWebElement.firstEWebElement();
        Assert.assertEquals(firstEWebElement.firstElement(),element1);
    }

    @Test(expectedExceptions = {NoEWebElementException.class})
    public void testFirstEWebElementFunctionThrowsException(){
        EWebElement eWebElement = new EWebElement();
        eWebElement.firstEWebElement();
    }

    @Test
    public void testLastEWebElementFunction(){
        EWebElement eWebElement = new EWebElement(elements);
        EWebElement lastEWebElement = eWebElement.lastEWebElement();
        Assert.assertEquals(lastEWebElement.firstElement(),element2);
    }

    @Test(expectedExceptions = {NoEWebElementException.class})
    public void testLastEWebElementFunctionThrowsException(){
        EWebElement eWebElement = new EWebElement();
        eWebElement.lastEWebElement();
    }
 }
