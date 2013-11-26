package com.test.automation.framework.keywordmodel;

public interface IFileDataset {
	
	public String packagePath = "package ${packagePath}";
	public String simpleTestImportStatement = "import org.testng.annotations.Test;";
	public String classDeclaration = "\n\npublic class ${className}{\n";
	public String testAnnotation = "@Test(${testArguments})";

}
