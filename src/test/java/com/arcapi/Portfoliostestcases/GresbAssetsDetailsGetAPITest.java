package com.arcapi.Portfoliostestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class GresbAssetsDetailsGetAPITest extends BaseClass {
	@Test(groups="CheckGresb")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void GresbAssetsDetailsGetAPI(String SheetName,String ProjectTypeColumn, int rownumber) {
	
		try {
			url = "/portfolios/ID:" +  data.getCellData(SheetName, "PortfolioID", rowNumThree) + "/assets/";
			CommonMethod.res = MethodCall.GETRequest(url);	
			
			CommonMethod.fetchedID = CommonMethod.res.path("count").toString();
			System.out.println(CommonMethod.fetchedID);
			data.setCellData(SheetName, "PortfolioID", 4, CommonMethod.fetchedID);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
				e.printStackTrace();
		}

	
	}


}


