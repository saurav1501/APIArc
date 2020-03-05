package com.arcapi.Portfoliostestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.RandomData;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

import net.minidev.json.JSONObject;

public class PortfoliosDetailPUTAPITest extends BaseClass {

	@Test
	@Parameters({"SheetName","rownumber" })
	public void PortfoliosDetailPUTAPI(String SheetName, int rownumber) {

		try {
			JSONObject jsonAsMap = new JSONObject();
			jsonAsMap.put("name", RandomData.protfolioName());
			jsonAsMap.put("organization", "USGBCARC");
			jsonAsMap.put("organization_contact", "1234567890");
			jsonAsMap.put("organization_country", "US");
			url = "/portfolios/ID:" + data.getCellData(SheetName, "PortfolioID", rownumber) + "/";

			CommonMethod.res = MethodCall.POSTRequest(url, jsonAsMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}

}