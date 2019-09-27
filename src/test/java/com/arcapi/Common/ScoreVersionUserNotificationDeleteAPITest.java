package com.arcapi.Common;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ScoreVersionUserNotificationDeleteAPITest extends BaseClass{
	
	@Test(groups="CheckAsset")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void ScoreVersionUserNotificationDeleteAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		try {
			url="/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/scores/version/user/notification/";
			CommonMethod.res = MethodCall.DELETERequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}



}


