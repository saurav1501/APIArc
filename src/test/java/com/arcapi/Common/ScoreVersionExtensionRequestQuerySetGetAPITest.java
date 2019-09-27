package com.arcapi.Common;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ScoreVersionExtensionRequestQuerySetGetAPITest extends BaseClass{
	
	@Test(groups="CheckAsset")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void ScoreVersionExtensionRequestQuerySetGetAPI(String SheetName,String ProjectTypeColumn, int rownumber)  {

		try {
			url="/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/scores/version/extension/request/?q=10";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 404);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}



}


