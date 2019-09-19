package com.arcapi.Buildingtestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class OtherFuelMeterCreateAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void OtherFuelMeterCreateAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		
		int row = 2;
		
		/*
		 * District Steam - 40
		 * District Hot Water -41
		 * District Chilled Water - 42,43,44
		 * Natural Gas - 1
		 * Fuel Oil - 2,7,8
		 * Wood - 3
		 * Propane - 4
		 * Liquid Propane - 5
		 * Kerosene - 6 
		 * Coal - 9,10
		 * Coke - 11
		 * Diesel - 13
		 */
		
		String[] Unit = {"gal","kGal", "MGal","cf","ccf","kcf","mcf","l","cu m","GJ","MBtu", "kBtu","therms","MWh","kWh"};
		String[] Type = {"40","41","42","43","44","1","2","7","8","3","4","5","6","9","10","11","13"};
		
		for (int i=0;i < Unit.length;i++) {
			for(int j=0;j<Type.length;j++) {

		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("name", "Test Other Fuel Meter");
		jsonAsMap.put("native_unit", Unit[i]);
		jsonAsMap.put("type", Type[j]);

		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/";

		CommonMethod.res = MethodCall.POSTRequest(url,jsonAsMap);

		CommonMethod.fetchedID = CommonMethod.res.path("id").toString();

		data.setCellData("DataInput", "OtherFuelMeterID", row, CommonMethod.fetchedID);

		
		Assertion.verifyStatusCode(CommonMethod.res, 201);
		
		row++;
			}
	}
	}
	}