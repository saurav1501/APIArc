package com.arcapi.Common;

import org.testng.annotations.Test;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class UserAccessLevelSAPGetTest extends BaseClass {

	@Test(groups="UserAccess")
	public void UserAccessLevelSAPGet() {

		try {
			url ="/auth/access/";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
}