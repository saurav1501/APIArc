package com.arcapi.release1_18testcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class AssetUpdateExcludeSpacesTest extends BaseClass {

	@Test(groups="CheckExcludeSpaces")
    @Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetUpdateExcludeSpaces(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		
		JSONObject jsonAsMap = new JSONObject();
		
		jsonAsMap.put("operating_hours", "30");
		jsonAsMap.put("year_constructed", "2018");
		jsonAsMap.put("exclude_spaces", "true");
		jsonAsMap.put("survey_score_occupant", "total_occupant");
	
		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/";

		CommonMethod.res = MethodCall.PUTRequest(url, jsonAsMap);
		Assertion.verifyStatusCode(CommonMethod.res, 200);
		
	}}