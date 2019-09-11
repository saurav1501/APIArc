package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class WasteDivertedBulkDataPostTest extends BaseClass {

	@Test(groups="Waste")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void WasteDivertedBulkDataPost(String SheetName,String ProjectTypeColumn, int rownumber) {

		
		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/waste/diverted/";
			listpayload= MeterPayload.wasteMeter();
			CommonMethod.res = MethodCall.POSTRequest(url,listpayload);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
}