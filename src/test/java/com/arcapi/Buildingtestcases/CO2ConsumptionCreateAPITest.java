package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CO2ConsumptionCreateAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void CO2ConsumptionCreateAPI(String SheetName,String ProjectTypeColumn, int rownumber)  {

				
		try {
			payload = MeterPayload.meterData3();
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
					+ data.getCellData("DataInput", "CO2MeterID", rownumber) + "/consumption/";

			
			CommonMethod.res = MethodCall.POSTRequest(url, payload);
			
			CommonMethod.fetchedID = CommonMethod.res.path("id").toString();
			data.setCellData("DataInput","CO2PK", rownumber, CommonMethod.fetchedID);
			
			Assertion.verifyStatusCode(	CommonMethod.res, 201);
			} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

}