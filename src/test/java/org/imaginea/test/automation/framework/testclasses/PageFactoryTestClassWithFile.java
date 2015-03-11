package org.imaginea.test.automation.framework.testclasses;

import org.imaginea.test.automation.framework.dom.EWebElement;
import org.imaginea.test.automation.framework.dom.ElementOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.io.File;

/**
 * Created by varunm on 03-03-2015.
 */
public class PageFactoryTestClassWithFile {

    public WebElement id;

    @FindBy(id = "idWebElement")
    public WebElement elementUsingShortFindBy;

    @FindBy(how = How.ID, using = "elementUsingLongAndShortFindBy")
    public WebElement elementUsingLongFindBy;

    @FindBy(how = How.ID, using = "elementUsingLongAndShortFindBy")
    public EWebElement eWebElementUsingLongFindBy;

    @FindBy(id = "elementUsingLongAndShortFindBy")
    public EWebElement eWebElementUsingShortFindBy;

    @FindBy(css="elementWithOptions")
    @ElementOptions(name = "Element with name", required = false, navigablePageClasses = {PassingPageClassA.class,PassingPageClassB.class})
    public EWebElement elementWithOptions;

    public File getLocatorFile(){
        File file = new File("src/test/resources/locator/factory/PageFactoryTestClassFile.properties");
        return file;
    }
}
