package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class MeterCreatePOSTEmissionTest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void MeterCreatePOSTAPI(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber)  {

	
		try {
			JSONObject jsonAsMap = new JSONObject();
			jsonAsMap.put("name", "Energy_Meter");
			jsonAsMap.put("native_unit", "kBtu");
			jsonAsMap.put("type", "29");
			
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/";
			CommonMethod.res = MethodCall.POSTRequest(url, jsonAsMap);
			CommonMethod.fetchedID = CommonMethod.res.path("id").toString();
			data.setCellData(CustomSheetName, "MeterID", rownumber, CommonMethod.fetchedID);
			Assertion.verifyStatusCode(CommonMethod.res, 201);
		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}


}