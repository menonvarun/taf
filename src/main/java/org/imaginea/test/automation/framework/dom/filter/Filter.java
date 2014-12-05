package org.imaginea.test.automation.framework.dom.filter;

/**
 * Created by menonvarun on 03-12-2014.
 *
 * Filters that is used for filtering the WebElements. Mainily used By EWebElement class
 */
public class Filter {

    private FilterType filterType;
    private String attributeName;
    private TextMatcher textMatcher;

    public Filter(FilterType filterType, String attributeName, TextMatcher textMatcher){
        this.filterType = filterType;
        this.attributeName = attributeName;
        this.textMatcher = textMatcher;
    }

    /**
     * Returns the filter Type stored in the said object.
     * @return FilterType stored
     */
    public FilterType getFilterType() {
        return filterType;
    }

    /**
     * Returns the attribute name stored in the said object
     * @return Attribute name
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * Returns the TextMatcher stored
     * @return
     */
    public TextMatcher getTextMatcher() {
        return textMatcher;
    }


    /**
     * Returns the filter to exactly match the id attribute value
     * @param idValue The value to compare
     * @return
     */
    public static Filter withId(String idValue){
        return whereId(TextMatching.equalsValue(idValue));
    }

    /**
     * Returns the filter to exactly match the name attribute value
     * @param nameValue The value to compare
     * @return
     */
    public static Filter withName(String nameValue){
        return whereName(TextMatching.equalsValue(nameValue));
    }

    /**
     * Returns the filter to exactly match the class attribute value
     * @param classValue The value to compare
     * @return
     */
    public static Filter withClass(String classValue){
        return whereClass(TextMatching.equalsValue(classValue));
    }

    /**
     * Returns the filter to exactly match the text value
     * @param textValue The value to compare
     * @return
     */
    public static Filter withText(String textValue){
        return whereText(TextMatching.equalsValue(textValue));
    }

    /**
     * Returns the filter to exactly match the respective attributeName value
     * @param attributeName The attribute name whose value have to be compared
     * @param attributeValue The value to compare
     * @return
     */
    public static Filter withAttribute(String attributeName, String attributeValue){
        return whereAttribute(attributeName, TextMatching.equalsValue(attributeValue));
    }

    /**
     * Returns filter for id attribute using given TextMatcher.
     * Please use the static methods available inside the {@link TextMatching} class to get the
     * respective TextMatcher.
     * @param textMatcher
     * @return
     */
    public static Filter whereId(TextMatcher textMatcher){
        return new Filter(FilterType.ID, FilterType.ID.name(),textMatcher);
    }

    /**
     * Returns filter for name attribute using given TextMatcher.
     * Please use the static methods available inside the {@link TextMatching} class to get the
     * respective TextMatcher.
     * @param textMatcher
     * @return
     */
    public static Filter whereName(TextMatcher textMatcher){
        return new Filter(FilterType.NAME, FilterType.NAME.name(),textMatcher);
    }

    /**
     * Returns filter for class attribute using given TextMatcher.
     * Please use the static methods available inside the {@link TextMatching} class to get the
     * respective TextMatcher
     * @param textMatcher
     * @return
     */
    public static Filter whereClass(TextMatcher textMatcher){
        return new Filter(FilterType.CLASS,FilterType.CLASS.name(),textMatcher);
    }

    /**
     * Returns filter for filtering text of a element for a given TextMatcher.
     * Please use the static methods available inside the {@link TextMatching} class to get the
     * respective TextMatcher
     * @param textMatcher
     * @return
     */
    public static Filter whereText(TextMatcher textMatcher){
        return new Filter(FilterType.TEXT,FilterType.TEXT.name(),textMatcher);
    }

    /**
     * Returns a filter for the given attributeName value using given TextMatcher.
     * Please use the static methods available inside the {@link TextMatching} class to get the
     * respective TextMatcher.
     * @param attributeName
     * @param textMatcher
     * @return
     */
    public static Filter whereAttribute(String attributeName, TextMatcher textMatcher){
        return new Filter(FilterType.ATTRIBUTE, attributeName,textMatcher);
    }
}
