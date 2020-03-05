package com.arcapi.Portfoliostestcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

import net.minidev.json.JSONObject;

public class PortfoliosAssetPostAPITest extends BaseClass {

	@Test(priority=1)
	public void PortfoliosAssetPostAPI() {
	
		try {
			JSONObject jsonAsMap = new JSONObject();
			
			String projectID1 = data.getCellData(sheetName, "ProjectIDBuildingNone", rowNumTwo);
			
			jsonAsMap.put("asset_partner_id", projectID1);
			
			url = "/portfolios/ID:" +  data.getCellData(sheetName, "PortfolioID", rowNumTwo) + "/assets/";

			CommonMethod.res =MethodCall.POSTRequest(url, jsonAsMap);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			CommonMethod.fetchedID = CommonMethod.res.path("id").toString();
			CommonMethod.fetchedID = CommonMethod.fetchedID.replaceAll("\\[", "").replaceAll("\\]", "");
			data.setCellData(sheetName, "PortfolioPK", rowNumTwo, CommonMethod.fetchedID);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
	}
   
	@Test(priority=2)
	public void CreatePortfoliosAsset2PostAPI()  {
			
		try {
			String projectID2 = data.getCellData(sheetName, "ProjectIDBuildingNone", rowNumThree);
			System.out.println(projectID2);
			JSONObject jsonAsMap = new JSONObject();
			jsonAsMap.put("asset_partner_id", projectID2);
			
			url = "/portfolios/ID:" +  data.getCellData(sheetName, "PortfolioID", rowNumTwo) + "/assets/";

			CommonMethod.res = MethodCall.POSTRequest(url,jsonAsMap);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		
	}
}