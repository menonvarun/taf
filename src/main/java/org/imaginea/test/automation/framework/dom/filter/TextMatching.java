package org.imaginea.test.automation.framework.dom.filter;

import java.util.regex.Pattern;

/**
 * Created by menonvarun on 03-12-2014.
 * This class provides different utilities to match a text/string
 */
public class TextMatching {

    /**
     * Returns a {@link TextMatcher} for matching the text that starts with the given string
     * @param str String to match
     * @return
     */
    public static TextMatcher startsWith(String str) {
        return new PatternTextMatcher(Pattern.quote(str) + ".*");
    }

    /**
     * Returns a {@link TextMatcher} for matching the text that does not starts with the given string
     * @param str String to match
     * @return
     */
    public static TextMatcher notStartsWith(String str) {
        return new NegatedTextMatcher(startsWith(str));
    }

    /**
     * Returns a {@link TextMatcher} for matching the text that contains the given string
     * @param str String to match
     * @return
     */
    public static TextMatcher contains(String str) {
        return new PatternTextMatcher(".*" + Pattern.quote(str) + ".*");
    }

    /**
     * Returns a {@link TextMatcher} for matching the text that does not contains the given string
     * @param str String to match
     * @return
     */
    public static TextMatcher notContains(String str) {
        return new NegatedTextMatcher(contains(str));
    }

    /**
     * Returns a {@link TextMatcher} for matching the text that ends with the given string
     * @param str String to match
     * @return
     */
    public static TextMatcher endsWith(String str) {
        return new PatternTextMatcher(".*" + Pattern.quote(str));
    }

    /**
     * Returns a {@link TextMatcher} for matching the text that does not ends with the given string
     * @param str String to match
     * @return
     */
    public static TextMatcher notEndsWith(String str) {
        return new NegatedTextMatcher(endsWith(str));
    }

    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that starts with the given
     * string.
     * @param str String to match
     * @return
     */
    public static TextMatcher iStartsWith(String str) {
        return new PatternTextMatcher("(?i)" + Pattern.quote(str) + ".*");
    }

    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that does not starts with the given
     * string.
     * @param str String to match
     * @return
     */
    public static TextMatcher iNotStartsWith(String str) {
        return new NegatedTextMatcher(iStartsWith(str));
    }

    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that contains the given
     * string.
     * @param str String to match
     * @return
     */
    public static TextMatcher iContains(String str) {
        return new PatternTextMatcher("(?i).*" + Pattern.quote(str) + ".*");
    }

    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that does not contains the given
     * string.
     * @param str String to match
     * @return
     */
    public static TextMatcher iNotContains(String str) {
        return new NegatedTextMatcher(iContains(str));
    }

    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that ends with the given
     * string.
     * @param str String to match
     * @return
     */
    public static TextMatcher iEndsWith(String str) {
        return new PatternTextMatcher("(?i).*" + Pattern.quote(str));
    }

    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that does not ends with the given
     * string.
     * @param str String to match
     * @return
     */
    public static TextMatcher iNotEndsWith(String str) {
        return new NegatedTextMatcher(iEndsWith(str));
    }

    /**
     * Returns a {@link TextMatcher} for matching the text that contains word of the given string
     * @param str String to match
     * @return
     */
    public static TextMatcher containsWord(String str) {
        return new PatternTextMatcher("(^|.+\\s)" + Pattern.quote(str) + "($|\\s.+)");
    }

    /**
     * Returns a {@link TextMatcher} for matching the text that does not contains word of the given string
     * @param str String to match
     * @return
     */
    public static TextMatcher notContainsWord(String str) {
        return new NegatedTextMatcher(containsWord(str));
    }

    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that contains the word for the given
     * string.
     * @param str String to match
     * @return
     */
    public static TextMatcher iContainsWord(String str) {
        return new PatternTextMatcher("(?i)(^|.+\\s)" + Pattern.quote(str) + "($|\\s.+)");
    }

    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that does not contains
     * the word for the given string.
     * @param str String to match
     * @return
     */
    public static TextMatcher iNotContainsWord(String str) {
        return new NegatedTextMatcher(iContainsWord(str));
    }

    /**
     * Returns a {@link TextMatcher} for matching the text that starts with the given pattern.
     * @param pattern pattern to be used for matching.
     * @return
     */
    public static TextMatcher startsWith(Pattern pattern) {
        return new PatternTextMatcher(pattern.pattern() + ".*");
    }

    /**
     * Returns a {@link TextMatcher} for matching the text that does not starts with the given pattern.
     * @param pattern pattern to be used for matching.
     * @return
     */
    public static TextMatcher notStartsWith(Pattern pattern) {
        return new NegatedTextMatcher(startsWith(pattern));
    }

