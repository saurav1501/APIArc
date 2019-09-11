package com.arcapi.Buildingtestcases;

import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ThirdPartyListAppsAPITest extends BaseClass {

	@Test(groups="CheckApps")
	public void ThirdPartyListAppsAPI() {

		url ="/apps/";
		CommonMethod.res = MethodCall.GETRequest(url);
		Assertion.verifyStatusCode(CommonMethod.res, 200);

	}
}