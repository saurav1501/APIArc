package com.arcapi.Common.CityCom;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class MeasuresDetailAPITest extends BaseClass {

	@Test(groups="CheckMeasures")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void MeasuresDetailAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

		url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/measures/" + data.getCellData(SheetName, "MeasuresID", rownumber)+"/";
		CommonMethod.res = MethodCall.GETRequest(url);
		Assertion.verifyStatusCode(CommonMethod.res, 200);
		
	}
	


}