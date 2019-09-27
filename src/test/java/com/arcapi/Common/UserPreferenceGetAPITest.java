package com.arcapi.Common;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class UserPreferenceGetAPITest extends BaseClass {
@Test(groups="CheckUserDetail")
@Parameters({ "SheetName", "rownumber" })
public void UserPreferenceGetAPI(String SheetName, int rownumber) {

	try {
		url = "/auth/user/preference/";
		CommonMethod.res = MethodCall.GETRequest(url);		
		Assertion.verifyStatusCode(CommonMethod.res, 200);
	} catch (Exception e) {
		
		e.printStackTrace();
	}

}

}
