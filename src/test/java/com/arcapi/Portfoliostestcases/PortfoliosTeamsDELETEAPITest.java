package com.arcapi.Portfoliostestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class PortfoliosTeamsDELETEAPITest extends BaseClass {

	@Test(groups="CheckPortfolio")
	@Parameters({ "SheetName","rownumber" })
	public void PortfoliosTeamsDELETEAPI(String SheetName, int rownumber) throws IOException {

		
		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("username", "test-02@gmail.com");
		
		url = "/portfolios/ID:" + data.getCellData(SheetName, "PortfolioID", rownumber) + "/teams/";
		CommonMethod.res = MethodCall.DELETERequest(url);
		Assertion.verifyStatusCode(CommonMethod.res , 200);

	
	}

	

}