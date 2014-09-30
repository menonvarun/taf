package org.imaginea.test.automation.framework.testng.listener;


import java.lang.reflect.Field;

import org.imaginea.test.automation.framework.keywordmodel.suite.ISimpleTest;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.internal.TestResult;


public class MethodInvokerListener implements IInvokedMethodListener{

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		ITestNGMethod testNGMethod = testResult.getMethod();
		Object[] params = testResult.getParameters();
		ISimpleTest testDetails=null;
		if(params.length>0){
			Object tempDetails = params[0];
			if(tempDetails instanceof ISimpleTest)
				testDetails = (ISimpleTest)tempDetails;
			
		}
		changeTestMethodName(testResult,testDetails);
		ITestNGMethod customMethod = new CustomTestNGMethod(testNGMethod, testDetails);
		((TestResult)testResult).setMethod(customMethod);		
	}
	
	private void changeTestMethodName(ITestResult result,ISimpleTest testDetails){
		String methodName="";
		if(testDetails!=null){
			methodName = testDetails.getTestId()+" : "+testDetails.getTestName();
			
			try {
				@SuppressWarnings("rawtypes")
				Class cls = result.getClass();
				Field methodNameF;
				methodNameF = cls.getDeclaredField("m_name");
				methodNameF.setAccessible(true);
				methodNameF.set(result, methodName);
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	}
}
