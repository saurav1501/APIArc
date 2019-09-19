package com.arcapi.Portfoliostestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

import net.minidev.json.JSONObject;

public class PortfoliosAnalyticsCategoryDetailAPITest extends BaseClass {

	@Test(groups="CheckPortfolio")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void PortfoliosAnalyticsCategoryDetailAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

		try {
			JSONObject jsonAsMap = new JSONObject();
			
			jsonAsMap.put("asset_partner_id", data.getCellData(SheetName, ProjectTypeColumn, rownumber));
			
			url = "/portfolios/ID:" +  data.getCellData(SheetName, "PortfolioID", rownumber) + "/analytics/energy/";
			CommonMethod.res = MethodCall.POSTRequest(url, jsonAsMap);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}

	
	}


}