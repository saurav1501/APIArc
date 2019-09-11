package com.arcapi.release1_18testcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetDetailQueryParamTrackerDateAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetDetailQueryParamAPI(String SheetName,String ProjectTypeColumn, int rownumber)  {

			
			
		try {
			String val[] = {"2010-01-01","2009-01-01"};
			
			for(String Date : val) {

			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/?date="+Date;

			CommonMethod.res = MethodCall.GETRequest(url);
			
			Assertion.verifyStatusCode(	CommonMethod.res, 200);

			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	

}