package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
					+ data.getCellData("DataInput", "VOCMeterID", rownumber) + "/consumption/";

			CommonMethod.res = MethodCall.POSTRequest(url, payload);	
			CommonMethod.res.then().assertThat().statusCode(201);

			CommonMethod.fetchedID = CommonMethod.res.path("id").toString();

			data.setCellData("DataInput", "VOCPK", rownumber, CommonMethod.fetchedID);

			CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}
	


}