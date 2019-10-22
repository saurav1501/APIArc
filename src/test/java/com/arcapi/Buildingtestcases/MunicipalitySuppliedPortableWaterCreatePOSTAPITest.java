package com.arcapi.Buildingtestcases;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

public class MunicipalitySuppliedPortableWaterCreatePOSTAPITest extends BaseClass{
	
		@Test(groups="CheckMeter")
		@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
		public void municipalitySuppliedPortableWaterCreatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

			try {
				url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/";

				payload= MeterPayload.MunicipalitySuppliedPortableWater();

				CommonMethod.res = MethodCall.POSTRequest(url, payload);

				CommonMethod.fetchedID = CommonMethod.res.path("id").toString();
				
				data.setCellData(sheetName, "WaterMeterID", rowNumTwo, CommonMethod.fetchedID);

				Assertion.verifyStatusCode(CommonMethod.res, 201);
			} catch (JsonProcessingException e) {
				
				e.printStackTrace();
			}
		}

	

	}


