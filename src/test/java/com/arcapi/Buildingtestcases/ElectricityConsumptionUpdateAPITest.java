package com.arcapi.Buildingtestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ElectricityConsumptionUpdateAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void ElectricityConsumptionUpdateAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

			
        int RowNum = 11;
    	for (int i =2; i<= RowNum;i++) {

    	payload = MeterPayload.meterData2();	
    	url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
				+ data.getCellData("DataInput", "ElectricityMeterID", i) + "/consumption/ID:"
				+ data.getCellData("DataInput", "ElectricityPK", i) + "/";


		CommonMethod.res = MethodCall.PUTRequest(url, payload);
		
		Assertion.verifyStatusCode(CommonMethod.res, 200);
	}
	}



}