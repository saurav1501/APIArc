package com.arcapi.Scoretestcases.version3;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class TransitGetTest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void TransitGet(String SheetName,String ProjectTypeColumn, int rownumber)  {

		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
		+ "/transit/mean/";
		
		
		CommonMethod.res = MethodCall.GETRequest(url);
		Assertion.verifyStatusCode(CommonMethod.res, 200);
		
	
}}