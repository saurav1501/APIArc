package com.arcapi.CrossRatingSystemtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class TemperatureDataGetTest extends BaseClass {

	@Test(groups="CheckTemp")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void TemperatureDataGet(String SheetName,String ProjectTypeColumn, int rownumber) {

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/temperature/?resample=2015-01-05/P1D,2015-01-06/P1D,2015-01-07/P1D,2015-01-08/P1D";
			CommonMethod.res = MethodCall.GETRequest(url);
			
			Assertion.verifyStatusCode(	CommonMethod.res, 404);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}}