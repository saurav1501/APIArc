package com.arcapi.Common;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class SocreVersionUpdatePostAPITest extends BaseClass{
	
	@Test(groups="CheckAsset")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void SocreVersionUpdatePostAPI(String SheetName,String ProjectTypeColumn, int rownumber)  {

		try {
			map = new HashMap<>();
			map.put("new_version", "10");
			
			url="/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/scores/version/update/";
			CommonMethod.res = MethodCall.POSTRequest(url, map);
			Assertion.verifyStatusCode(CommonMethod.res, 400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}



}


