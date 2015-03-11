package org.imaginea.test.automation.framework.testclasses;

import org.imaginea.test.automation.framework.dom.EWebElement;
import org.imaginea.test.automation.framework.dom.ElementOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by varunm on 03-03-2015.
 */
public class PageFactoryTestClass {

    @FindBy
    public WebElement id;

    @FindBy(id = "id2")
    public WebElement idWebElement;

    @FindBy(id = "location")
    public EWebElement elementWithoutName;

    @FindBy(css="#id[name=\"name\"]")
    @ElementOptions(name = "Element with name", required = false, navigablePageClasses = {PassingPageClassA.class,PassingPageClassB.class})
    public EWebElement elementWithOptions;
}
