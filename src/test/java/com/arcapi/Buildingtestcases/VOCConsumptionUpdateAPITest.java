package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VOCConsumptionUpdateAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VOCConsumptionUpdateAPI(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			payload = MeterPayload.meterData2();
			
			url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
					+ data.getCellData("DataInput", "VOCMeterID", rownumber) + "/consumption/ID:"
					+ data.getCellData("DataInput", "VOCPK", rownumber) + "/";

			CommonMethod.res = MethodCall.PUTRequest(url, payload);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	



}