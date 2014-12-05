package org.imaginea.test.automation.framework.dom.filter;

import java.util.regex.Pattern;

/**
 * Created by menonvarun on 03-12-2014.
 *
 * A pattern based TextMatcher
 */
public class PatternTextMatcher implements TextMatcher {

    final Pattern pattern;

    public PatternTextMatcher(CharSequence pattern) {
        this.pattern = Pattern.compile(pattern.toString(), Pattern.DOTALL);
    }

    public boolean matches(String text) {
        return pattern.matcher(text).matches();
    }
}
