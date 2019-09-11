package com.arcapi.Common;

import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class PerformanceComparablesGetAPITest extends BaseClass {

	@Test(groups="CheckPerformance")
	public void PerformanceComparablesGetAPI() {

		try {
			url = "/comparables/";
			CommonMethod.res= MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	
	}

	}