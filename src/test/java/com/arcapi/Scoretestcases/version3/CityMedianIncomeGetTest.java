package com.arcapi.Scoretestcases.version3;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class CityMedianIncomeGetTest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void CityMedianIncomeGet(String SheetName,String ProjectTypeColumn, int rownumber)  {

	try {
		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
		+ "/median_income/mean/";
			CommonMethod.res = MethodCall.GETRequest(url);
			
		Assertion.verifyStatusCode(CommonMethod.res, 200);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}}