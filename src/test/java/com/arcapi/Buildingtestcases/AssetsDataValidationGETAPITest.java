package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetsDataValidationGETAPITest extends BaseClass {
	@Test(groups="CheckData")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void AssetsDataValidationGETAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

		String data_Endpoint[] = {"energy", "water", "waste","transportation","humanexperience"};
		
		try {
			for(String data_endpoint:data_Endpoint) {
			url = "/assets/LEED:" +  data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/datavalidate/"+data_endpoint+"/?at=2019-12-01";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	}




