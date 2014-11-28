package org.imaginea.test.automation.framework.util;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.imaginea.test.automation.framework.config.DefaultConfig;
import org.imaginea.test.automation.framework.locator.CustomPageFactory;
import org.imaginea.test.automation.framework.pagemodel.Browser;
import org.imaginea.test.automation.framework.pagemodel.PageClass;
import org.imaginea.test.automation.framework.pagemodel.PageException;
import org.openqa.selenium.WebDriver;

/**
 * Utility class containing mainly the util methods for the Page Class and Locator class.
 * Not meant to be used by other classes.
 * @author  Varun Menon
 *
 */
public abstract class PageObjectUtils extends Utilities{
	protected DefaultConfig config;
    protected Browser browser;

    public void setBrowser(Browser browser){
        this.browser = browser;
    }

    /**
     * Utility to create and get the said page class object.
     * This utility directly creates the said page class object and returns it. No navigation or verification is done for the said page.
     * <p>This method initializes any PageFactory based WebElements present in the said
     * class using the provided locator file.
     * @param pageClass Page class extending {@link PageClass} for which the object has to be created and returned.
     * @param customLocatorFile - <code>File</code> object of the file containing the key/value pair of the locators.
     * @return The said page class object
     */
	@SuppressWarnings("unchecked")
	public <T> T getPageObject(Class<?> pageClass, File customLocatorFile){
		validatePageClass(pageClass);
		T page = null;
		try {
			page = (T) pageClass.newInstance();
			initializePage(page, customLocatorFile);
		} catch (IllegalAccessException | InstantiationException e) {
			throw new PageException("Unable to create instance of page class \""+pageClass.getSimpleName()+"\". " +
					"Make sure you have an empty constructor defined for the said class and it is not private.");
		}
		return page;
		
	}
    /**
     * Utility to create and get the said page class object.
     * This utility directly creates the said page class object and returns it. No navigation or verification is done for the said page.
     * <p>This method initializes any PageFactory based WebElements present in the said
     * class using the provided locator file path.
     * @param pageClass Page class extending {@link PageClass} for which the object has to be created and returned.
     * @param filePath - File path of the file containing the key/value pair of the locators.
     * @return The said page class object
     */
	public <T> T getPageObject(Class<? extends PageClass> pageClass,String filePath){
		File file = new File(filePath);
		return getPageObject(pageClass,file);
	}

    /**
     * Utility to create and get the said page class object.
     * This utility directly creates the said page class object and returns it. No navigation or verification is done for the said page.
     * @param pageClass Page class extending {@link PageClass} for which the object has to be created and returned.
     * @return The said page class object
     */
	public <T> T getPageObject(Class<?> pageClass){
		return getPageObject(pageClass,null);
	}
	
	public String getUrl(PageClass page){
		String baseUrl = config.getConfigValue("base_url");
		String pageUrl = page.toUrl();
		URI finalUri = null;
		try {
			URI baseUri = new URI(baseUrl);
			URI pageUri = new URI(pageUrl);
			
			if(!pageUri.isAbsolute())
				finalUri = baseUri.resolve(pageUri);
			else
				finalUri = pageUri;
		} catch (URISyntaxException e) {
			throw new RuntimeException("Issue while getting url, make sure you had provided the correct base and page url.",e);
		}
		return finalUri.toString();		
	}
	
	public <T> T to(WebDriver driver, T page){
		String url = getUrl((PageClass) page);
		driver.get(url);
		at(page);
		return page;		
	}
    /**
     * Navigates to said page after appending the base url to Page url.
     * This utility also verifies whether the driver is at the said Page by using "at" method of the Page after navigation.
     * @param pageClass - Page class extending {@link PageClass} to where the driver have to be navigated.
     * @return The said page class object once verification is successful.
     */
	public <T> T to( Class<?> pageClass ){
		return to(pageClass,(File)null);
	}

    /**
     * Utility to verify that the driver is at said page or not.
     * @param pageClass Page class extending {@link PageClass} which have to verified to be on.
     * @return The said page class object once verification is successful.
     */
	public <T> T at( Class<?> pageClass){
		return at(pageClass,(File)null);
	}

