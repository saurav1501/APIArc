package com.arcapi.Portfoliostestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifyGresbAssetsDetailsGetAPITest extends BaseClass {
	@Test(groups="CheckGresb")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void GresbAssetsDetailsGetAPI(String SheetName,String ProjectTypeColumn, int rownumber) {
	
		try {
			url = "/portfolios/ID:" +  data.getCellData(SheetName, "PortfolioID", rowNumThree) + "/assets/";
			CommonMethod.res = MethodCall.GETRequest(url);	
			
			String TotalProject = CommonMethod.res.path("count").toString();
			
					
			String count = data.getCellData(SheetName, "PortfolioID", 4);
			Integer totalProject = Integer.parseInt(count);
			Integer expected = totalProject +2;
			String calcuatedExpected = expected.toString();
			Assertion.verifyData(TotalProject, calcuatedExpected);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
				e.printStackTrace();
		}

	
	}


}


