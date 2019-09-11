package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class MeterBulkConsumptionPOSTAPITest extends BaseClass {

	@Test(groups="CheckMeter")
    @Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void MeterBulkConsumptionPOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

		try {
			url ="/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"+ data.getCellData(SheetName, "MeterID", rownumber) + "/bulkconsumption/";
			listpayload = MeterPayload.meterData1();
			CommonMethod.res = MethodCall.POSTRequest(url, listpayload);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



}