package org.imaginea.test.automation.framework.dom;

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
