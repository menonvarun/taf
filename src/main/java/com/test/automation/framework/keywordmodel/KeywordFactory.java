package com.test.automation.framework.keywordmodel;

import java.util.ArrayList;
import java.util.List;

import com.test.automation.framework.config.DefaultConfig;

public class KeywordFactory {
	
	DefaultConfig config = DefaultConfig.getDefaultConfig();
	List<IKeyword> keywordClassObjects;
	
	public KeywordFactory(){
		this.keywordClassObjects = getKeywordImplementations(); 	
	}
	
	private List<IKeyword> getKeywordImplementations(){
		String[] keywordClasses = config.getConfigValue("listeners").split(",");
		ClassLoader cloader = this.getClass().getClassLoader();
		List<Class<?>> listenerClasses = new ArrayList<Class<?>>();
		List<IKeyword> keywordClsObjs = new ArrayList<IKeyword>();
		for(String keywordClass:keywordClasses){
			try{
				Class<?> cls = cloader.loadClass(keywordClass);
				listenerClasses.add(cls);
			}catch(ClassNotFoundException e){
				throw new KeywordException("Unable to find class with name as mentione in listner property: "+
						keywordClass+" Make sure the class is present in the classpath.");				
			}
		}
		
		
		for (Class<?> cls : listenerClasses) {
			if (IKeyword.class.isAssignableFrom(cls)) {
				IKeyword obj = null;
				try {
					obj = (IKeyword) cls.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					throw new KeywordException("Unable to find an empty argument constructor for the keyword class:"+cls.getName());
				}
				if(obj!=null){
					keywordClsObjs.add(obj);
				}
				
			}
		}
		return keywordClsObjs;
	}
	
	public void executeKeyword(String keyword, Object[] args){
		boolean executed = false;
		for(IKeyword keywordCls : keywordClassObjects){
			boolean supported = keywordCls.isSupported(keyword, args);
			if(supported){
				keywordCls.execute(keyword, args);
				executed = true;
			}
		}
		if(!executed){
			throw new KeywordException("Unable to find any keyword class that support keyword: \""+keyword+"\" and arguments: \""+args+"\".");			
		}
		
	}
	
	 

}
