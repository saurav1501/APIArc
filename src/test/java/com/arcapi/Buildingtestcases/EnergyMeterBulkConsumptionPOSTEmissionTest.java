package com.arcapi.Buildingtestcases;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class EnergyMeterBulkConsumptionPOSTEmissionTest extends BaseClass {

	@Test(groups="CheckMeter")
    @Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void MeterBulkConsumptionPOSTAPI(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber) throws IOException {

	
		try {
			JSONObject jsonAsMap1 = new JSONObject();
			jsonAsMap1.put("start_date", "2019-01-01");
			jsonAsMap1.put("end_date", "2019-01-31");
			jsonAsMap1.put("reading", "120");
			
			JSONObject jsonAsMap2 = new JSONObject();
			jsonAsMap2.put("start_date", "2019-02-01");
			jsonAsMap2.put("end_date", "2019-02-28");
			jsonAsMap2.put("reading", "150");
			
			JSONObject jsonAsMap3 = new JSONObject();
			jsonAsMap3.put("start_date", "2019-03-01");
			jsonAsMap3.put("end_date", "2019-03-31");
			jsonAsMap3.put("reading", "200.005");
			
			ArrayList<JSONObject> list = new ArrayList<JSONObject>();
			list.add(jsonAsMap1);
			list.add(jsonAsMap2);
			list.add(jsonAsMap3);

			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
					+ data.getCellData(CustomSheetName, "MeterID", rownumber) + "/bulkconsumption/";
			
			CommonMethod.res = MethodCall.POSTRequest(url,list);

			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}