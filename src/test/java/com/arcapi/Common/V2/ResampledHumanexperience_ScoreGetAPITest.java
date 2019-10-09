package com.arcapi.Common.V2;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ResampledHumanexperience_ScoreGetAPITest extends BaseClass {

	@Test(groups="CheckAnalysis")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void ResampledHumanexperience_ScoreGetAPI(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			url = "/assets/LEED:"+data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/resampled/humanexperience_score/";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	

}