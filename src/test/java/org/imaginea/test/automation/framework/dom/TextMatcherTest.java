package org.imaginea.test.automation.framework.dom;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

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

    @Test
    public void verifyContainPatternWhenPatternMatches(){
        Assert.assertTrue(contains(Pattern.compile("contains")).matches("This containstext a value."));
    }

    @Test
    public void verifyContainsPatternWhenPatternDontMatch(){
        Assert.assertFalse(contains(Pattern.quote("contains")).matches("A test value"));
    }

    @Test
    public void verifyContainIgnoreCasePatternWhenPatternMatches(){
        Assert.assertTrue(iContains(Pattern.compile("contains")).matches("This cOnTaiNsTexT a value."));
    }

    @Test
    public void verifyContainsIgnoreCasePatternWhenPatternDontMatch(){
        Assert.assertFalse(iContains(Pattern.quote("contains")).matches("A test value"));
    }

    @Test
    public void verifyNotContainPatternWhenPatternMatches(){
        Assert.assertTrue(notContains(Pattern.quote("contains")).matches("A test value"));
    }

    @Test
    public void verifyNotContainsPatternWhenPatternDontMatch(){
        Assert.assertFalse(notContains(Pattern.compile("contains")).matches("This containsTest a value."));
    }

    @Test
    public void verifyNotContainIgnoreCasePatternWhenPatternMatches(){
        Assert.assertTrue(iNotContains(Pattern.quote("contains")).matches("A test value"));
    }

    @Test
    public void verifyNotContainsIgnoreCasePatternWhenPatternDontMatch(){
        Assert.assertFalse(iNotContains(Pattern.compile("contains")).matches("This cOnTaiNs a value."));
    }

    @Test
    public void verifyContainsWordPatternWhenPatternDontMatch(){
        Assert.assertFalse(containsWord(Pattern.quote("contains")).matches("A test value"));
    }

    @Test
    public void verifyContainWordIgnoreCasePatternWhenPatternMatches(){
        Assert.assertTrue(iContainsWord(Pattern.compile("contains")).matches("This cOnTaiNs a value."));
    }

    @Test
    public void verifyContainsWordIgnoreCasePatternWhenPatternDontMatch(){
        Assert.assertFalse(iContainsWord(Pattern.quote("contains")).matches("A test value"));
    }

    @Test
    public void verifyNotContainWordPatternWhenPatternMatches(){
        Assert.assertTrue(notContainsWord(Pattern.quote("contains")).matches("A test value"));
    }

    @Test
    public void verifyNotContainsWordPatternWhenPatternDontMatch(){
        Assert.assertFalse(notContainsWord(Pattern.compile("contains")).matches("This contains a value."));
    }

    @Test
    public void verifyNotContainWordIgnoreCasePatternWhenPatternMatches(){
        Assert.assertTrue(iNotContainsWord(Pattern.quote("contains")).matches("A test value"));
    }

    @Test
    public void verifyNotContainsWordIgnoreCasePatternWhenPatternDontMatch(){
        Assert.assertFalse(iNotContainsWord(Pattern.compile("contains")).matches("This cOnTaiNs a value."));
    }

    @Test
    public void verifyStartsWithPatternWhenPatternMatches(){
        Assert.assertTrue(startsWith(Pattern.compile("starts with")).matches("starts with value."));
    }

    @Test
    public void verifyStartsWithPatternWhenPatternDontMatch(){
        Assert.assertFalse(startsWith(Pattern.quote("starts with")).matches("does not starts with value"));
    }

    @Test
    public void verifyStartsWithIgnoreCasePatternWhenPatternMatches(){
        Assert.assertTrue(iStartsWith(Pattern.compile("starts with")).matches("StaRTs WitH value"));
    }

    @Test
    public void verifyStartsWithIgnoreCasePatternWhenPatternDontMatch(){
        Assert.assertFalse(iStartsWith(Pattern.quote("starts with")).matches("A test value"));
    }

    @Test
    public void verifyNotStartsWithPatternWhenPatternMatches(){
        Assert.assertTrue(notStartsWith(Pattern.quote("starts with")).matches("Does not starts with value."));
    }

    @Test
    public void verifyNotStartsWithPatternWhenPatternDontMatch(){
        Assert.assertFalse(notStartsWith(Pattern.compile("starts with")).matches("starts with value"));
    }

    @Test
    public void verifyNotStartsWithIgnoreCasePatternWhenPatternMatches(){
        Assert.assertTrue(iNotStartsWith(Pattern.quote("starts with")).matches("A test value"));
    }

    @Test
    public void verifyNotStartsWithIgnoreCasePatternWhenPatternDontMatch(){
        Assert.assertFalse(iNotStartsWith(Pattern.compile("starts with")).matches("StaRTs WitH value"));
    }

    @Test
    public void verifyEndsWithPatternWhenPatternMatches(){
        Assert.assertTrue(endsWith(Pattern.compile("ends with")).matches("This value ends with"));
    }

    @Test
    public void verifyEndsWithPatternWhenPatternDontMatch(){
        Assert.assertFalse(endsWith("ends with").matches("This value dont ends with the respected value"));
    }

    @Test
    public void verifyEndsWithIgnoreCasePatternWhenPatternMatches(){
        Assert.assertTrue(iEndsWith(Pattern.compile("ends with")).matches("This value eNdS wItH"));
    }

    @Test
    public void verifyEndsWithIgnoreCasePatternWhenPatternDontMatch(){
        Assert.assertFalse(iEndsWith(Pattern.quote("EnDS WiTh")).matches("This value eNdS wItH nothing"));
    }

    @Test
    public void verifyNotEndsWithPatternWhenPatternMatches(){
        Assert.assertTrue(notEndsWith(Pattern.quote("ends with")).matches("This value dont ends with the respected value"));
    }

    @Test
    public void verifyNotEndsWithPatternWhenPatternDontMatch(){
        Assert.assertFalse(notEndsWith(Pattern.compile("ends with")).matches("This value ends with"));
    }

    @Test
    public void verifyNotEndsWithIgnoreCasePatternWhenPatternMatches(){
        Assert.assertTrue(iNotEndsWith(Pattern.compile("EnDS WiTh")).matches("This value eNdS wItH nothing"));
    }

    @Test
    public void verifyNotEndsWithIgnoreCasePatternWhenPatternDontMatch(){
        Assert.assertFalse(iNotEndsWith(Pattern.compile("EnDS WiTh")).matches("This value eNdS wItH"));
    }
}
