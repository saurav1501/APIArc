package com.arcapi.Portfoliostestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class PortfoliosAssetDetailGetAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","rownumber" })
	public void PortfoliosAssetDetailGetAPI(String SheetName, int rownumber) {

		try {
			url = "/portfolios/ID:" + data.getCellData(SheetName, "PortfolioID", rownumber)
					+ "/assets/" + data.getCellData(SheetName, "PortfolioPK", rownumber) + "/";

			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}

}