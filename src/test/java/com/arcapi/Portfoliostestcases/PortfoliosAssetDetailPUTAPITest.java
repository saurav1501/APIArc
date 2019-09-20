package com.arcapi.Portfoliostestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class PortfoliosAssetDetailPUTAPITest extends BaseClass {

	@Test(groups="CheckPortfolio")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void PortfoliosAssetDetailPUTAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		JSONObject jsonAsMap = new JSONObject();		
		jsonAsMap.put("asset_partner_id", data.getCellData(sheetName, ProjectTypeColumn, rowNumTwo));

		url = "/portfolios/ID:" + data.getCellData(SheetName, "PortfolioID", rownumber)
		+ "/assets/" + data.getCellData(SheetName, "PortfolioPK", rownumber) + "/";

		CommonMethod.res = MethodCall.PUTRequest(url,jsonAsMap);
		Assertion.verifyStatusCode(CommonMethod.res, 200);

	
	}


}