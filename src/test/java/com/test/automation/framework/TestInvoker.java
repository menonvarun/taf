package com.test.automation.framework;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestInvoker {
	
	@Test
	public void testMethod1(ITestContext context){
		
		context.setAttribute("testin1", "testing1");
		
	}
	
	@Test
	public void testMethod2(ITestContext context){
		context.setAttribute("testin2", "testing2");
		
	}

}
