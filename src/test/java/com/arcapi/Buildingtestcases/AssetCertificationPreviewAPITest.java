package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetCertificationPreviewAPITest extends BaseClass {

	@Test(groups="CheckData")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetCertificationPreviewAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

		try {
			
		
			
			url = "/assets/LEED:" +  data.getCellData(SheetName, ProjectTypeColumn, rownumber) +"/certifications/preview/";
			CommonMethod.res = MethodCall.POSTRequestHTMLFileUpload(url);
			Assertion.verifyStatusCode(	CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}