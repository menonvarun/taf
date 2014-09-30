package org.imaginea.test.automation.framework.testng.listener;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.imaginea.test.automation.framework.keywordmodel.suite.ISimpleTest;
import org.testng.IClass;
import org.testng.IRetryAnalyzer;
import org.testng.ITestClass;
import org.testng.ITestNGMethod;
import org.testng.internal.ConstructorOrMethod;
import org.testng.xml.XmlTest;


public class CustomTestNGMethod implements ITestNGMethod{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3491580028168342364L;
	private ITestNGMethod originalTestNGMethod;
	private ISimpleTest testDetails;
	public CustomTestNGMethod(ITestNGMethod originalTestNGMethod, ISimpleTest testDetails) {
		this.originalTestNGMethod = originalTestNGMethod;	
		this.testDetails = testDetails;
	}
	
	@Override
	public ITestNGMethod clone(){
		return this.originalTestNGMethod.clone();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(Object o) {
		return this.originalTestNGMethod.compareTo(o);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getRealClass() {
		return this.originalTestNGMethod.getRealClass();
	}

	@Override
	public ITestClass getTestClass() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getTestClass();
	}

	@Override
	public void setTestClass(ITestClass cls) {
		this.originalTestNGMethod.setTestClass(cls);		
	}

	@Override
	@Deprecated
	public
	Method getMethod() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getMethod();
	}

	@Override
	public String getMethodName() {
		String methodName;
		if(testDetails!=null){
			methodName = testDetails.getTestId()+" : "+testDetails.getTestName();
		}else{
			methodName= this.originalTestNGMethod.getMethodName();
		}
		return methodName;
	}

	@Override
	@Deprecated
	public
	Object[] getInstances() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getInstances();
	}

	@Override
	public Object getInstance() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getInstance();
	}

	@Override
	public long[] getInstanceHashCodes() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getInstanceHashCodes();
	}

	@Override
	public String[] getGroups() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getGroups();
	}

	@Override
	public String[] getGroupsDependedUpon() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getGroupsDependedUpon();
	}

	@Override
	public String getMissingGroup() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getMissingGroup();
	}

	@Override
	public void setMissingGroup(String group) {
		this.originalTestNGMethod.setMissingGroup(group);
		
	}

	@Override
	public String[] getBeforeGroups() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getBeforeGroups();
	}

	@Override
	public String[] getAfterGroups() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getAfterGroups();
	}

	@Override
	public String[] getMethodsDependedUpon() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getMethodsDependedUpon();
	}

	@Override
	public void addMethodDependedUpon(String methodName) {
		this.originalTestNGMethod.addMethodDependedUpon(methodName);
		
	}

	@Override
	public boolean isTest() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.isTest();
	}

	@Override
	public boolean isBeforeMethodConfiguration() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.isBeforeMethodConfiguration();
	}

	@Override
	public boolean isAfterMethodConfiguration() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.isAfterMethodConfiguration();
	}

	@Override
	public boolean isBeforeClassConfiguration() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.isBeforeClassConfiguration();
	}

	@Override
	public boolean isAfterClassConfiguration() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.isAfterClassConfiguration();
	}

	@Override
	public boolean isBeforeSuiteConfiguration() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.isBeforeSuiteConfiguration();
	}

	@Override
	public boolean isAfterSuiteConfiguration() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.isAfterSuiteConfiguration();
	}

	@Override
	public boolean isBeforeTestConfiguration() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.isBeforeTestConfiguration();
	}

	@Override
	public boolean isAfterTestConfiguration() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.isAfterTestConfiguration();
	}

	@Override
	public boolean isBeforeGroupsConfiguration() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.isBeforeGroupsConfiguration();
	}

	@Override
	public boolean isAfterGroupsConfiguration() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.isAfterGroupsConfiguration();
	}

	@Override
	public long getTimeOut() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getTimeOut();
	}

	@Override
	public void setTimeOut(long timeOut) {
		this.originalTestNGMethod.setTimeOut(timeOut);
		
	}

	@Override
	public int getInvocationCount() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getInvocationCount();
	}

	@Override
	public void setInvocationCount(int count) {
		this.originalTestNGMethod.setInvocationCount(count);
		
	}

	@Override
	public int getSuccessPercentage() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getSuccessPercentage();
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getId();
	}

	@Override
	public void setId(String id) {
		this.originalTestNGMethod.setId(id);
		
	}

	@Override
	public long getDate() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getDate();
	}

	@Override
	public void setDate(long date) {
		this.originalTestNGMethod.setDate(date);
		
	}

	@Override
	public boolean canRunFromClass(IClass testClass) {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.canRunFromClass(testClass);
	}

	@Override
	public boolean isAlwaysRun() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.isAlwaysRun();
	}

	@Override
	public int getThreadPoolSize() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getThreadPoolSize();
	}

	@Override
	public void setThreadPoolSize(int threadPoolSize) {
		this.originalTestNGMethod.setThreadPoolSize(threadPoolSize);
		
	}

	@Override
	public boolean getEnabled() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getEnabled();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getDescription();
	}

	@Override
	public void incrementCurrentInvocationCount() {
		this.originalTestNGMethod.incrementCurrentInvocationCount();
		
	}

	@Override
	public int getCurrentInvocationCount() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getCurrentInvocationCount();
	}

	@Override
	public void setParameterInvocationCount(int n) {
		this.originalTestNGMethod.setParameterInvocationCount(n);
		
	}

	@Override
	public int getParameterInvocationCount() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getParameterInvocationCount();
	}

	@Override
	public IRetryAnalyzer getRetryAnalyzer() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getRetryAnalyzer();
	}

	@Override
	public void setRetryAnalyzer(IRetryAnalyzer retryAnalyzer) {
		this.originalTestNGMethod.setRetryAnalyzer(retryAnalyzer);
		
	}

	@Override
	public boolean skipFailedInvocations() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.skipFailedInvocations();
	}

	@Override
	public void setSkipFailedInvocations(boolean skip) {
		this.originalTestNGMethod.setSkipFailedInvocations(skip);
	}

	@Override
	public long getInvocationTimeOut() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getInvocationTimeOut();
	}

	@Override
	public boolean ignoreMissingDependencies() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.ignoreMissingDependencies();
	}

	@Override
	public void setIgnoreMissingDependencies(boolean ignore) {
		this.originalTestNGMethod.setIgnoreMissingDependencies(ignore);
		
	}

	@Override
	public List<Integer> getInvocationNumbers() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getInvocationNumbers();
	}

	@Override
	public void setInvocationNumbers(List<Integer> numbers) {
		this.originalTestNGMethod.setInvocationNumbers(numbers);
		
	}

	@Override
	public void addFailedInvocationNumber(int number) {
		this.originalTestNGMethod.addFailedInvocationNumber(number);
		
	}

	@Override
	public List<Integer> getFailedInvocationNumbers() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getFailedInvocationNumbers();
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getPriority();
	}

	@Override
	public void setPriority(int priority) {
		this.originalTestNGMethod.setPriority(priority);		
	}

	@Override
	public XmlTest getXmlTest() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getXmlTest();
	}

	@Override
	public ConstructorOrMethod getConstructorOrMethod() {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.getConstructorOrMethod();
	}

	@Override
	public Map<String, String> findMethodParameters(XmlTest test) {
		// TODO Auto-generated method stub
		return this.originalTestNGMethod.findMethodParameters(test);
	}
	
	public String toString(){
		return this.originalTestNGMethod.toString();
	}

}
