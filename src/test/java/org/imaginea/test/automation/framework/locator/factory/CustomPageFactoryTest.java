package org.imaginea.test.automation.framework.locator.factory;

import org.imaginea.test.automation.framework.TafTestClass;
import org.imaginea.test.automation.framework.locator.CustomPageFactory;

import org.imaginea.test.automation.framework.pagemodel.PageClass;
import org.imaginea.test.automation.framework.testclasses.PageFactoryTestClass;
import org.imaginea.test.automation.framework.testclasses.PageFactoryTestClassWithFile;
import org.imaginea.test.automation.framework.testclasses.PassingPageClassA;
import org.imaginea.test.automation.framework.util.VerifyUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by varunm on 03-03-2015.
 */
public class CustomPageFactoryTest extends TafTestClass{
    private WebDriver driver;
    PageFactoryTestClass pageFactoryTestClass;
    private VerifyUtil verifyUtil;

    @BeforeClass
    public void beforeClass(){
        driver = browser.getDriver();
        browser.navigateTo(getHtmlFile("index.html"));
    }

    @BeforeMethod
    public void beforeMethod(){
        verifyUtil = new VerifyUtil();
    }

    @Test
    public void testingPresenceOfElementOptionsWithBrowser(){
        PageFactoryTestClass testClass = CustomPageFactory.initElements(browser,PageFactoryTestClass.class);

        verifyUtil.verifyEquals(testClass.elementWithoutName.getName(), "elementWithoutName");
        verifyUtil.verifyEquals(testClass.elementWithoutName.isRequired(), true);
        verifyUtil.verifyEquals(testClass.elementWithoutName.getNavigablePageClasses().size(), 0);

        verifyUtil.verifyEquals(testClass.elementWithOptions.getName(), "Element with name");
        verifyUtil.verifyEquals(testClass.elementWithOptions.isRequired(), false);
        verifyUtil.verifyEquals(testClass.elementWithOptions.getNavigablePageClasses().size(), 2);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testingPresenceOfElementOptionsWithDriver(){
        PageFactoryTestClass testClass = CustomPageFactory.initElements(driver,PageFactoryTestClass.class);

        verifyUtil.verifyEquals(testClass.elementWithoutName.getName(), "elementWithoutName");
        verifyUtil.verifyEquals(testClass.elementWithoutName.isRequired(), true);
        verifyUtil.verifyEquals(testClass.elementWithoutName.getNavigablePageClasses().size(), 0);

        verifyUtil.verifyEquals(testClass.elementWithOptions.getName(), "Element with name");
        verifyUtil.verifyEquals(testClass.elementWithOptions.isRequired(), false);
        verifyUtil.verifyEquals(testClass.elementWithOptions.getNavigablePageClasses().size(), 2);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }


    @Test
    public void testingPresenceOfElementOptionsWithBrowserUsingDefaultLocatorFile(){
        PageFactoryTestClassWithFile testClass = CustomPageFactory.initElements(browser,PageFactoryTestClassWithFile.class);

        verifyUtil.verifyEquals(testClass.eWebElementUsingLongFindBy.getName(), "eWebElementUsingLongFindBy");
        verifyUtil.verifyEquals(testClass.eWebElementUsingLongFindBy.isRequired(), true);
        verifyUtil.verifyEquals(testClass.eWebElementUsingLongFindBy.getNavigablePageClasses().size(), 0);

        verifyUtil.verifyEquals(testClass.eWebElementUsingShortFindBy.getName(), "eWebElementUsingShortFindBy");
        verifyUtil.verifyEquals(testClass.eWebElementUsingShortFindBy.isRequired(), true);
        verifyUtil.verifyEquals(testClass.eWebElementUsingShortFindBy.getNavigablePageClasses().size(), 0);


        verifyUtil.verifyEquals(testClass.elementWithOptions.getName(), "Element with name");
        verifyUtil.verifyEquals(testClass.elementWithOptions.isRequired(), false);
        verifyUtil.verifyEquals(testClass.elementWithOptions.getNavigablePageClasses().size(), 2);

        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testingPresenceOfElementOptionsWithDriverUsingDefaultLocatorFile(){
        PageFactoryTestClassWithFile testClass = CustomPageFactory.initElements(driver,PageFactoryTestClassWithFile.class);

        verifyUtil.verifyEquals(testClass.eWebElementUsingLongFindBy.getName(), "eWebElementUsingLongFindBy");
        verifyUtil.verifyEquals(testClass.eWebElementUsingLongFindBy.isRequired(), true);
        verifyUtil.verifyEquals(testClass.eWebElementUsingLongFindBy.getNavigablePageClasses().size(), 0);

        verifyUtil.verifyEquals(testClass.eWebElementUsingShortFindBy.getName(), "eWebElementUsingShortFindBy");
        verifyUtil.verifyEquals(testClass.eWebElementUsingShortFindBy.isRequired(), true);
        verifyUtil.verifyEquals(testClass.eWebElementUsingShortFindBy.getNavigablePageClasses().size(), 0);

        verifyUtil.verifyEquals(testClass.elementWithOptions.getName(), "Element with name");
        verifyUtil.verifyEquals(testClass.elementWithOptions.isRequired(), false);
        verifyUtil.verifyEquals(testClass.elementWithOptions.getNavigablePageClasses().size(), 2);

        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testingPresenceOfElementOptionsWithBrowserUsingCustomLocatorFile(){

        PageFactoryTestClassWithFile testClass = CustomPageFactory.initElements(browser,PageFactoryTestClassWithFile.class,
                "src/test/resources/locator/factory/PageFactoryTestClassCustomFile.properties");

        verifyUtil.verifyEquals(testClass.eWebElementUsingLongFindBy.getName(), "eWebElementUsingLongFindBy");
        verifyUtil.verifyEquals(testClass.eWebElementUsingLongFindBy.isRequired(), true);
        verifyUtil.verifyEquals(testClass.eWebElementUsingLongFindBy.getNavigablePageClasses().size(), 0);

        verifyUtil.verifyEquals(testClass.eWebElementUsingShortFindBy.getName(), "eWebElementUsingShortFindBy");
        verifyUtil.verifyEquals(testClass.eWebElementUsingShortFindBy.isRequired(), true);
        verifyUtil.verifyEquals(testClass.eWebElementUsingShortFindBy.getNavigablePageClasses().size(), 0);


        verifyUtil.verifyEquals(testClass.elementWithOptions.getName(), "Element with name");
        verifyUtil.verifyEquals(testClass.elementWithOptions.isRequired(), false);
        verifyUtil.verifyEquals(testClass.elementWithOptions.getNavigablePageClasses().size(), 2);

        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testingPresenceOfElementOptionsWithDriverUsingCustomLocatorFile(){
        PageFactoryTestClassWithFile testClass = CustomPageFactory.initElements(driver,PageFactoryTestClassWithFile.class,
                "src/test/resources/locator/factory/PageFactoryTestClassCustomFile.properties");

        verifyUtil.verifyEquals(testClass.eWebElementUsingLongFindBy.getName(), "eWebElementUsingLongFindBy");
        verifyUtil.verifyEquals(testClass.eWebElementUsingLongFindBy.isRequired(), true);
        verifyUtil.verifyEquals(testClass.eWebElementUsingLongFindBy.getNavigablePageClasses().size(), 0);

        verifyUtil.verifyEquals(testClass.eWebElementUsingShortFindBy.getName(), "eWebElementUsingShortFindBy");
        verifyUtil.verifyEquals(testClass.eWebElementUsingShortFindBy.isRequired(), true);
        verifyUtil.verifyEquals(testClass.eWebElementUsingShortFindBy.getNavigablePageClasses().size(), 0);

        verifyUtil.verifyEquals(testClass.elementWithOptions.getName(), "Element with name");
        verifyUtil.verifyEquals(testClass.elementWithOptions.isRequired(), false);
        verifyUtil.verifyEquals(testClass.elementWithOptions.getNavigablePageClasses().size(), 2);

        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testingPageNavigationValidationWithBrowser(){
        PageFactoryTestClassWithFile testClass = CustomPageFactory.initElements(browser,PageFactoryTestClassWithFile.class);

        PageClass page = testClass.elementWithOptions.click();

        Assert.assertTrue(page instanceof PassingPageClassA, String.format("Expected click action should return object of type %s but found it to be %s.",
                PassingPageClassA.class.getName(), page.getClass().getName()) );
    }

    @Test
    public void testingPageNavigationValidationWithDriver(){
        PageFactoryTestClassWithFile testClass = CustomPageFactory.initElements(driver,PageFactoryTestClassWithFile.class);

        PageClass page = testClass.elementWithOptions.click();

        Assert.assertTrue(page instanceof PassingPageClassA, String.format("Expected click action should return object of type %s but found it to be %s.",
                PassingPageClassA.class.getName(), page.getClass().getName()) );
    }
}
