package com.arcapi.Buildingtestcases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class RecomputePerformanceScoreBeforeTest extends BaseClass {

	@Test(groups="CheckRecompute")
	@Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void RecomputePerformanceScoreGet(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber) {

		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/scores/recompute/";
		CommonMethod.res = MethodCall.GETRequest(url);	
		CommonMethod.fetchedID = CommonMethod.res.path("scores.energy").toString();
		Assert.assertNotNull(CommonMethod.fetchedID);
		Assertion.verifyStatusCode(CommonMethod.res, 200);
	
	}

	

}