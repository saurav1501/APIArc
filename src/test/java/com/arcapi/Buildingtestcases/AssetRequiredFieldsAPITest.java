package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetRequiredFieldsAPITest extends BaseClass {

	@Test(groups="CheckRequiredFields")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetRequiredFieldsAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

		try {
			url = "/assets/LEED:" +  data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/requiredfields/?page=all";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(	CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}