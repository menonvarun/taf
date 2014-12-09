---
layout: default
title: Verify Utility
permalink: /verify_util.html
heading: Verify Utility
toc: true
---

#Verify Utility

Many a time while doing automation there may be a requirement where we just have to verify a condition and continue with our test execution.

An example can be a form with 10 mandatory fields where we want to verify that an exception is thrown when a mandatory filed is not filled.
In this scenario if there are no validation done for 2nd, 5th and 6th field, while using Assert statements in our test we only get information of 2nd field validation failure but not about 5th and 6th field. 

In this case we will have enhanced test coverage if we verify all the fields first and then use the assert statement at the last.
This will give us information about all the failures in a single shot. Following is an example of the usage:

<pre class="brush: java;">

@Test
public void verifyMandatoryFormFields(){
	VerifyUtil verifyUtil = new VerifyUtil();
	userName.sendKeys("");
	verifyUtil.verifyTrue(getErrorMessage(userName) != "",
                "No error was thrown user name field");
	firstName.sendKeys("");
	verifyUtil.verifyTrue(getErrorMessage(firstName) != "",
                "No error was thrown first name field");
	lastName.sendKeys("");
	verifyUtil.verifyTrue(getErrorMessage(lastName) != "",
                "No error was thrown last name field");
	email.sendKeys("");
	verifyUtil.verifyTrue(getErrorMessage(email) != "",
                "No error was thrown email field");
	Assert.assertTrue(verifyUtil.isPassed(), verifyUtil.getFailureMessage());
				
}

</pre>