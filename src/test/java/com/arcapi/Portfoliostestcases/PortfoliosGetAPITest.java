package com.arcapi.Portfoliostestcases;

import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class PortfoliosGetAPITest extends BaseClass {

	@Test(groups="CheckPortfolio")
	public void PortfoliosGetAPI()  {

		try {
			url = "/portfolios/";
			CommonMethod.res = MethodCall.POSTRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}

}