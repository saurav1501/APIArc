 package com.arcapi.Common;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.OtherDetails;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class S3DocumentRemoveAPITest extends BaseClass {

	@Test(groups="CheckRemoveS3Document")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void S3DocumentRemoveAPI(String SheetName,String ProjectTypeColumn, int rownumber){
		
		try {
			url= "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/s3documents/";
			payload = OtherDetails.uploadS3();
			CommonMethod.res = MethodCall.PUTRequest(url, payload);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}