package com.arcapi.Common;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifyAgreementPresentAPITest extends BaseClass {

	@Test(groups="CheckAgreement")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyAgreementPresentAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
        
		try {
			url= "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/documents/";
			CommonMethod.res = MethodCall.GETRequest(url);	
			CommonMethod.fetchedID = CommonMethod.res.path("EtFile.ObjectId").toString();
			String agreementList = "[DOCHOLDERL-"+data.getCellData(SheetName, ProjectTypeColumn, rownumber)+"]";
			Assert.assertEquals(agreementList, CommonMethod.fetchedID);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	


}