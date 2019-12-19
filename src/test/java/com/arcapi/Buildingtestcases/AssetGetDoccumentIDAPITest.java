package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetGetDoccumentIDAPITest extends BaseClass {

	@Test(groups ="CheckData")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void AssetGetDoccumentIDAPI(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
				
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/certifications/?source=Performance Score Certification";
			
			CommonMethod.res = MethodCall.GETRequest(url);
			
			Integer id =  CommonMethod.res.jsonPath().get("certificates[0].certificate");;
			System.out.println(id);
			String docid = String.valueOf(id);
			
			
			//?access-token="+CommonMethod.Token+"&subscription-key="+CommonMethod.SubscriptionKey"
				
			data.setCellData("LEEDONLINE", "LeedIDINT", 4, docid);
			
			
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			
		} catch (Exception e) {
		e.printStackTrace();
		}
	}



}