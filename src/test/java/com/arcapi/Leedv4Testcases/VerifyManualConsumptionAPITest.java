package com.arcapi.Leedv4Testcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifyManualConsumptionAPITest extends BaseClass {

	@Test(groups="CheckData")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyManualConsumptionAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {



		url ="/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
				+ data.getCellData("DataInput", "HumanExperienceMeterID", 2) + "/consumption/";
 
		CommonMethod.res = MethodCall.GETRequest(url);
		Assertion.verifyStatusCode(CommonMethod.res , 200);
			
	
	
		}
	}
	
