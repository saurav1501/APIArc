package com.arcapi.Common;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ScoreVersionNotificationPOSTAPITest extends BaseClass{
	
	@Test(groups="CheckAsset")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void ScoreVersionNotificationPOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		try {
			url="/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/scores/version/notification/";
			map = new HashMap<>();
			map.put("notify_on" ,"2020-05-28");
			
			CommonMethod.res = MethodCall.POSTRequest(url,map);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}



}


