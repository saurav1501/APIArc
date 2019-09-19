package com.arcapi.CrossRatingSystemtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class WaterGetTest extends BaseClass {

	@Test(groups="CheckWater")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void WaterGet(String SheetName,String ProjectTypeColumn, int rownumber)  {
		
		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
			+ "/water/?resample=2017-01-05/P1D,2017-01-06/P1D,2017-01-07/P1D,2017-01-08/P1D";

			
			CommonMethod.res = MethodCall.GETRequest(url);
			
			Assertion.verifyStatusCode(CommonMethod.res, 404);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}}