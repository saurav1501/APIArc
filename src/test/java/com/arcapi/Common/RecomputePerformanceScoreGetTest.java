package com.arcapi.Common;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class RecomputePerformanceScoreGetTest extends BaseClass {

	@Test(groups="CheckRecompute")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void RecomputePerformanceScoreGet(String SheetName,String ProjectTypeColumn, int rownumber){

	    try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/scores/recompute/";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}


}