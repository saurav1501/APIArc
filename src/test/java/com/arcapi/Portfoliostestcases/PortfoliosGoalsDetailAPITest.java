package com.arcapi.Portfoliostestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class PortfoliosGoalsDetailAPITest extends BaseClass {

	@Test(groups="CheckPortflio")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void PortfoliosGoalsDetailAPI(String SheetName,String ProjectTypeColumn, int rownumber)  {

			
		try {
			url = "/portfolios/ID:" +  data.getCellData(SheetName, "PortfolioID", rownumber) + "/goals/";

			CommonMethod.res = MethodCall.GETRequest(url);
				
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}