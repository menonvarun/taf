package org.imaginea.test.automation.framework.dom.filter;

/**
 * Created by menonvarun on 03-12-2014.
 *
 * A negative text matcher
 */
public class NegatedTextMatcher implements TextMatcher {
    final TextMatcher matcher;

    public NegatedTextMatcher(TextMatcher matcher) {
        this.matcher = matcher;
    }

    public boolean matches(String text) {
        return !matcher.matches(text);
    }
}
