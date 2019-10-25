package com.arcapi.CrossRatingSystemtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class CO2GetTest extends BaseClass {

	@Test(groups="CheckCo2")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void CO2GetTestAPI(String SheetName,String ProjectTypeColumn, int rownumber)  {

	try {
		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
		+ "/co2/?resample=2017-01-05/P1D,2017-01-06/P1D,2017-01-07/P1D,2017-01-08/P1D";

		CommonMethod.res = MethodCall.GETRequest(url);
			
		Assertion.verifyStatusCode(CommonMethod.res, 403);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}}