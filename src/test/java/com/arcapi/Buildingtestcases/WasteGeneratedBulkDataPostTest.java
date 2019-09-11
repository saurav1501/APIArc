package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class WasteGeneratedBulkDataPostTest extends BaseClass {

	@Test(groups="Waste")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void WasteGeneratedBulkDataPost(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/waste/generated/";
			listpayload = MeterPayload.wasteMeter();
			CommonMethod.res = MethodCall.POSTRequest(url,listpayload);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
		
			e.printStackTrace();
		}

	}


}