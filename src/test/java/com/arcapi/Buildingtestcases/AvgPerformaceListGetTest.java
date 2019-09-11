package com.arcapi.Buildingtestcases;

import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AvgPerformaceListGetTest extends BaseClass {

	@Test(groups="CheckAvgPerformace")
	public void AvgPerformaceList() {
		
		try {
			url = "/comparables/?state=USDC";
			CommonMethod.res= MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}