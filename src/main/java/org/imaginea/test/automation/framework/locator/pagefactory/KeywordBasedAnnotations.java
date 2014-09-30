package org.imaginea.test.automation.framework.locator.pagefactory;

import java.lang.reflect.Field;

import org.imaginea.test.automation.framework.locator.locatorfiles.ILocatorFile;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.pagefactory.Annotations;


/**
 * Extension of the Page factory Annotation
 * @author  Varun Menon
 *
 */
public class KeywordBasedAnnotations extends Annotations{
	ILocatorFile locatorFile;
	Field field;
	
	public KeywordBasedAnnotations(ILocatorFile locatorFile,Field field) {
		super(field);
		this.field = field;
		this.locatorFile = locatorFile;
	}
	
	protected By buildByFromLongFindBy(FindBy findBy) {
		How how = findBy.how();
	    String using = locatorFile.getLocatorFor(findBy.using());

	    switch (how) {
	      case CLASS_NAME:
	        return By.className(using);

	      case CSS:
	        return By.cssSelector(using);

	      case ID:
	        return By.id(using);

	      case ID_OR_NAME:
	        return new ByIdOrName(using);

	      case LINK_TEXT:
	        return By.linkText(using);

	      case NAME:
	        return By.name(using);

	      case PARTIAL_LINK_TEXT:
	        return By.partialLinkText(using);

	      case TAG_NAME:
	        return By.tagName(using);

	      case XPATH:
	        return By.xpath(using);

	      default:
	        // Note that this shouldn't happen (eg, the above matches all
	        // possible values for the How enum)
	        throw new IllegalArgumentException("Cannot determine how to locate element " + this.field);
	    }
		
	}
	
	protected By buildByFromShortFindBy(FindBy findBy) {
		if (!"".equals(findBy.className()))
			return By.className(locatorFile.getLocatorFor(findBy.className()));

		if (!"".equals(findBy.css()))
			return By.cssSelector(locatorFile.getLocatorFor(findBy.css()));

		if (!"".equals(findBy.id()))
			return By.id(locatorFile.getLocatorFor(findBy.id()));

		if (!"".equals(findBy.linkText()))
			return By.linkText(locatorFile.getLocatorFor(findBy.linkText()));

		if (!"".equals(findBy.name()))
			return By.name(locatorFile.getLocatorFor(findBy.name()));

		if (!"".equals(findBy.partialLinkText()))
			return By.partialLinkText(locatorFile.getLocatorFor(findBy.partialLinkText()));

		if (!"".equals(findBy.tagName()))
			return By.tagName(locatorFile.getLocatorFor(findBy.tagName()));

		if (!"".equals(findBy.xpath()))
			return By.xpath(locatorFile.getLocatorFor(findBy.xpath()));

		// Fall through
		return null;

	}

}
