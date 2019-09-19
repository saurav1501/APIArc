package com.arcapi.CrossRatingSystemtestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class GhgGetTest extends BaseClass {

	@Test(groups="CheckGHG")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void GhgGetTestAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

	try {
		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
		+ "/ghg/?resample=2017-01-05/P1D,2017-01-06/P1D,2017-01-07/P1D,2017-01-08/P1D";
		
			CommonMethod.res = MethodCall.GETRequest(url);
			
		Assertion.verifyStatusCode(	CommonMethod.res, 404);
	} catch (Exception e) {
		e.printStackTrace();
	}
}}