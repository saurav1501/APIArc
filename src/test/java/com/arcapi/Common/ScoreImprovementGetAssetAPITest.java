package com.arcapi.Common;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ScoreImprovementGetAssetAPITest extends BaseClass {
	@Test(groups="ScoreImprovement")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void ScoreImprovementGetAssetAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		
			try {
				url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber)+"/scores/improvement/";		
				CommonMethod.res = MethodCall.GETRequest(url);
				Assertion.verifyStatusCode(CommonMethod.res, 200);
			} catch (Exception e) {
				e.printStackTrace();
			}

}}