package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class ConsumptionUpdateEmissionAPITest extends BaseClass {

	@Test(groups="CheckEmession")
	@Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void ConsumptionUpdateEmissionAPI(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber) {

			
		try {
			String reading = data.getCellData(CustomSheetName, "IncreasedEmissionFactor", 3);

			JSONObject jsonAsMap = new JSONObject();
			jsonAsMap.put("start_date", "2019-01-01");
			jsonAsMap.put("reading", reading);

			url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
					+ data.getCellData(CustomSheetName, "EmissionMeterID", rownumber) + "/consumption/ID:"
					+ data.getCellData(CustomSheetName, "EmissionMeterPK", rownumber) + "/";

			CommonMethod.res = MethodCall.PUTRequest(url, jsonAsMap);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	

	}

	
}