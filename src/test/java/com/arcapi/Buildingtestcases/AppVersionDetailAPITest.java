package com.arcapi.Buildingtestcases;

import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AppVersionDetailAPITest extends BaseClass {

	@Test(groups="CheckAppVersion")
	public void AppVersionDetailAPI() {
		
		try {
			url = "/version/";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(	CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}



}