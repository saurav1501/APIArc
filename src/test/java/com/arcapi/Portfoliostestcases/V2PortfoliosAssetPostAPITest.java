package com.arcapi.Portfoliostestcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class V2PortfoliosAssetPostAPITest extends BaseClass {

	@Test(groups="AddPortfolio")
	public void PortfoliosAssetPostAPI() throws IOException {
	
		JSONObject jsonAsMap = new JSONObject();
		
		String projectID1 = data.getCellData(sheetName, "ProjectIDBuildingNone", rowNumTwo);
		
		jsonAsMap.put("asset_partner_id", projectID1);
		
		url = "/portfolios/ID:" +  data.getCellData(sheetName, "PortfolioID", rowNumTwo) + "/assets/";

		CommonMethod.res =MethodCall.POSTRequest(url, jsonAsMap);
		Assertion.verifyStatusCode(CommonMethod.res, 200);
		CommonMethod.fetchedID = CommonMethod.res.path("id").toString();
		CommonMethod.fetchedID = CommonMethod.fetchedID.replaceAll("\\[", "").replaceAll("\\]", "");
		data.setCellData(sheetName, "PortfolioPK", rowNumTwo, CommonMethod.fetchedID);
	
	}
   
	
}