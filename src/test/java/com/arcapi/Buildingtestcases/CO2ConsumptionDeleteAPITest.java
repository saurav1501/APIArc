package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class CO2ConsumptionDeleteAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void CO2ConsumptionDeleteAPI(String SheetName, String ProjectTypeColumn, int rownumber){

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
					+ data.getCellData("DataInput", "CO2MeterID", rownumber) + "/consumption/ID:"
					+ data.getCellData("DataInput", "CO2PK", rownumber) + "/";

			CommonMethod.res = MethodCall.DELETERequest(url);

			Assertion.verifyStatusCode(	CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}	
			}
	

	
}