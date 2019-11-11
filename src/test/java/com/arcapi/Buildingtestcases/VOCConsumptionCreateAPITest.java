package com.arcapi.Buildingtestcases;

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
			payload = MeterPayload.meterData3();
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
					+ data.getCellData("DataInput", "ExcelTemplateMeterID", 4) + "/consumption/";

			CommonMethod.res = MethodCall.POSTRequest(url, payload);	
			Assertion.verifyStatusCode(	CommonMethod.res, 201);
			CommonMethod.fetchedID = CommonMethod.res.path("id").toString();

			data.setCellData("DataInput", "VOCPK", rownumber, CommonMethod.fetchedID);

			} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}
	


}