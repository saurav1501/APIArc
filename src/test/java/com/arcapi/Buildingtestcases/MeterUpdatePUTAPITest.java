package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class MeterUpdatePUTAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void MeterUpdatePUTAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

		try {
			url= "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:" + data.getCellData(SheetName, "MeterID", rownumber) + "/";
			payload = MeterPayload.meterUpdate();
			CommonMethod.res =MethodCall.PUTRequest(url, payload);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
}