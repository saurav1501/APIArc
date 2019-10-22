package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class WasteCreatePOSTAPITest extends BaseClass {

	@Test(groups="CheckWaste")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void WasteCreatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber){
		
		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/waste/";
			payload = MeterPayload.wasteMeter4();
			CommonMethod.res = MethodCall.POSTRequest(url, payload);
			CommonMethod.fetchedID = CommonMethod.res.path("id").toString();
			data.setCellData(SheetName, "WasteID", rownumber, CommonMethod.fetchedID);
			Assertion.verifyStatusCode(CommonMethod.res, 201);
		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}

}