    /**
     * Navigates to said page after appending the base url to Page url and initializes any
     * PageFactory based WebElements in the said class using the provided locator file.
     * This utility also verifies whether the driver is at the said Page by using "at" method of the Page after navigation.
     * @param pageClass - Page class extending {@link PageClass} to where the driver have to be navigated.
     * @param file - <code>File</code> object of the file containing the key/value pair of the locators.
     * @return The said page class object once verification is successful.
     */
	public <T> T to( Class<?> pageClass,File file ){
		validatePageClass(pageClass);
		T page = getPageObject(pageClass,file);
		String url = getUrl((PageClass) page);
		browser.getDriver().get(url);
		at(page);
		return page;		
	}

    /**
     * Navigates to said page after appending the base url to Page url. This method initializes any
     * PageFactory based WebElements in the said class using the provided locator file path.
     * This utility also verifies whether the driver is at the said Page by using "at" method of the Page after navigation.
     *
     * @param pageClass - Page class extending {@link PageClass} to where the driver have to be navigated.
     * @param filePath  - File path of the file containing the key/value pair of the locators.
     * @return The said page class object once verification is successful.
     */
    public <T> T to(Class<?> pageClass, String filePath) {
        File file = new File(filePath);
        return to(pageClass, file);
    }

    /**
     * Utility to verify that the driver is at said page or not.
     * This method initializes any PageFactory based WebElements in the said
     * class using the provided locator file path.
     * @param pageClass Page class extending {@link PageClass} which have to verified to be on.
     * @param filePath - File path of the file containing the key/value pair of the locators.
     * @return The said page class object once verification is successful.
     */
    public <T> T at( Class<?> pageClass,String filePath){
		File file = new File(filePath);
		return at(pageClass,file);
	}

    /**
     * Utility to verify that the driver is at said page or not.
     * This method initializes any PageFactory based WebElements in the said
     * class using the provided locator file.
     * @param pageClass Page class extending {@link PageClass} which have to verified to be on.
     * @param file - <code>File</code> object of the file containing the key/value pair of the locators.
     * @return The said page class object once verification is successful.
     */
	public <T> T at( Class<?> pageClass,File file){
		T page = getPageObject(pageClass,file);
		return at(page);
	}

    /**
     * Navigates to said page after appending the base url to Page url.
     * This utility also verifies whether the driver is at the said Page by using "at" method of the Page after navigation.
     * @param page - Object of the Page class extending {@link PageClass} to where the driver have to be navigated.
     * @return The said page class object once verification is successful.
     */
    public <T> T to( T page ){
        return to(page,(File)null);
    }

    /**
     * Utility to verify that the driver is at said page or not.
     * @param page Object of the Page class extending {@link PageClass} which have to verified to be on.
     * @return The said page class object once verification is successful.
     */
    public <T> T at( T page){
        return at(page,(File)null);
    }

    /**
     * Navigates to said page after appending the base url to Page url and initializes any
     * PageFactory based WebElements in the said class using the provided locator file.
     * This utility also verifies whether the driver is at the said Page by using "at" method of the Page after navigation.
     * @param page - Object of the Page class extending {@link PageClass} to where the driver have to be navigated.
     * @param file - <code>File</code> object of the file containing the key/value pair of the locators.
     * @return The said page class object once verification is successful.
     */
    public <T> T to( T page,File file ){
        validatePageClass(page.getClass());
        initializePage(page, file);
        String url = getUrl((PageClass) page);
        browser.getDriver().get(url);
        at(page);
        return page;
    }

    /**
     * Navigates to said page after appending the base url to Page url. This method initializes any
     * PageFactory based WebElements in the said class using the provided locator file path.
     * This utility also verifies whether the driver is at the said Page by using "at" method of the Page after navigation.
     *
     * @param page - Object of the Page class extending {@link PageClass} to where the driver have to be navigated.
     * @param filePath  - File path of the file containing the key/value pair of the locators.
     * @return The said page class object once verification is successful.
     */
    public <T> T to( T page,String filePath){
        File file = new File(filePath);
        return to(page,file);
    }

    /**
     * Utility to verify that the driver is at said page or not.
     * This method initializes any PageFactory based WebElements in the said
     * class using the provided locator file path.
     * @param page Object of the Page class extending {@link PageClass} which have to verified to be on.
     * @param filePath - File path of the file containing the key/value pair of the locators.
     * @return The said page class object once verification is successful.
     */
    public <T> T at( T page,String filePath){
        File file = new File(filePath);
        return at(page,file);
    }

