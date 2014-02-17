package com.menonvarun.test.automation.framework.util;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;

import com.menonvarun.test.automation.framework.config.DefaultConfig;
import com.menonvarun.test.automation.framework.locator.CustomPageFactory;
import com.menonvarun.test.automation.framework.pagemodel.Browser;
import com.menonvarun.test.automation.framework.pagemodel.PageClass;
import com.menonvarun.test.automation.framework.pagemodel.PageException;
/**
 * Utility class containing mainly the util methods for the Page Class and Locator class.
 * Not meant to be used by other classes.
 * @author  Varun Menon
 *
 */
public class CommonMethods {
	DefaultConfig config;
	
	public CommonMethods(){
		this(DefaultConfig.getDefaultConfig());
	}
	
	public CommonMethods(DefaultConfig config){
		this.config = config;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getPageObject(Browser browser,Class<?> pageClass, File customLocatorfile){
		validatePageClass(pageClass);
		T page = null;
		try {
			page = (T) pageClass.newInstance();
			((PageClass) page).init(browser);
			
			File locatorFile = ((PageClass) page).getLocatorFile();
			
			if(customLocatorfile!=null)
				CustomPageFactory.initElements(browser, page, customLocatorfile);
			else if(locatorFile!=null)
				CustomPageFactory.initElements(browser, page, locatorFile);
			else
				CustomPageFactory.initElements(browser, page);
				
		} catch (IllegalAccessException | InstantiationException e) {
			throw new PageException("Unable to create instance of page class \""+pageClass.getSimpleName()+"\". " +
					"Make sure you have an empty constructor defined for the said class and it is not private.");
		}
		return page;
		
	}
	
	public <T> T getPageObject(Browser browser,Class<?> pageClass){
		return getPageObject(browser, pageClass,null);		
	}
	
	public String getUrl(PageClass page){
		String baseUrl = config.getConfigValue("base_url");
		String pageUrl = page.toUrl();
		URI finalUri = null;
		try {
			URI baseUri = new URI(baseUrl);
			URI pageUri = new URI(pageUrl);
			
			finalUri = baseUri.resolve(pageUri);
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
	
	public <T> T to(Browser browser, Class<?> pageClass ){
		return to(browser,pageClass,(File)null);		
	}
	
	public <T> T at(Browser browser, Class<?> pageClass){
		return at(browser,pageClass,(File)null);
	}
	
	public <T> T to(Browser browser, Class<?> pageClass,File file ){
		validatePageClass(pageClass);
		T page = getPageObject(browser, pageClass,file);
		String url = getUrl((PageClass) page);
		browser.getDriver().get(url);
		at(page);
		return page;		
	}
	
	public <T> T to(Browser browser, Class<?> pageClass,String filePath){
		File file = new File(filePath);
		return to(browser,pageClass,file);
	}	
	
	public <T> T at(Browser browser, Class<?> pageClass,String filePath){
		File file = new File(filePath);
		return at(browser,pageClass,file);
	}
	
	public <T> T at(Browser browser, Class<?> pageClass,File file){
		T page = getPageObject(browser, pageClass,file);
		return at(page);
	}
	
	public <T> T at(T page){
		boolean atPage = ((PageClass) page).at();
		if(!atPage){
			throw new RuntimeException("Driver is not at the said page: "+page.getClass().getSimpleName());
		}
		return page;
	}
	
	public <T> boolean isAt(T page){
		boolean atPage = ((PageClass) page).at();
		return atPage;
	}
	
	public <T> boolean isAt(Browser browser, Class<?> pageClass, File file){
		T page = getPageObject(browser, pageClass,file);
		return isAt(page);
	}
	
	public <T> boolean isAt(Browser browser, Class<?> pageClass, String filePath){
		File file = new File(filePath);
		return isAt(browser,pageClass,file);
	}
	
	public <T> boolean isAt(Browser browser, Class<?> pageClass){
		return isAt(browser,pageClass,(File)null);
	}
	
	private void validatePageClass(Class<?> pageClass){
		if(!PageClass.class.isAssignableFrom(pageClass)){
			throw new PageException("The passed class: "+pageClass.getSimpleName()+
					" does not extend PageClass. Please make sure all your page classes extend PageClass.");
			
		}
	}

}
