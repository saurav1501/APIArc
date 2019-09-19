package com.arcapi.Scoretestcases.version3;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class CityVehiclesMilesGetTest extends BaseClass {

	@Test(groups="CheckMeterCity")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void CityVehiclesMilesGet(String SheetName,String ProjectTypeColumn, int rownumber)  {

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
			+ "/vehicles_miles/mean/";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res , 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}}