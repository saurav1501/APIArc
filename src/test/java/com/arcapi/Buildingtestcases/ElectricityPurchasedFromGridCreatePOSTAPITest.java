package com.arcapi.Buildingtestcases;
import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;



public class ElectricityPurchasedFromGridCreatePOSTAPITest extends BaseClass{
	
		@Test(groups="CheckMeter")
		@Parameters({"SheetName","ProjectTypeColumn","rownumber"})
		public void ElectricityPurchasedFromGridCreatePOST(String SheetName,String ProjectTypeColumn, int rownumber)  {
			try {
				url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/";

				payload = MeterPayload.meterData();

				CommonMethod.res = MethodCall.POSTRequest(url, payload);
				
				CommonMethod.fetchedID = CommonMethod.res.path("id").toString();

				data.setCellData(sheetName, "MeterID", rowNumTwo, CommonMethod.fetchedID);

				Assertion.verifyStatusCode(CommonMethod.res, 201);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}

		

	}


