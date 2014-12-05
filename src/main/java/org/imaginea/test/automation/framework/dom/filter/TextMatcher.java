package org.imaginea.test.automation.framework.dom.filter;

/**
 * Created by menonvarun on 03-12-2014.
 */
public interface TextMatcher {

    /**
     * Matches the TextMatcher for the given text
     * @param text The text that needs to be matched
     * @return true if it matches else false.
     */
    boolean matches(String text);
}
