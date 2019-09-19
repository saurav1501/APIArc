package com.arcapi.Portfoliostestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class PortfoliosAssetDetailDELETEAPITest extends BaseClass {

	@Test(groups="CheckDeletePortfolio")
	@Parameters({ "SheetName","rownumber" })
	public void PortfoliosAssetDetailDELETEAPI(String SheetName, int rownumber) throws IOException {

		url = "/portfolios/ID:" + data.getCellData(SheetName, "PortfolioID", rownumber)
		+ "/assets/" + data.getCellData(SheetName, "PortfolioPK", rownumber) + "/";
		
		CommonMethod.res = MethodCall.DELETERequest(url);
		Assertion.verifyStatusCode(	CommonMethod.res, 200);

		
	}

	

}