package com.arcapi.CrossRatingSystemtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VOCGetTest extends BaseClass {

	@Test(groups="CheckVoc")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VOCGet(String SheetName,String ProjectTypeColumn, int rownumber)  {
		
		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
			+ "/voc/?resample=2017-01-05/P1D,2017-01-06/P1D,2017-01-07/P1D,2017-01-08/P1D";
			
			CommonMethod.res= MethodCall.GETRequest(url);
			
			Assertion.verifyStatusCode(CommonMethod.res, 403);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}}