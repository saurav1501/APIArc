package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

public class SurveyAllRoutesFeedbackVisitorAPIPOSTAPITest extends BaseClass{
	
	@Test(groups="CheckSurvey")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void SurveyAllRoutesFeedbackVisitorAPI(String SheetName,String ProjectTypeColumn, int rownumber){
	try {
		url = "/assets/LEED:" + data.getCellData(sheetName, ProjectTypeColumn, rownumber) +"/survey/?key="+ data.getCellData(sheetName, "BuildingKeyID", rownumber);
		payload = MeterPayload.submitSurveyExtremelyUnsatisfied();	
		CommonMethod.res = MethodCall.POSTRequest(url, payload);
		Assertion.verifyStatusCode(CommonMethod.res, 201);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
}}
