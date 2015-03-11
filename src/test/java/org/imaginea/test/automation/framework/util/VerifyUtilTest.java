package org.imaginea.test.automation.framework.util;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by varunm on 05-03-2015.
 */
public class VerifyUtilTest {

    private VerifyUtil verifyUtil;

    @BeforeMethod
    public void setup(){
        verifyUtil = new VerifyUtil();
    }

    @Test
    public void testStringVerifyEquals() {
        verifyUtil.verifyEquals("string", "string");
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testIntegerVerifyEquals() {
        verifyUtil.verifyEquals(10, 10);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testFloatVerifyEquals() {
        verifyUtil.verifyEquals(10.1F, 10.1F, 0.1F);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testDoubleVerifyEquals() {
        verifyUtil.verifyEquals(10.1D, 10.1D, 0.1D);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testLongVerifyEquals() {
        verifyUtil.verifyEquals(100L, 100L);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testBooleanVerifyEquals() {
        verifyUtil.verifyEquals(true , true);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testByteVerifyEquals() {
        verifyUtil.verifyEquals((byte)1, (byte)1);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testCharVerifyEquals() {
        verifyUtil.verifyEquals('c', 'c');
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testVerifyNotNull() {
        verifyUtil.verifyNotNull("test");
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testVerifyNull() {
        verifyUtil.verifyNull(null);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }




}
