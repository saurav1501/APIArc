package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class WaterConsumptionUpdateAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void WaterConsumptionUpdateAPI(String SheetName,String ProjectTypeColumn, int rownumber){

			
        try {
			int RowNum = 45;
			for (int i =2; i<= RowNum;i++) {

			payload = MeterPayload.meterData2();

			url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
							+ data.getCellData("DataInput", "WaterMeterID", i) + "/consumption/ID:"
							+ data.getCellData("DataInput", "WaterPK", i) + "/";
			CommonMethod.res = MethodCall.PUTRequest(url, payload);
			Assertion.verifyStatusCode(CommonMethod.res, 200);

}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}