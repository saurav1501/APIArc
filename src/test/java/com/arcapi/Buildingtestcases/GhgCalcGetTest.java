package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class GhgCalcGetTest extends BaseClass {

	@Test(groups="CheckGhgCalc")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void GhgCalcGet(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			url = "/assets/LEED:"+data.getCellData(SheetName, ProjectTypeColumn, rownumber)+"/ghgcalc/?resample=2015-01-05/P1D,2015-01-06/P1D,2015-01-07/P1D,2015-01-08/P1D";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}