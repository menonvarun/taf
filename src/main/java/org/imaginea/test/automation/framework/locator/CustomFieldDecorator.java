package org.imaginea.test.automation.framework.locator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

import org.imaginea.test.automation.framework.dom.EWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementListHandler;


/**
 * Default decorator for use with PageFactory. Will decorate 1) all of the
 * WebElement fields and 2) List<WebElement> fields that have {@literal @FindBy}
 * , {@literal @FindBys}, or {@literal @FindAll} annotation with a proxy that
 * locates the elements using the passed in ElementLocatorFactory.
 */
public class CustomFieldDecorator extends DefaultFieldDecorator {

	protected ElementLocatorFactory factory;

	public CustomFieldDecorator(ElementLocatorFactory factory) {
		super(factory);
		this.factory = factory;
	}

	public Object decorate(ClassLoader loader, Field field) {
		Object decoratedField = super.decorate(loader, field);
		if (decoratedField != null) {
			return decoratedField;

		}
		if (!EWebElement.class.isAssignableFrom(field.getType()))
			return null;

		ElementLocator locator = factory.createLocator(field);
		if (locator == null) {
			return null;
		}

		if (EWebElement.class.isAssignableFrom(field.getType())) {
			return proxyForEWebElement(loader, locator);
		} else {
			return null;
		}
	}

	/**
	 * Return eWebelement object
	 * 
	 * @param loader
	 * @param locator
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected EWebElement proxyForEWebElement(ClassLoader loader,
			ElementLocator locator) {
		
		InvocationHandler handler = new LocatingElementListHandler(locator);

		List<WebElement> proxy;
		proxy = (List<WebElement>) Proxy.newProxyInstance(loader,
				new Class[] { List.class }, handler);

		EWebElement eWebElement = new EWebElement(proxy);
		return eWebElement;

	}

}