    /**
     * Returns a {@link TextMatcher} for matching the text that contains the given pattern.
     * @param pattern pattern to be used for matching.
     * @return
     */
    public static TextMatcher contains(Pattern pattern) {
        return new PatternTextMatcher(".*" + pattern.pattern() + ".*");
    }

    /**
     * Returns a {@link TextMatcher} for matching the text that does not contains the given pattern.
     * @param pattern pattern to be used for matching.
     * @return
     */
    public static TextMatcher notContains(Pattern pattern) {
        return new NegatedTextMatcher(contains(pattern));
    }

    /**
     * Returns a {@link TextMatcher} for matching the text that ends with the given pattern.
     * @param pattern pattern to be used for matching.
     * @return
     */
    public static TextMatcher endsWith(Pattern pattern) {
        return new PatternTextMatcher(".*" + pattern.pattern());
    }

    /**
     * Returns a {@link TextMatcher} for matching the text that does not
     * ends with the given pattern.
     * @param pattern pattern to be used for matching.
     * @return
     */
    public static TextMatcher notEndsWith(Pattern pattern) {
        return new NegatedTextMatcher(endsWith(pattern));
    }
    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that
     * starts with the given pattern.
     * @param pattern pattern to be used for matching.
     * @return
     */
    public static TextMatcher iStartsWith(Pattern pattern) {
        return new PatternTextMatcher("(?i)" + pattern.pattern() + ".*");
    }

    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that does not
     * starts with the given pattern.
     * @param pattern pattern to be used for matching.
     * @return
     */
    public static TextMatcher iNotStartsWith(Pattern pattern) {
        return new NegatedTextMatcher(iStartsWith(pattern));
    }

    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that
     * contains the given pattern.
     * @param pattern pattern to be used for matching.
     * @return
     */
    public static TextMatcher iContains(Pattern pattern) {
        return new PatternTextMatcher("(?i).*" + pattern.pattern() + ".*");
    }

    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that does not
     * contains the given pattern.
     * @param pattern pattern to be used for matching.
     * @return
     */
    public static TextMatcher iNotContains(Pattern pattern) {
        return new NegatedTextMatcher(iContains(pattern));
    }

    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that ends with the given pattern.
     * @param pattern pattern to be used for matching.
     * @return
     */
    public static TextMatcher iEndsWith(Pattern pattern) {
        return new PatternTextMatcher("(?i).*" + pattern.pattern());
    }

    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that does not
     * ends with based on the given pattern.
     * @param pattern pattern to be used for matching.
     * @return
     */
    public static TextMatcher iNotEndsWith(Pattern pattern) {
        return new NegatedTextMatcher(iEndsWith(pattern));
    }

    /**
     * Returns a {@link TextMatcher} for matching the text that contains the word based on the given pattern.
     * @param pattern pattern to be used for matching.
     * @return
     */
    public static TextMatcher containsWord(Pattern pattern) {
        return new PatternTextMatcher("(^|.+\\s)" + pattern.pattern() + "($|\\s.+)");
    }

    /**
     * Returns a {@link TextMatcher} for matching the text that does not contains the word based on the given pattern.
     * @param pattern pattern to be used for matching.
     * @return
     */
    public static TextMatcher notContainsWord(Pattern pattern) {
        return new NegatedTextMatcher(containsWord(pattern));
    }

    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that contains the word based on the given pattern.
     * @param pattern pattern to be used for matching.
     * @return
     */
    public static TextMatcher iContainsWord(Pattern pattern) {
        return new PatternTextMatcher("(?i)(^|.+\\s)" + pattern.pattern() + "($|\\s.+)");
    }

    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that does not
     * contains the word based on the given pattern.
     * @param pattern pattern to be used for matching.
     * @return
     */
    public static TextMatcher iNotContainsWord(Pattern pattern) {
        return new NegatedTextMatcher(iContainsWord(pattern));
    }

    /**
     * Returns a {@link TextMatcher} for matching the text that equals the given string
     * @param str String to match
     * @return
     */
    public static TextMatcher equalsValue(String str){
        return new PatternTextMatcher(Pattern.quote(str));
    }

    /**
     * Returns a {@link TextMatcher} for matching the text that does not equals the given string
     * c
     * @return
     */
    public static TextMatcher notEqualsValue(String str){
        return new NegatedTextMatcher(equalsValue(str));
    }

    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that equals the given string.
     * @param str String to match
     * @return
     */
    public static TextMatcher iEqualsValue(String str){
        return new PatternTextMatcher("(?i)" +Pattern.quote(str));
    }

    /**
     * Returns a {@link TextMatcher} for matching as case insensitive the text that does not
     * equalt the given string.
     * @param str String to match
     * @return
     */
    public static TextMatcher iNotEqualsValue(String str){
        return new NegatedTextMatcher(iEqualsValue(str));
    }

}
