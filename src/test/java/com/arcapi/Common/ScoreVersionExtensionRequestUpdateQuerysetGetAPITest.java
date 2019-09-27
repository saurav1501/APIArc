package com.arcapi.Common;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ScoreVersionExtensionRequestUpdateQuerysetGetAPITest extends BaseClass{
	
	@Test(groups="CheckAsset")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void ScoreVersionExtensionRequestUpdateQuerysetGetAPI(String SheetName,String ProjectTypeColumn, int rownumber)  {

		try {
			url="/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/scores/version/extension/request/update/";
			CommonMethod.res = MethodCall.DELETERequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 404);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}



}


