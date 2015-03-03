package org.imaginea.test.automation.framework.dom;

import org.imaginea.test.automation.framework.TafTestClass;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.imaginea.test.automation.framework.dom.filter.Filter.*;
import static org.imaginea.test.automation.framework.dom.filter.TextMatching.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by menonvarun on 04-12-2014.
 */
public class EWebElementTest extends TafTestClass{

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = browser.getDriver();
        driver.get(getHtmlFile("index.html"));
    }

    @Test
    public void testBasicEWebElementCreation(){
        List<WebElement> elements = driver.findElements(By.cssSelector("span"));
        EWebElement eWebElement = new EWebElement(elements);
        Assert.assertTrue(eWebElement.size()>0);
    }

    @Test
    public void testEmptyEWebElementCreation(){
        EWebElement eWebElement = new EWebElement();
        Assert.assertTrue(eWebElement.size()==0);
    }

    @Test
    public void testWithNameFilterCriteria(){
        List<WebElement> elements = driver.findElements(By.cssSelector("span"));
        EWebElement eWebElement = new EWebElement(elements).filter(withName("name"));
        Assert.assertEquals(eWebElement.size(), 1);
    }

    @Test
    public void testWhereNameWithAFilterCriteria(){
        List<WebElement> elements = driver.findElements(By.cssSelector("span"));
        EWebElement eWebElement = new EWebElement(elements).filter(whereName(contains("name")));
        Assert.assertEquals(eWebElement.size(), 4);
    }

    @Test
    public void testWithNameFilterCriteriaWhenNoElementFound(){
        List<WebElement> elements = driver.findElements(By.cssSelector("span"));
        EWebElement eWebElement = new EWebElement(elements).filter(withName("test"));
        Assert.assertEquals(eWebElement.size(), 0);
    }

    @Test
    public void testWhereNameWithAFilterCriteriaWhenNoElementFound(){
        List<WebElement> elements = driver.findElements(By.cssSelector("span"));
        EWebElement eWebElement = new EWebElement(elements).filter(whereName(contains("test")));
        Assert.assertEquals(eWebElement.size(), 0);
    }

    @Test
    public void testWithIdFilterCriteria(){
        List<WebElement> elements = driver.findElements(By.cssSelector("span"));
        EWebElement eWebElement = new EWebElement(elements).filter(withId("id"));
        Assert.assertEquals(eWebElement.size(), 1);
    }

    @Test
    public void testWhereIdWithAFilterCriteria(){
        List<WebElement> elements = driver.findElements(By.cssSelector("span"));
        EWebElement eWebElement = new EWebElement(elements).filter(whereId(contains("id")));
        Assert.assertEquals(eWebElement.size(), 4);
    }

    @Test
    public void testWithIdFilterCriteriaWhenNoElementFound(){
        List<WebElement> elements = driver.findElements(By.cssSelector("span"));
        EWebElement eWebElement = new EWebElement(elements).filter(withId("test"));
        Assert.assertEquals(eWebElement.size(), 0);
    }

    @Test
    public void testWhereIdWithAFilterCriteriaWhenNoElementFound(){
        List<WebElement> elements = driver.findElements(By.cssSelector("span"));
        EWebElement eWebElement = new EWebElement(elements).filter(whereId(contains("test")));
        Assert.assertEquals(eWebElement.size(), 0);
    }

    @Test
    public void testWithClassFilterCriteria(){
        List<WebElement> elements = driver.findElements(By.cssSelector("span"));
        EWebElement eWebElement = new EWebElement(elements).filter(withClass("small"));
        Assert.assertEquals(eWebElement.size(), 5);
    }

    @Test
    public void testWhereClassWithAFilterCriteria(){
        List<WebElement> elements = driver.findElements(By.cssSelector("span"));
        EWebElement eWebElement = new EWebElement(elements).filter(whereClass(contains("sma")));
        Assert.assertEquals(eWebElement.size(), 5);
    }

    @Test
    public void testWithClassFilterCriteriaWhenNoElementFound(){
        List<WebElement> elements = driver.findElements(By.cssSelector("span"));
        EWebElement eWebElement = new EWebElement(elements).filter(withClass("test"));
        Assert.assertEquals(eWebElement.size(), 0);
    }

    @Test
    public void testWhereClassWithAFilterCriteriaWhenNoElementFound(){
        List<WebElement> elements = driver.findElements(By.cssSelector("span"));
        EWebElement eWebElement = new EWebElement(elements).filter(whereClass(contains("test")));
        Assert.assertEquals(eWebElement.size(), 0);
    }

    @Test
    public void testWithTextFilterCriteria(){
        List<WebElement> elements = driver.findElements(By.cssSelector("span"));
        EWebElement eWebElement = new EWebElement(elements).filter(withText("Small 1"));
        Assert.assertEquals(eWebElement.size(), 1);
    }

    @Test
    public void testWhereTextWithAFilterCriteria(){
        List<WebElement> elements = driver.findElements(By.cssSelector("span"));
        EWebElement eWebElement = new EWebElement(elements).filter(whereText(contains("Small")));
        Assert.assertEquals(eWebElement.size(), 5);
    }

    @Test
    public void testWithTextFilterCriteriaWhenNoElementFound(){
        List<WebElement> elements = driver.findElements(By.cssSelector("span"));
        EWebElement eWebElement = new EWebElement(elements).filter(withText("test"));
        Assert.assertEquals(eWebElement.size(), 0);
    }

    @Test
    public void testWhereTextWithAFilterCriteriaWhenNoElementFound(){
        List<WebElement> elements = driver.findElements(By.cssSelector("span"));
        EWebElement eWebElement = new EWebElement(elements).filter(whereText(contains("test")));
        Assert.assertEquals(eWebElement.size(), 0);
    }

    @Test
    public void testWithAttributeFilterCriteria(){
        List<WebElement> elements = driver.findElements(By.cssSelector("input"));
        EWebElement eWebElement = new EWebElement(elements).filter(withAttribute("value","John"));
        Assert.assertEquals(eWebElement.size(), 4);
    }

    @Test
    public void testWhereAttributeWithAFilterCriteria(){
        List<WebElement> elements = driver.findElements(By.cssSelector("input"));
        EWebElement eWebElement = new EWebElement(elements).filter(whereAttribute("value",contains("Do")));
        Assert.assertEquals(eWebElement.size(), 1);
    }

    @Test
    public void testWithAttributeFilterCriteriaWhenNoElementFound(){
        List<WebElement> elements = driver.findElements(By.cssSelector("input"));
        EWebElement eWebElement = new EWebElement(elements).filter(withAttribute("value","test"));
        Assert.assertEquals(eWebElement.size(), 0);
    }

    @Test
    public void testWhereAttributeWithAFilterCriteriaWhenNoElementFound(){
        List<WebElement> elements = driver.findElements(By.cssSelector("input"));
        EWebElement eWebElement = new EWebElement(elements).filter(whereAttribute("value", contains("test")));
        Assert.assertEquals(eWebElement.size(), 0);
    }

    @Test
    public void testEWebElementCreationUsingASingleWebElement(){
        WebElement element = driver.findElement(By.cssSelector("input"));
        EWebElement eWebElement = new EWebElement(element);
        Assert.assertEquals(eWebElement.size(),1);
        Assert.assertEquals(eWebElement.firstElement(), element);
    }

    @Test
    public void testClickFunction(){
        List<WebElement> elements = new ArrayList<>();
        WebElement element1 = Mockito.mock(WebElement.class);
        WebElement element2 = Mockito.mock(WebElement.class);

        elements.add(element1);
        elements.add(element2);

        EWebElement eWebElement = new EWebElement(elements);
        eWebElement.click();
        Mockito.verify(element1).click();

    }
}
