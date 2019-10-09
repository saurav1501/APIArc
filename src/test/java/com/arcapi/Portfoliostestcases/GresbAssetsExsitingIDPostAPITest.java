package com.arcapi.Portfoliostestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class GresbAssetsExsitingIDPostAPITest extends BaseClass {
	@Test(groups="CheckGresb")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void GresbAssetsExsitingIDPostAPI(String SheetName,String ProjectTypeColumn, int rownumber) {
	
		try {
			url = "/portfolios/ID:" +  data.getCellData(SheetName, "PortfolioID", rowNumThree) + "/gresb/excel/";
			CommonMethod.res = MethodCall.POSTUploadFile(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
				e.printStackTrace();
		}

	
	}


}


