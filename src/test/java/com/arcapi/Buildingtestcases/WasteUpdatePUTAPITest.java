package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class WasteUpdatePUTAPITest extends BaseClass {

	@Test(groups="CheckWaste")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void WasteUpdatePUTAPI(String SheetName,String ProjectTypeColumn, int rownumber){

	  try {
		  payload = MeterPayload.wasteMeter2();
		  url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/waste/ID:"
					+ data.getCellData(SheetName, "WasteID", rownumber) + "/";
		  CommonMethod.res = MethodCall.PUTRequest(url, payload);
		  Assertion.verifyStatusCode(CommonMethod.res, 200);
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		

	}

	
}