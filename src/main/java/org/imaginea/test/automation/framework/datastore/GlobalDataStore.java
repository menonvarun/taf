package org.imaginea.test.automation.framework.datastore;

import java.util.HashMap;
import java.util.Map;
/**
 * A global data store class to store the data that can be used in your tests.
 * The class provide utility to store the data as a static data as well as for a thread.
 * This is helpful when you want to share a particular data across multiple tests in a execution or in a 
 * particular thread when you are executing tests in parallel. 
 * @author  Varun Menon
 *
 */

public class GlobalDataStore {
	
	private static Map<String, Object> staticData;
	private static ThreadLocal<Map<String, Object>> threadedMap;
	private static GlobalDataStore INSTANCE;
	private GlobalDataStore(){
		init();		
	}
	
	/**
	 * Use this method to get the instance of the of this class.
	 *  
	 * @return {@link GlobalDataStore} singleton instance
	 */
	public static GlobalDataStore getInstance(){
		if(INSTANCE == null)
			INSTANCE = new GlobalDataStore();
		return INSTANCE;
	}

	private void init(){
		staticData = new HashMap<String, Object>();
		threadedMap = new ThreadLocal<Map<String,Object>>();
	}
	
	private void initThreadData() {
		synchronized (GlobalDataStore.class) {
			if (threadedMap.get() == null)
				threadedMap.set(new HashMap<String, Object>());
		}
	}
	
	/**
	 * Stores data in a static way this data will available to the whole execution.
	 * @param key Name to be used to refer to the said data.
	 * @param value The data object that you need to store this can be any object.
	 */
	public void putStaticData(String key, Object value){
		storeStaticData(key, value);		
	}
	
	/**
	 * Stores data in way that is will be available for a particular thread.
	 * This usefule when you executing tests in a multi-threaded environment and want to share certain 
	 * data across particular thread and not across whole execution.
	 * @param key Name to be used to refer to the said data.
	 * @param value The data object that you need to store this can be any object.
	 */
	public synchronized void putThreadData(String key, String value){
		initThreadData();
		storeThreadedData(key, value);
	}
	
	/**
	 * Use this method to retrieve the data that has been stored for a particular thread.
	 * @param key The key which was used to store the data 
	 * @return The value stored for the said key or <code>null</code> in case no key was found.
	 */
	@SuppressWarnings("unchecked")
	public synchronized <T> T getThreadData(String key){
		initThreadData();
		synchronized (GlobalDataStore.class) {
			Map<String,Object> dataMap = threadedMap.get();
			if(dataMap.containsKey(key))
				return (T) dataMap.get(key);
			return null;			
		}
		
	}
	
	/**
	 * Use this method to retrieve the data that has been stored to be available across the whole execution.
	 * @param key The key which was used to store the data 
	 * @return The value stored for the said key or <code>null</code> in case no key was found.
	 */
	@SuppressWarnings("unchecked")
	public <T> T getStaticData(String key) {
		synchronized (GlobalDataStore.class) {
			if(staticData.containsKey(key))
				return (T) staticData.get(key);
			return null;
		}
	}
	
	/**
	 * Use this method to retrieve the data that has been stored for a particular thread.
	 * @param key The key which was used to store the data 
	 * @param defaultValue The default value that needs to be returned in case the key is not found.
	 * 
	 * @return The value stored for the said key or the given <code><i>defaultValue</i></code> in case no key was found.
	 */
	@SuppressWarnings("unchecked")
	public <T> T getThreadData(String key, Object defaultValue){
		Object data = getThreadData(key);
		if(data == null)
			data = defaultValue;
		return (T) data;
	}
	
	/**
	 * Use this method to retrieve the data that has been stored to be available across the whole execution.
	 * @param key The key which was used to store the data 
	 * @param defaultValue The default value that needs to be returned in case the key is not found.
	 * 
	 * @return The value stored for the said key or the given <code><i>defaultValue</i></code> in case no key was found.
	 */
	@SuppressWarnings("unchecked")
	public <T> T getStaticData(String key, Object defaultValue) {
		Object data = getStaticData(key);
		if(data == null)
			data = defaultValue;
		return (T) data;
	}
	
	private static synchronized void storeStaticData(String key, Object value){
		staticData.put(key, value);		
	}
	
	private static synchronized void storeThreadedData(String key, Object value){		
		threadedMap.get().put(key, value);		
	}
}
