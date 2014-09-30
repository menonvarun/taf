package org.imaginea.test.automation.framework.keywordmodel.keywords;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Stores method information of each of the method present inside 
 * the keyword class.
 * @author  Varun Menon
 *
 */
public class MethodInfo {
	
	private String methodName;
	private int noOfParams;
	private Method methodObject;
	private Class<?>[] prameterTypes;
	private List<Class<?>> parameterTypesAsList;
	
	public MethodInfo(Method method){
		this.methodObject = method;
		this.methodName = method.getName();
		this.noOfParams = method.getParameterTypes().length;
		this.prameterTypes = method.getParameterTypes();
		this.parameterTypesAsList = Arrays.asList(prameterTypes);
	}
	
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public int getNoOfParams() {
		return noOfParams;
	}
	public void setNoOfParams(int noOfParams) {
		this.noOfParams = noOfParams;
	}
	public Method getMethodObject() {
		return methodObject;
	}
	public void setMethodObject(Method methodObject) {
		this.methodObject = methodObject;
	}
	public Class<?>[] getPrameterTypes() {
		return prameterTypes;
	}
	public void setPrameterTypes(Class<?>[] prameterTypes) {
		this.prameterTypes = prameterTypes;
	}
	public List<Class<?>> getParameterTypesAsList() {
		return parameterTypesAsList;
	}
	public void setParameterTypesAsList(List<Class<?>> parameterTypesAsList) {
		this.parameterTypesAsList = parameterTypesAsList;
	}
	
	

}
