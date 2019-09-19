package com.arcapi.Scoretestcases.version2;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class TemperatureDataPostTest extends BaseClass {

	@Test(groups="CheckTempratue")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void TemperatureDataPost(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

	
		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("reading", "40000");
		jsonAsMap.put("start_date", "2017-01-01");
		jsonAsMap.put("end_date", "2018-12-31");

		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/temperature/";

		CommonMethod.res = MethodCall.POSTRequest(url,jsonAsMap);
		
		Assertion.verifyStatusCode(CommonMethod.res,200);

	}

	
}