package com.arcapi.Portfoliostestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.RandomData;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class PortfoliosPostAPITest extends BaseClass {

	@Test(groups="CheckPortfolio")
	@Parameters({"SheetName","rownumber" })
	public void PortfoliosPostAPI(String SheetName, int rownumber) {
		try {
			
			JSONObject jsonAsMap = new JSONObject();
			jsonAsMap.put("name", RandomData.protfolioName());
			jsonAsMap.put("source", "Arc");
			jsonAsMap.put("description", "Test portfolio");
			jsonAsMap.put("address", "123-A/5, Washington, DC, US");
			jsonAsMap.put("organization", "Group10");
			jsonAsMap.put("organization_contact", "1234567890");
			jsonAsMap.put("organization_country", "US");
			jsonAsMap.put("organization_email", "usgbcarcapi1@gmail.com");
			
			url = "/portfolios/";
			CommonMethod.res = MethodCall.POSTRequest(url, jsonAsMap);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			CommonMethod.fetchedID = CommonMethod.res.path("pf_id").toString();
			CommonMethod.fetchedID = CommonMethod.fetchedID.replaceAll("\\[", "").replaceAll("\\]", "");
			data.setCellData(SheetName, "PortfolioID", rownumber, CommonMethod.fetchedID);
			CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}