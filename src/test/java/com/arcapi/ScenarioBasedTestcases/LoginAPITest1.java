package com.arcapi.ScenarioBasedTestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class LoginAPITest1 extends BaseClass {

	@Test(groups="Login")
	@Parameters({ "SheetName", "rownumber", "UserName","Password"})
	public void LoginAPI(String SheetName, int rownumber,String UserName,String Password) {
	    url = "/auth/login/";
		CommonMethod.res = MethodCall.POSTRequestLogin2(url,SheetName,UserName,Password);
		
		Assertion.verifyStatusCode(CommonMethod.res, 200);

	}


}
