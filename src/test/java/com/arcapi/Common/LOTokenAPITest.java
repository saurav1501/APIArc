package com.arcapi.Common;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class LOTokenAPITest extends BaseClass {

	@Test(groups="GenerateToken")
	@Parameters({ "SheetName", "rownumber" })
	public void LOTokenAPI(String SheetName, int rownumber) {

		try {
			url="/auth/leed-online/token/";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
}
}