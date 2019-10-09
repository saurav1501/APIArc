package com.arcapi.Buildingtestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class ConsumptionCreatePOSTEmissionTest extends BaseClass {

	@Test(groups="CheckEmission")
    @Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void ConsumptionCreatePOSTEmission(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		
		try {
			String reading = data.getCellData(CustomSheetName, "IncreasedEmissionFactor", rownumber);
			
			String[] startdate = {"2019-01-01"};
			
			for(String str : startdate) {
			JSONObject jsonAsMap1 = new JSONObject();
			jsonAsMap1.put("start_date", str);
			jsonAsMap1.put("reading", reading);
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
					+ data.getCellData(CustomSheetName, "EmissionMeterID", rownumber) + "/consumption/";
			
			CommonMethod.res = MethodCall.POSTRequest(url, jsonAsMap1);
			Assertion.verifyStatusCode(	CommonMethod.res , 201);
			CommonMethod.fetchedID = CommonMethod.res.path("id").toString();
			data.setCellData(CustomSheetName, "EmissionMeterPK", rownumber, CommonMethod.fetchedID);


}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}