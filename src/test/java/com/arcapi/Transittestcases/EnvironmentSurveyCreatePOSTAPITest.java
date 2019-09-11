package com.arcapi.Transittestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class EnvironmentSurveyCreatePOSTAPITest extends BaseClass {

	@Test(groups="CheckSurvey")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void EnvironmentSurveyCreatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			payload = MeterPayload.submitSurveyV2();		
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/survey/environment/?key="+ data.getCellData(SheetName, "BuildingKeyID", rownumber);
			CommonMethod.res = MethodCall.POSTRequest(url,payload);
			Assertion.verifyStatusCode(CommonMethod.res, 201);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}