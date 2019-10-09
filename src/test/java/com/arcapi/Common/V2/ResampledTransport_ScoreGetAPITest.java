package com.arcapi.Common.V2;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ResampledTransport_ScoreGetAPITest extends BaseClass {

	@Test(groups="CheckAnalysis")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void Waste_ScoreBasicAnalysisDataGetAPI(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			url = "/assets/LEED:"+data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/resampled/transport_score/";

			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}