    /**
     * Utility to verify that the driver is at said page or not.
     * This method initializes any PageFactory based WebElements in the said
     * class using the provided locator file.
     * @param page Object of the Page class extending {@link PageClass} which have to verified to be on.
     * @param file - <code>File</code> object of the file containing the key/value pair of the locators.
     * @return The said page class object once verification is successful.
     */
    public <T> T at( T page,File file){
        validatePageClass(page.getClass());
        initializePage(page,file);
        boolean atPage = ((PageClass) page).at();
        if(!atPage){
            throw new RuntimeException("Driver is not at the said page: "+page.getClass().getSimpleName());
        }
        return page;
    }

    /**
     * Similar to {@link #at(Class)} method, instead it return true or false based on the at verification done.
     * @param page Object of the Page class extending {@link PageClass} which have to verified to be on.
     * @return true or false
     */
	public <T> boolean isAt(T page){
        validatePageClass(page.getClass());
        initializePage(page, (File) null);
		boolean atPage = ((PageClass) page).at();
		return atPage;
	}

    /**
     * Similar to {@link #at(Class,java.io.File)} method, instead it return true or false based on the at verification done.
     * This method initializes any PageFactory based WebElements in the said
     * class using the provided locator file.
     * @param pageClass Page class extending {@link PageClass} which have to verified to be on.
     * @param file - <code>File</code> object of the file containing the key/value pair of the locators.
     * @return true or false
     */
	public <T> boolean isAt( Class<?> pageClass, File file){
		T page = getPageObject(pageClass,file);
		return isAt(page);
	}

    /**
     * Similar to {@link #at(Class,java.lang.String)} method, instead it return true or false based on the at verification done.
     * This method initializes any PageFactory based WebElements present in the said
     * class using the provided locator file path.
     * @param pageClass Page class extending {@link PageClass} which have to verified to be on.
     * @param filePath - File path of the file containing the key/value pair of the locators.
     * @return true or false
     */
	public <T> boolean isAt( Class<?> pageClass, String filePath){
		File file = new File(filePath);
		return isAt(pageClass,file);
	}

    /**
     * Similar to {@link #at(Class)} method, instead it return true or false based on the at verification done.
     * @param pageClass Page class extending {@link PageClass} which have to verified to be on.
     * @return true or false
     */
	public <T> boolean isAt( Class<?> pageClass){
		return isAt(pageClass,(File)null);
	}

    /**
     * Similar to {@link #at(T,java.io.File)} method, instead it return true or false based on the at verification done.
     * This method initializes any PageFactory based WebElements in the said
     * class using the provided locator file.
     * @param page Object of the Page class extending {@link PageClass} which have to verified to be on.
     * @param file - <code>File</code> object of the file containing the key/value pair of the locators.
     * @return true or false
     */
    public <T> boolean isAt( T page, File file){
        validatePageClass(page.getClass());
        initializePage(page, file);
        return isAt(page);
    }

    /**
     * Similar to {@link #at(Class)} method, instead it return true or false based on the at verification done.
     * This method initializes any PageFactory based WebElements present in the said
     * class using the provided locator file path.
     * @param page Page class extending {@link PageClass} which have to verified to be on.
     * @param filePath - File path of the file containing the key/value pair of the locators.
     * @return true or false
     */
    public <T> boolean isAt( T page, String filePath){
        File file = new File(filePath);
        return isAt(page, file);
    }

    private void validatePageClass(Class<?> pageClass){
		if(!PageClass.class.isAssignableFrom(pageClass)){
			throw new PageException("The passed class: "+pageClass.getSimpleName()+
					" does not extend PageClass. Please make sure all your page classes extend PageClass.");
			
		}
	}

    private <T> void initializePage( T page, File customLocatorFile){
        ((PageClass) page).init(browser);

        File locatorFile = ((PageClass) page).getLocatorFile();

        if(customLocatorFile!=null)
            CustomPageFactory.initElements(browser, page, customLocatorFile);
        else if(locatorFile!=null)
            CustomPageFactory.initElements(browser, page, locatorFile);
        else
            CustomPageFactory.initElements(browser, page);
    }

}
