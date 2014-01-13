package com.test.automation.framework.testng.listener;


import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.internal.TestResult;

import com.test.automation.framework.keywordmodel.suite.ISimpleTest;

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
		ITestNGMethod customMethod = new CustomTestNGMethod(testNGMethod, testDetails);
		((TestResult)testResult).setMethod(customMethod);		
	}

	

}
