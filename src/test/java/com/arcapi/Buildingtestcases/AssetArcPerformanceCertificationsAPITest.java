package com.arcapi.Buildingtestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetArcPerformanceCertificationsAPITest extends BaseClass {

	@Test(groups="CheckData")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetArcPerformanceCertificationsAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/certifications/?source=Performance Score Certification";
		CommonMethod.res = MethodCall.GETRequest(url);		
		Assertion.verifyStatusCode(CommonMethod.res, 200);		
	}


}