package com.arcapi.Portfoliostestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class PortfoliosTeamsGetAPITest extends BaseClass {

	@Test(groups="CheckPortfolio")
	@Parameters({ "SheetName","rownumber" })
	public void PortfoliosTeamsGetAPI(String SheetName, int rownumber) throws IOException {

		url = "/portfolios/ID:" + data.getCellData(SheetName, "PortfolioID", rownumber) + "/teams/";

		CommonMethod.res = MethodCall.GETRequest(url);
		Assertion.verifyStatusCode(CommonMethod.res , 200);

			}


}