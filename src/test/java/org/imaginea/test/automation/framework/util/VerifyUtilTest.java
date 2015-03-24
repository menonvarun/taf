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
    public void testShortVerifyEquals() {
        verifyUtil.verifyEquals((short)1, (short)1);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testVerifySame() {
        Object a = new Object();
        verifyUtil.verifySame(a, a);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testVerifyTrue() {
        verifyUtil.verifyTrue(true);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testVerifyFalse() {
        verifyUtil.verifyFalse(false);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testFailWithACause() {
        verifyUtil.fail("Testing fail message with cause", new Exception("test exception"));
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test(expectedExceptions = {AssertionError.class})
    public void testVerifyAssertMethod() {
        verifyUtil.fail();
        verifyUtil.assertVerify();
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

    @Test
    public void testStringVerifyNotEquals() {
        verifyUtil.verifyNotEquals("string", "teststring");
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testIntegerVerifyNotEquals() {
        verifyUtil.verifyNotEquals(10, 11);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testFloatVerifyNotEquals() {
        verifyUtil.verifyNotEquals(10.1F, 12.1F, 0.1F);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testDoubleVerifyNotEquals() {
        verifyUtil.verifyNotEquals(10.1D, 12.1D, 0.1D);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testLongVerifyNotEquals() {
        verifyUtil.verifyNotEquals(100L, 110L);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testBooleanVerifyNotEquals() {
        verifyUtil.verifyNotEquals(true , false);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testByteVerifyNotEquals() {
        verifyUtil.verifyNotEquals((byte)1, (byte)2);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testCharVerifyNotEquals() {
        verifyUtil.verifyNotEquals('c', 'd');
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testShortVerifyNotEquals() {
        verifyUtil.verifyNotEquals((short) 1, (short) 2);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testVerifyNotSame() {
        Object a = new Object();
        Object b = new Object();
        verifyUtil.verifyNotSame(a, b);
        Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testStringFailingVerifyEquals() {
        verifyUtil.verifyEquals("string", "some string");
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testIntegerFailingVerifyEquals() {
        verifyUtil.verifyEquals(10, 11);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testFloatFailingVerifyEquals() {
        verifyUtil.verifyEquals(10.1F, 12.1F, 0.1F);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testDoubleFailingVerifyEquals() {
        verifyUtil.verifyEquals(10.1D, 12.1D, 0.1D);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testLongFailingVerifyEquals() {
        verifyUtil.verifyEquals(100L, 110L);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testBooleanFailingVerifyEquals() {
        verifyUtil.verifyEquals(true , false);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testByteFailingVerifyEquals() {
        verifyUtil.verifyEquals((byte)1, (byte)2);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testCharFailingVerifyEquals() {
        verifyUtil.verifyEquals('c', 'd');
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testFailingVerifyNotNull() {
        verifyUtil.verifyNotNull(null);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testFailingVerifyNull() {
        verifyUtil.verifyNull("test");
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testStringFailingVerifyNotEquals() {
        verifyUtil.verifyNotEquals("string", "string");
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testIntegerFailingVerifyNotEquals() {
        verifyUtil.verifyNotEquals(10, 10);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testFloatFailingVerifyNotEquals() {
        verifyUtil.verifyNotEquals(10.1F, 10F, 0.2F);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testDoubleFailingVerifyNotEquals() {
        verifyUtil.verifyNotEquals(10.1D, 10D, 0.1D);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testLongFailingVerifyNotEquals() {
        verifyUtil.verifyNotEquals(100L, 100L);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testBooleanFailingVerifyNotEquals() {
        verifyUtil.verifyNotEquals(true , true);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testByteFailingVerifyNotEquals() {
        verifyUtil.verifyNotEquals((byte)1, (byte)1);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testCharFailingVerifyNotEquals() {
        verifyUtil.verifyNotEquals('c', 'c');
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testFailingShortVerifyEquals() {
        verifyUtil.verifyEquals((short)1, (short)2);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testFailingVerifySame() {
        Object a = new Object();
        Object b = new Object();
        verifyUtil.verifySame(a, b);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testFailingShortVerifyNotEquals() {
        verifyUtil.verifyNotEquals((short)1, (short)1);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testFailingVerifyNotSame() {
        Object a = new Object();
        verifyUtil.verifyNotSame(a, a);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testFailingVerifyTrue() {
        verifyUtil.verifyTrue(false);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }

    @Test
    public void testFailingVerifyFalse() {
        verifyUtil.verifyFalse(true);
        Assert.assertFalse(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
    }





}
