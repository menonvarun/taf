package com.test.automation.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.BaseTestMethod;

public class TestInvokedMethod implements IInvokedMethodListener{
	static int number=1;
	/*@Override
	public void transform(ITestAnnotation annotation, Class testClass,
			Constructor testConstructor, Method testMethod) {
		annotation.setTestName("Testing 123");
		
	}*/

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		((BaseTestMethod)method.getTestMethod()).setDescription("This is a description");
		
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		ITestNGMethod cTest = testResult.getMethod().clone();
		
		Method m = testResult.getMethod().getConstructorOrMethod().getMethod();
		testResult.getClass();
		Field f;
		try {
			f = m.getClass().getDeclaredField("name");
			f.setAccessible(true);
			f.set(m, "No, you’re not!" + number++);
		} catch (NoSuchFieldException | SecurityException e) {
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
