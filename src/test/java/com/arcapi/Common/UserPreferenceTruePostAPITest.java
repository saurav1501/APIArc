package com.arcapi.Common;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class UserPreferenceTruePostAPITest extends BaseClass {
@Test(groups="CheckUserDetail")
@Parameters({ "SheetName", "rownumber" })
public void UserPreferencePostAPI(String SheetName, int rownumber) {

	try {
		url = "/auth/user/preference/";
		map = new HashMap<>();
		map.put("ptype","score_version_notifictaion");
		map.put("is_selected",true);
		CommonMethod.res = MethodCall.POSTRequest(url,map);		
		Assertion.verifyStatusCode(CommonMethod.res, 200);
	
	} catch (Exception e) {
		
		e.printStackTrace();
	}

}

}
