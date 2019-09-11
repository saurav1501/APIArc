package com.arcapi.Buildingtestcases;

import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class RatingSystemListAPITest extends BaseClass {

	@Test(groups="CheckRatingSystem")
	public void RatingSystemListAPI() {
		try {
			url = "/ratingsystem/";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}