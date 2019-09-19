package com.arcapi.Parksmarttestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class UserActivityListAPITest extends BaseClass {

	@Test(groups="CheckActivity")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void UserActivityListAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

		try {
			url = "/assets/activity/?leed_id="+ data.getCellData(SheetName, ProjectTypeColumn, rownumber) +"&type=building";

			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	

}