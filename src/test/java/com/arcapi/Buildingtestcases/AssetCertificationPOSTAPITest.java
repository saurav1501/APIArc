package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.OtherDetails;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetCertificationPOSTAPITest extends BaseClass {

	@Test(groups= "CheckCertificationPoint")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetCertificationPOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

		try {
			url = "/assets/LEED:" +  data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/certifications/";
			payload = OtherDetails.addPoint();
			CommonMethod.res = MethodCall.POSTRequest(url,payload);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}


}