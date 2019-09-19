package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

public class VOCMeterCreateAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VOCMeterCreateAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

				
		try {
			payload = MeterPayload.CreateTVOCMeter();
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/";
			CommonMethod.res =MethodCall.POSTRequest(url, payload);
			Assertion.verifyStatusCode(CommonMethod.res, 201);
			CommonMethod.fetchedID = CommonMethod.res.path("id").toString();
			data.setCellData("DataInput", "VOCMeterID", rownumber, CommonMethod.fetchedID);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	
	}
	
}