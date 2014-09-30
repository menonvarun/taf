package org.imaginea.test.automation.framework.keywordmodel.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Factory class to find the supported readers for the keyword files.
 * This class also provides utils to parse the keyword files.  
 * @author  Varun Menon
 *
 */
public class ReaderFactory {
	File file;
	String[] arguments;
	List<IKeywordReader> keyReaders = new ArrayList<IKeywordReader>();
	
	/**
	 * Creates {@link ReaderFactory} object by using the {@link File} object passed.
	 * @param file {@link File} object of the keyword file 
	 */
	public ReaderFactory(File file){
		this.file = file;
		this.arguments = null;
		initialize();
	}
	
	/**
	 * Creates {@link ReaderFactory} object by using the {@link File} object passed.
	 * @param file {@link File} object of the keyword file 
	 * @param arguments {@link Object Object[]} of any extra arguments that needs to be passed.
	 * Ex. Sheet Name for excel or separator for the csv file.
	 */
	public ReaderFactory(File file, String... arguments){
		this.file = file;
		this.arguments = arguments;
		initialize();
	}
	
	private void initialize(){
		keyReaders.add(new ExcelKeywordReader());
		keyReaders.add(new CsvKeywordReader());		
	}
	
	/**
	 * Get the keyword test case data stored in the file. 
	 * @return List of {@link org.imaginea.test.automation.framework.keywordmodel.reader.IKeywordStore IKeywordStore} 
	 * after parsing the file passed to the constructor <br/> {@link #ReaderFactory(File)} or {@link #ReaderFactory(File, String...)}
	 */
	public List<IKeywordStore> getKeywordTestData(){
		boolean supported = false;
		IKeywordReader supportedReader = null;
		for(IKeywordReader reader : keyReaders){
			if(reader.isSupported(file)){
				supported = true;
				supportedReader = reader;
				break;
			}			
		}
		if(supported){
			return getData(supportedReader);
		}else{
			throw new KeywordReaderException("Unable to find any supported reader for file: "+ file.getName());
		}
		
	}
	
	private List<IKeywordStore> getData(IKeywordReader reader){
		if(this.arguments == null){
			return reader.readFile(this.file);
		}else{
			return reader.readFile(file, arguments);
		}
	}

}
