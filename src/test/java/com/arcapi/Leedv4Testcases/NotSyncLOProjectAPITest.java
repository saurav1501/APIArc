package com.arcapi.Leedv4Testcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Controller.TestUtils;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import junit.framework.Assert;

public class NotSyncLOProjectAPITest extends BaseClass {

	@Test(groups="CheckSync")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void LOProjectSyncAPI(String SheetName,String ProjectTypeColumn, int rownumber){
		try {
			url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/syncproject/";
			CommonMethod.res = MethodCall.GETRequest(url);
			response= TestUtils.getResposeString(CommonMethod.res);
			Assert.assertTrue(response.contains("message"));
			Assert.assertTrue(response.contains("Project by-passed from syncing against rating system."));
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	

	}

}