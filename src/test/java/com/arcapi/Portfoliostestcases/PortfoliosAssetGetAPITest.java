package com.arcapi.Portfoliostestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class PortfoliosAssetGetAPITest extends BaseClass {

	@Test(groups="CheckPortfolio")
	@Parameters({ "SheetName","rownumber" })
	public void PortfoliosAssetGetAPI(String SheetName, int rownumber)  {

		try {
			url = "/portfolios/ID:" + data.getCellData(SheetName, "PortfolioID", rownumber) + "/assets/";

			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(	CommonMethod.res , 200);
		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}


}