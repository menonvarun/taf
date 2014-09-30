package org.imaginea.test.automation.framework.keywordmodel.keywords;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.imaginea.test.automation.framework.keywordmodel.KeywordException;


/**
 * Base keyword class that implements the <code>IKeyword</code> interface.
 * This class provides utility to execute the keywords using the Java reflection api.
 * <p> Extend this class if you want your methods to be used as keywords.
 * 
 * @author  Varun Menon
 *
 */
public abstract class KeywordBase implements IKeyword{
	
	Map<String,Map<Integer,List<MethodInfo>>> methods = new HashMap<String, Map<Integer,List<MethodInfo>>>();
	private Object executingObject=null;
	private boolean initialized = false;
	
	protected KeywordBase() {
		initialize();
	}
	
	private void initialize(){
		executingObject = this;
		initialize(executingObject);				
	}
	
	/**
	 * Initializes the class to collect the information of the methods available in the said class.
	 * Use this method if you want to initialize methods from some other class object. 
	 * @param obj Object of the class whose method you to use for keyword execution.
	 */
	protected void initialize(Object obj){
		Class<?> cls = obj.getClass();
		methods.clear();
		Iterator<Method> it = Arrays.asList(cls.getMethods()).iterator();
		while (it.hasNext()) {
			Method method = it.next();
			MethodInfo methodInfo = new MethodInfo(method);
			List<MethodInfo> infoList;
			Map<Integer,List<MethodInfo>> paramMethodMap;
			String methodName = method.getName();
			int noOfParams = method.getParameterTypes().length;
			
			if(methods.containsKey(methodName)){
				paramMethodMap = methods.get(methodName);				
				if(paramMethodMap.containsKey(noOfParams)){
					infoList = paramMethodMap.get(noOfParams);										
				} else {
					infoList = new ArrayList<MethodInfo>();										
				}
				infoList.add(methodInfo);
				paramMethodMap.put(noOfParams, infoList);
			} else {
				paramMethodMap = new HashMap<Integer,List<MethodInfo>>();
				infoList = new ArrayList<MethodInfo>();
				infoList.add(methodInfo);
				paramMethodMap.put(noOfParams, infoList);				
			}
			
			methods.put(methodName, paramMethodMap);
		}
		this.initialized = true;
	}
	
	@Override
	public boolean isSupported(String methodName, Object[] args){
		if(!initialized){
			initialize();
		}
		if(!methods.containsKey(methodName)){
			return false;
		}
		Map<Integer,List<MethodInfo>> paramMethodInfos = methods.get(methodName);
		boolean supported = false;
		
		if(paramMethodInfos.containsKey(args.length)){
			supported = true;
		}
		
		return supported;				
	}
	
	@Override
	public void execute(String keyword, Object[] args){
		Map<Integer,List<MethodInfo>> paramMethodInfos = methods.get(keyword);
		boolean executed = false;
		boolean canBeExecuted = false;
		if(paramMethodInfos.containsKey(args.length)){
			List<MethodInfo> methodInfos = paramMethodInfos.get(args.length);
			for(MethodInfo methodInfo:methodInfos){
				Object[] convertedParams = convertParamTypes(methodInfo.getPrameterTypes(), args);
				if(convertedParams != null){
					canBeExecuted = true;					
				}
				if(canBeExecuted){
					Method method = methodInfo.getMethodObject();
					try {
						method.invoke(executingObject, convertedParams);
						executed = true;
						break;
					} catch (IllegalAccessException | IllegalArgumentException escape){
						/*
						 * Intentionally escaping the exception so that we can continue with the execution
						 * of any other supported method that is available. 
						 */
					}catch(InvocationTargetException e) {
						throw new KeywordException(e);						
					}
				}
								
			}			
		}
		if(!executed){
			throw new KeywordException("Unable to find any key word with name: \""+keyword+
					"\" and arguments: \""+args.toString()+" in class: \""+
					this.getClass().getName()+"\".");
		}
	}
	
	private Object[] convertParamTypes(Class<?>[] methodParams, Object[] passedParams){
		List<Object> paramTypes = new ArrayList<Object>();
		for(int itr = 0; itr < methodParams.length; itr++){
			Class<?> paramType = methodParams[itr];
			Object data = getConvertedParam(paramType, passedParams[itr]);
			if(data == null){
				return null;
			}
			paramTypes.add(data);
		}	
		
		return paramTypes.toArray();
	}
	
	private Object getConvertedParam(Class<?> fieldType, Object obj) {
		String value = (String) obj;
		
		Object data = null;
		try {
			if (fieldType == int.class || fieldType == Integer.class) {
				data = value.length() == 0 ? null : Integer.parseInt(value);
			} else if (fieldType == String.class) {
				data = value.length() == 0 ? null : value;
			} else if (fieldType == Boolean.class || fieldType == boolean.class) {
				data = value.length() == 0 ? null : Boolean.parseBoolean(value);
			} else if (fieldType == float.class || fieldType == Float.class) {
				data = value.length() == 0 ? null : Float.parseFloat(value);
			} else if (fieldType == long.class || fieldType == Long.class) {
				data = value.length() == 0 ? null : Long.parseLong(value);
			} else if (fieldType == double.class || fieldType == Double.class) {
				data = value.length() == 0 ? null : Double.parseDouble(value);
			} else if (fieldType == short.class || fieldType == Short.class) {
				data = value.length() == 0 ? null : Short.parseShort(value);
			} else if (fieldType == char.class || fieldType == Character.class) {
				data = value.length() == 0 ? null : value.charAt(0);
			} else if (fieldType == byte.class || fieldType == Byte.class) {
				data = value.length() == 0 ? null : Byte.valueOf(value);
			}
		} catch (NumberFormatException e) {

		}
		return data;
	}
	

}
