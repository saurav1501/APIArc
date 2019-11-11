package com.arcapi.Buildingtestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class CO2ConsumptionUpdateAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void CO2ConsumptionUpdateAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		try {
			payload = MeterPayload.meterData2();
			url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
					+ data.getCellData("DataInput", "ExcelTemplateMeterID", 4) + "/consumption/";

			CommonMethod.res = MethodCall.POSTRequest(url, payload);
			
			Assertion.verifyStatusCode(CommonMethod.res, 201);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}