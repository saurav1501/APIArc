package com.arcapi.Portfoliostestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

import net.minidev.json.JSONObject;

public class PortfoliosTeamsPostAPITest extends BaseClass {

	@Test(groups="CheckPortfolio")
	@Parameters({ "SheetName","rownumber" })
	public void PortfoliosTeamsPostAPI(String SheetName, int rownumber){

		
		try {
			JSONObject jsonAsMap = new JSONObject();
			jsonAsMap.put("username", "test-02@gmail.com");
			jsonAsMap.put("permission", "can_edit");

			url = "/portfolios/ID:" + data.getCellData(SheetName, "PortfolioID", rownumber) + "/teams/";
			CommonMethod.res = MethodCall.POSTRequest(url, jsonAsMap);

			Assertion.verifyStatusCode(CommonMethod.res, 201);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

	
}