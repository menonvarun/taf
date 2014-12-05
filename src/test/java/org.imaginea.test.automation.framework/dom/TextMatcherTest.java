package org.imaginea.test.automation.framework.dom;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.imaginea.test.automation.framework.dom.filter.TextMatching.*;
/**
 * Created by varunm on 04-12-2014.
 */
public class TextMatcherTest {

    @Test
    public void verifyEqualsValueWhenValueMatches(){
        Assert.assertTrue(equalsValue("equals").matches("equals"));
    }

    @Test
    public void verifyEqualsValueWhenValueDontMatch(){
        Assert.assertFalse(equalsValue("equals").matches("equalsTest"));
    }

    @Test
    public void verifyEqualsIgnoreCaseValueWhenValueMatches(){
        Assert.assertTrue(iEqualsValue("equals").matches("eQuAls"));
    }

    @Test
    public void verifyEqualsIgnoreCaseValueWhenValueDontMatch(){
        Assert.assertFalse(iEqualsValue("equals").matches("eQuAlsTest"));
    }

    @Test
    public void verifyNotEqualsValueWhenValueMatches(){
        Assert.assertTrue(notEqualsValue("equals").matches("eQuAls"));
    }

    @Test
    public void verifyNotEqualsValueWhenValueDontMatch(){
        Assert.assertFalse(notEqualsValue("equals").matches("equals"));
    }

    @Test
    public void verifyNotEqualsIgnoreCaseValueWhenValueMatches(){
        Assert.assertTrue(iNotEqualsValue("equals").matches("eQuAlsTest"));
    }

    @Test
    public void verifyNotEqualsIgnoreCaseValueWhenValueDontMatch(){
        Assert.assertFalse(iNotEqualsValue("equals").matches("eQuAls"));
    }

    @Test
    public void verifyContainValueWhenValueMatches(){
        Assert.assertTrue(contains("contains").matches("This containstext a value."));
    }

    @Test
    public void verifyContainsValueWhenValueDontMatch(){
        Assert.assertFalse(contains("contains").matches("A test value"));
    }

    @Test
    public void verifyContainIgnoreCaseValueWhenValueMatches(){
        Assert.assertTrue(iContains("contains").matches("This cOnTaiNsTexT a value."));
    }

    @Test
    public void verifyContainsIgnoreCaseValueWhenValueDontMatch(){
        Assert.assertFalse(iContains("contains").matches("A test value"));
    }

    @Test
    public void verifyNotContainValueWhenValueMatches(){
        Assert.assertTrue(notContains("contains").matches("A test value"));
    }

    @Test
    public void verifyNotContainsValueWhenValueDontMatch(){
        Assert.assertFalse(notContains("contains").matches("This containsTest a value."));
    }

    @Test
    public void verifyNotContainIgnoreCaseValueWhenValueMatches(){
        Assert.assertTrue(iNotContains("contains").matches("A test value"));
    }

    @Test
    public void verifyNotContainsIgnoreCaseValueWhenValueDontMatch(){
        Assert.assertFalse(iNotContains("contains").matches("This cOnTaiNs a value."));
    }

    @Test
    public void verifyContainsWordValueWhenValueDontMatch(){
        Assert.assertFalse(containsWord("contains").matches("A test value"));
    }

    @Test
    public void verifyContainWordIgnoreCaseValueWhenValueMatches(){
        Assert.assertTrue(iContainsWord("contains").matches("This cOnTaiNs a value."));
    }

    @Test
    public void verifyContainsWordIgnoreCaseValueWhenValueDontMatch(){
        Assert.assertFalse(iContainsWord("contains").matches("A test value"));
    }

    @Test
    public void verifyNotContainWordValueWhenValueMatches(){
        Assert.assertTrue(notContainsWord("contains").matches("A test value"));
    }

    @Test
    public void verifyNotContainsWordValueWhenValueDontMatch(){
        Assert.assertFalse(notContainsWord("contains").matches("This contains a value."));
    }

    @Test
    public void verifyNotContainWordIgnoreCaseValueWhenValueMatches(){
        Assert.assertTrue(iNotContainsWord("contains").matches("A test value"));
    }

    @Test
    public void verifyNotContainsWordIgnoreCaseValueWhenValueDontMatch(){
        Assert.assertFalse(iNotContainsWord("contains").matches("This cOnTaiNs a value."));
    }

    @Test
    public void verifyStartsWithValueWhenValueMatches(){
        Assert.assertTrue(startsWith("starts with").matches("starts with value."));
    }

    @Test
    public void verifyStartsWithValueWhenValueDontMatch(){
        Assert.assertFalse(startsWith("starts with").matches("does not starts with value"));
    }

    @Test
    public void verifyStartsWithIgnoreCaseValueWhenValueMatches(){
        Assert.assertTrue(iStartsWith("starts with").matches("StaRTs WitH value"));
    }

    @Test
    public void verifyStartsWithIgnoreCaseValueWhenValueDontMatch(){
        Assert.assertFalse(iStartsWith("starts with").matches("A test value"));
    }

    @Test
    public void verifyNotStartsWithValueWhenValueMatches(){
        Assert.assertTrue(notStartsWith("starts with").matches("Does not starts with value."));
    }

    @Test
    public void verifyNotStartsWithValueWhenValueDontMatch(){
        Assert.assertFalse(notStartsWith("starts with").matches("starts with value"));
    }

    @Test
    public void verifyNotStartsWithIgnoreCaseValueWhenValueMatches(){
        Assert.assertTrue(iNotStartsWith("starts with").matches("A test value"));
    }

    @Test
    public void verifyNotStartsWithIgnoreCaseValueWhenValueDontMatch(){
        Assert.assertFalse(iNotStartsWith("starts with").matches("StaRTs WitH value"));
    }

    @Test
    public void verifyEndsWithValueWhenValueMatches(){
        Assert.assertTrue(endsWith("ends with").matches("This value ends with"));
    }

    @Test
    public void verifyEndsWithValueWhenValueDontMatch(){
        Assert.assertFalse(endsWith("ends with").matches("This value dont ends with the respected value"));
    }

    @Test
    public void verifyEndsWithIgnoreCaseValueWhenValueMatches(){
        Assert.assertTrue(iEndsWith("EnDS WiTh").matches("This value eNdS wItH"));
    }

    @Test
    public void verifyEndsWithIgnoreCaseValueWhenValueDontMatch(){
        Assert.assertFalse(iEndsWith("EnDS WiTh").matches("This value eNdS wItH nothing"));
    }

    @Test
    public void verifyNotEndsWithValueWhenValueMatches(){
        Assert.assertTrue(notEndsWith("ends with").matches("This value dont ends with the respected value"));
    }

    @Test
    public void verifyNotEndsWithValueWhenValueDontMatch(){
        Assert.assertFalse(notEndsWith("ends with").matches("This value ends with"));
    }

    @Test
    public void verifyNotEndsWithIgnoreCaseValueWhenValueMatches(){
        Assert.assertTrue(iNotEndsWith("EnDS WiTh").matches("This value eNdS wItH nothing"));
    }

    @Test
    public void verifyNotEndsWithIgnoreCaseValueWhenValueDontMatch(){
        Assert.assertFalse(iNotEndsWith("EnDS WiTh").matches("This value eNdS wItH"));
    }
}
