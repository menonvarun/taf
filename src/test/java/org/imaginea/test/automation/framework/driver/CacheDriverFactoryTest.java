package org.imaginea.test.automation.framework.driver;

import org.imaginea.test.automation.framework.config.DefaultConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by varunm on 08-12-2014.
 */
public class CacheDriverFactoryTest {
    @Test
    public void verifyGettingTheDefaultThreadBasedDriver(){
        CacheDriverFactory cacheDriverFactory = new CacheDriverFactory();
        Assert.assertEquals(cacheDriverFactory.getThreadBasedDriver(),true);
    }

    @Test
    public void verifyGettingTheThreadBasedDriverAfterManuallySetting(){
        CacheDriverFactory cacheDriverFactory = new CacheDriverFactory();
        cacheDriverFactory.setThreadBasedDriver(false);
        Assert.assertEquals(cacheDriverFactory.getThreadBasedDriver(),false);
    }

    @Test
    public void verifyGettingDefaultDriverProvider(){
        CacheDriverFactory cacheDriverFactory = new CacheDriverFactory();
        IDriverProvider driverProvider = cacheDriverFactory.getDriverProvider();
        Assert.assertTrue(driverProvider instanceof InbuiltDriverProvider);
    }

    @Test
    public void verifySettingCustomDriverProvider(){
        CacheDriverFactory cacheDriverFactory = new CacheDriverFactory();
        cacheDriverFactory.setDriverProvider(new TestDriverProviderSample());
        IDriverProvider driverProvider = cacheDriverFactory.getDriverProvider();
        Assert.assertTrue(driverProvider instanceof TestDriverProviderSample);
    }

    @Test
    public void verifyGettingUserDefinedDriverProviderWhenItsNotSet(){
        CacheDriverFactory cacheDriverFactory = new CacheDriverFactory();
        IDriverProvider driverProvider = cacheDriverFactory.getUserDefinedDriverProvider();
        Assert.assertEquals(driverProvider, null);
    }

    @Test
    public void verifyGettingUserDefinedDriverProviderWhenItsSet(){
        DefaultConfig config = DefaultConfig.getDefaultConfig();
        config.setConfigValue("userdefined.driverclass","org.imaginea.test.automation.framework.driver.TestDriverProviderSample");
        CacheDriverFactory cacheDriverFactory = new CacheDriverFactory();
        IDriverProvider driverProvider = cacheDriverFactory.getUserDefinedDriverProvider();
        Assert.assertTrue(driverProvider instanceof TestDriverProviderSample);
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void verifyExceptionThrownWhenUserDefinedDriverClassNotAvailable(){
        DefaultConfig config = DefaultConfig.getDefaultConfig();
        String originalUserDefinedValue = config.getConfigValue("userdefined.driverclass");
        try {
            config.setConfigValue("userdefined.driverclass", "TestDriverProvider");
            CacheDriverFactory cacheDriverFactory = new CacheDriverFactory();
            cacheDriverFactory.getUserDefinedDriverProvider();
        } finally {
            config.setConfigValue("userdefined.driverclass",originalUserDefinedValue);
        }
    }

    @Test
    public void verifyNewDriverCreatedWhenItsNull(){
        CacheDriverFactory cacheDriverFactory = new CacheDriverFactory();
        WebDriver driver = cacheDriverFactory.getDriver();
        Assert.assertNotEquals(driver, null);
    }

    @Test
    public void verifySameDriverObjectIsReturnedByGetDriver(){
        CacheDriverFactory cacheDriverFactory = new CacheDriverFactory();
        WebDriver driver1 = cacheDriverFactory.getDriver();
        WebDriver driver2 = cacheDriverFactory.getDriver();
        Assert.assertEquals(driver1, driver2);
    }

    @Test
    public void verifyAnewDriverIsReturnedAfterClearingDriver(){
        CacheDriverFactory cacheDriverFactory = new CacheDriverFactory();
        WebDriver driver1 = cacheDriverFactory.getDriver();
        CacheDriverFactory.clearCacheAndQuitDriver();
        WebDriver driver2 = cacheDriverFactory.getDriver();
        Assert.assertNotEquals(driver1, driver2);
    }

    @Test(expectedExceptions = {WebDriverException.class})
    public void verifyExistingDriverHasBeenQuitWhileClearCache(){
        CacheDriverFactory cacheDriverFactory = new CacheDriverFactory();
        WebDriver driver = cacheDriverFactory.getDriver();
        CacheDriverFactory.clearCacheAndQuitDriver();
        driver.findElement(By.tagName("html"));
    }

}
