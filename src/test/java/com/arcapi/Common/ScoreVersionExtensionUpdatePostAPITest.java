package com.arcapi.Common;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ScoreVersionExtensionUpdatePostAPITest extends BaseClass{
	
	@Test(groups="CheckAsset")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void ScoreVersionExtensionUpdatePost(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		try {
			url="/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/scores/version/extension/";
			map = new HashMap<>();
			
			map.put("request_id" ,"47");
			map.put("grant_till", "2019-10-28");
			map.put("is_granted", "false");
			
			CommonMethod.res = MethodCall.POSTRequest(url,map);
			Assertion.verifyStatusCode(CommonMethod.res, 404);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}



}


