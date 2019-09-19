package com.arcapi.Scoretestcases.version3;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class CityHighSchoolGetTest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void CityHighSchoolGet(String SheetName,String ProjectTypeColumn, int rownumber) {

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
			+ "/high_school/mean/";
			CommonMethod.res = MethodCall.GETRequest(url);
			
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}}