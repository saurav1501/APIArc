package com.arcapi.Citytestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.CityComPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class MeterCreateAPICityCommTest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void MeterCreatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber){
	
        int row = 2;
		url= "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/";
		String[] Type = {"265","266","267","268","269","270","271","272","273","274","275","276","277","278"};	
		for(String type : Type) {
		payload = CityComPayload.createMeter(type);
		CommonMethod.res = MethodCall.POSTRequest(url, payload);
		CommonMethod.fetchedID = CommonMethod.res.path("id").toString();
		data.setCellData("DataInput", "CityMeterID", row, CommonMethod.fetchedID);	
		Assertion.verifyStatusCode(CommonMethod.res, 201);	
		row++;
		}
	}
}

	
