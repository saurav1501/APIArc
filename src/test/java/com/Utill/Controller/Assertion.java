package com.Utill.Controller;

import org.testng.Assert;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.response.Response;

public class Assertion extends BaseClass {
	
	public static void verifyTrue(boolean flag){
		Assert.assertTrue(flag);
	}
	
	public static void verifyFalse(boolean flag){
		Assert.assertFalse(flag);
	}

	public static void verifyStatusCode(Response response, int status){
		Assert.assertEquals(TestUtils.getStatusCode(response), status);
	}
	
	public static void verifyStatusMessage(Response response, String status){
		Assert.assertEquals(TestUtils.getStatusMessage(response), status);
	}
	public static void verifyData(String actual,String expected ){
		Assert.assertEquals(actual, expected);
		CommonMethod.testlog("Info", "Verifies Data " + "Actual Data is : " +actual+ " Expected Data is :" +expected);
		CommonMethod.testlog("Pass", "Verifies Data " + "Actual Data is : " +actual+ " Expected Data is :" +expected);

	}
	
}
