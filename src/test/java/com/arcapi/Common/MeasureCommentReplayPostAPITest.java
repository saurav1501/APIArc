package com.arcapi.Common;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class MeasureCommentReplayPostAPITest extends BaseClass{
	
	@Test(groups="CheckComment")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void MeasureCommentReplayPostAPI(String SheetName,String ProjectTypeColumn, int rownumber)  {

		try {
			url="/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/waste/comment/";
			map = new HashMap<>();
			map.put("data","hey man");
			map.put("confidential",true);
			CommonMethod.res = MethodCall.POSTRequest(url,map);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}



}


