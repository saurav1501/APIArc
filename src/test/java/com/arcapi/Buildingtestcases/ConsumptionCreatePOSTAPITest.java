package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ConsumptionCreatePOSTAPITest extends BaseClass {

	@Test(groups="CheckConsumption")
    @Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void ConsumptionCreatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"+ data.getCellData(SheetName, "MeterID", rownumber) + "/consumption/?recompute_score=false";
			payload = MeterPayload.meterData2();
			CommonMethod.res = MethodCall.POSTRequest(url,payload);
			CommonMethod.fetchedID = CommonMethod.res.path("id").toString();
			data.setCellData(SheetName, "PK", rownumber, CommonMethod.fetchedID);
			Assertion.verifyStatusCode(CommonMethod.res , 201);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}