package org.imaginea.test.automation.framework.keywordmodel.executor;

import java.io.File;
import java.util.List;

import org.imaginea.test.automation.framework.keywordmodel.reader.IKeywordStore;
import org.imaginea.test.automation.framework.keywordmodel.reader.ReaderFactory;
import org.openqa.selenium.WebDriver;


/**
 * Base Keyword Executor class that helps in reading a specified keyword file 
 * and executing the respective keyword and its arguments.
 * @author  Varun Menon
 *
 */
public class KeywordExecutor {
	
	WebDriver driver;
	KeywordFactory keyfactory;
	ReaderFactory readerFactory;
	
	/**
	 * Main {@link KeywordExecutor} class constructor
	 * 
	 * @param driver {@link WebDriver} object to be set for Keyword classes
	 * @param file {@link File} object of the keyword file 
	 * @param args {@link Object Object[]} of any extra arguments that needs to be passed.
	 * Ex. Sheet Name for excel or separator for the csv file.
	 */
	public KeywordExecutor(WebDriver driver, File file, String...args){
		this.driver = driver;
		keyfactory = new KeywordFactory(driver);
		readerFactory = new ReaderFactory(file, args);		
	}
	
	/**
	 * {@link KeywordExecutor} class constructor which accepts the following params 
	 * and sets the {@link WebDriver} object for the Keyword classes as <b>null</b>.
	 * 
	 * <p> Use this constructor when your Keyword implementation classes don't need 
	 * WebDriver object for executing your keywords. 
	 * 
	 * @param file {@link File} object of the keyword file 
	 * @param args {@link Object Object[]} of any extra arguments that needs to be passed.
	 */
	public KeywordExecutor(File file, String...args){
		this(null,file,args);		
	}
	
	public KeywordExecutor(File file, List<String> arguments) {
		this(null, file, arguments);
	}

	public KeywordExecutor(WebDriver driver, File file, List<String> arguments) {
		this(driver, file, (String[]) arguments.toArray());
	}
	
	/**
	 * {@link KeywordExecutor} class constructor which accepts the following params 
	 * and sets the {@link WebDriver} object for the Keyword classes as <b>null</b>.
	 * 
	 * <p> Use this constructor when your Keyword implementation classes don't need 
	 * WebDriver object for executing your keywords. 
	 * 
	 * @param file {@link File} object of the keyword file 
	 */
	public KeywordExecutor(File file){
		this(null,file,(String[])null);		
	}
	
	/**
	 * {@link KeywordExecutor} class constructor which accepts the following params 
	 * 
	 * @param driver {@link WebDriver} object to be set for Keyword classes
	 * @param file {@link File} object of the keyword file 
	 */
	public KeywordExecutor(WebDriver driver, File file){
		this(driver,file,(String[])null);		
	}
	
	/**
	 *Executes keyword file that was passed while creating the object of {@link KeywordExecutor this} class.
	 */
	public void execute(){
		List<IKeywordStore> keys = readerFactory.getKeywordTestData();
		for(IKeywordStore key: keys){
			String keyword = key.getKeyword();
			List<Object> arguments = key.getArguments();
			keyfactory.executeKeyword(keyword, arguments.toArray());
		}
	}
	

}
