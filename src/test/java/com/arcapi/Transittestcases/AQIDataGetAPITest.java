package com.arcapi.Transittestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AQIDataGetAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void AQIDataGetAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/aqi/?resample=2015-01-05/P1D,2015-01-06/P1D,2015-01-07/P1D,2015-01-08/P1D";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(	CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}