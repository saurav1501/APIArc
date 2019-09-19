package com.arcapi.Portfoliostestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class PortfoliosGoalsUpdateAPITest extends BaseClass {

	@Test(groups="CheckPortfolio")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void PortfoliosGoalsUpdateAPI(String SheetName,String ProjectTypeColumn, int rownumber)  {

		try {
			JSONObject jsonAsMap = new JSONObject();
			jsonAsMap.put("asset_partner_id", data.getCellData(SheetName, ProjectTypeColumn, rownumber));
			jsonAsMap.put("category", "carbon");
			
			url = "/portfolios/ID:" +  data.getCellData(SheetName, "PortfolioID", rownumber) + "/goals/";

			CommonMethod.res = MethodCall.PUTRequest(url, jsonAsMap);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
	}

}