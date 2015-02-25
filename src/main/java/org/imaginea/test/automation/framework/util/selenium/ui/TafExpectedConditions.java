package org.imaginea.test.automation.framework.util.selenium.ui;


import org.imaginea.test.automation.framework.pagemodel.Browser;
import org.imaginea.test.automation.framework.pagemodel.PageClass;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

/**
 * Created by varunm on 24-02-2015.
 */
public class TafExpectedConditions {

    public static ExpectedCondition<Boolean> presenceOfElement(
            final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try{
                    element.getTagName();
                }catch(NoSuchElementException e){
                    return false;
                }
                return true;
            }

            @Override
            public String toString() {
                return "presence of element located by: " + element;
            }
        };
    }

    public static ExpectedCondition<Boolean> presenceOfElements(
            final List<WebElement> elements) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                if(elements.size()>0)
                    return true;
                return false;
            }

            @Override
            public String toString() {
                return "presence of element located by: " + elements;
            }
        };
    }

    public static ExpectedCondition<Boolean> presenceOfPage(final Browser browser,
                                                      final Class<?> pageClass) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean)browser.isAt(pageClass);
            }

            @Override
            public String toString() {
                return "Presence of page: " + pageClass.getName();
            }
        };
    }

    public static ExpectedCondition<Boolean> presenceOfPage(final Browser browser,
                                                            final PageClass pageObject) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean)browser.isAt(pageObject);
            }

            @Override
            public String toString() {
                return "Presence of page: " + pageObject.getClass().getName();
            }
        };
    }
}
