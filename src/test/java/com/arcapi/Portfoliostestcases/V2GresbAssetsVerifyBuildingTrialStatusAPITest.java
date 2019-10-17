package com.arcapi.Portfoliostestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class V2GresbAssetsVerifyBuildingTrialStatusAPITest extends BaseClass {
	@Test(groups="CheckGresb")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void V2GresbAssetsVerifyBuildingTrialStatusAPI(String SheetName,String ProjectTypeColumn, int rownumber) {
	
		try {
			url = "/portfolios/ID:" +  data.getCellData(SheetName, "PortfolioID", rowNumThree) + "/assets/";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			
			int count  = CommonMethod.res.path("count");
			
			
			System.out.println(count);
			for (int i=0;i<count;i++) {
				
				CommonMethod.fetchedID  = CommonMethod.res.path("results["+i+"].building.building_status").toString();
				Assertion.verifyData(CommonMethod.fetchedID , "activated_addendum_agreement_pending");
				
				CommonMethod.fetchedID  = CommonMethod.res.path("results["+i+"].building.is_trial_selected").toString();
				Assertion.verifyData(CommonMethod.fetchedID , "false");
				
	
			}
		
			
		} catch (Exception e) {
				e.printStackTrace();
		}

	
	}


}


