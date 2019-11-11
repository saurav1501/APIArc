package com.arcapi.ScenarioBasedTestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

public class VOCConsumptionCreateAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VOCConsumptionCreateAPI(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			payload = MeterPayload.meterData5();
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
					+ data.getCellData("DataInput", "ExcelTemplateMeterID", 5) + "/consumption/";

			CommonMethod.res = MethodCall.POSTRequest(url, payload);	
			Assertion.verifyStatusCode(	CommonMethod.res, 201);
		
			} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}
	


}