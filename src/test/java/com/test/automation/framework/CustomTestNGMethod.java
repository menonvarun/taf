package com.test.automation.framework;

import org.testng.ITestNGMethod;
import org.testng.internal.BaseTestMethod;
import org.testng.internal.ConstructorOrMethod;
import org.testng.internal.annotations.IAnnotationFinder;

public class CustomTestNGMethod extends BaseTestMethod {

	public CustomTestNGMethod(ConstructorOrMethod com,
			IAnnotationFinder annotationFinder, Object instance) {
		super(com, annotationFinder, instance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ITestNGMethod clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
