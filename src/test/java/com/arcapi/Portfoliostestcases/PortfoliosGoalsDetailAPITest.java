package com.arcapi.Portfoliostestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class PortfoliosGoalsDetailAPITest extends BaseClass {

	@Test(groups="CheckPortflio")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void PortfoliosGoalsDetailAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		JSONObject jsonAsMap = new JSONObject();
		
		jsonAsMap.put("asset_partner_id", data.getCellData(SheetName, ProjectTypeColumn, rownumber));
		
		url = "/portfolios/ID:" +  data.getCellData(SheetName, "PortfolioID", rownumber) + "/goals/";

		CommonMethod.res = MethodCall.POSTRequest(url, jsonAsMap);
			
		Assertion.verifyStatusCode(CommonMethod.res, 200);


	}

}