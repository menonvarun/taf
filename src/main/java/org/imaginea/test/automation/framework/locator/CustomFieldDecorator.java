package org.imaginea.test.automation.framework.locator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.imaginea.test.automation.framework.dom.EWebElement;
import org.imaginea.test.automation.framework.dom.ElementOptions;
import org.imaginea.test.automation.framework.pagemodel.Browser;
import org.imaginea.test.automation.framework.pagemodel.PageClass;
import org.openqa.selenium.WebDriver;
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
    protected WebDriver driver;
    protected Browser browser;

	public CustomFieldDecorator(ElementLocatorFactory factory) {
		super(factory);
		this.factory = factory;
	}

    public CustomFieldDecorator(Browser browser, ElementLocatorFactory factory) {
        super(factory);
        this.factory = factory;
        this.browser = browser;
    }

    public CustomFieldDecorator(WebDriver driver, ElementLocatorFactory factory) {
        super(factory);
        this.factory = factory;
        this.browser = new Browser();
        this.browser.setDriver(driver);
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
			return proxyForEWebElement(this.browser, loader, locator, field);
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
	protected EWebElement proxyForEWebElement(Browser browser, ClassLoader loader,
			ElementLocator locator, Field field) {
		
		InvocationHandler handler = new LocatingElementListHandler(locator);
        String name = "";
        List<Class<? extends PageClass>> navigablePageClasses = new ArrayList<>();
        boolean required = true;
		List<WebElement> proxy;
		proxy = (List<WebElement>) Proxy.newProxyInstance(loader,
				new Class[] { List.class }, handler);

        if(field.isAnnotationPresent(ElementOptions.class)){
            ElementOptions options = field.getAnnotation(ElementOptions.class);
            name = options.name();
            navigablePageClasses = Arrays.asList(options.navigablePageClasses());
            required = options.required();
        }
        if(name.contentEquals("")){
            name = field.getName();
        }
		EWebElement eWebElement = new EWebElement(browser, proxy);
        eWebElement.setName(name);
        eWebElement.setRequired(required);
        eWebElement.setNavigablePageClasses(navigablePageClasses);
		return eWebElement;

	}

}
