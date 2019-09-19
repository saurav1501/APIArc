package com.arcapi.Leedv4Testcases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifyManualConsumptionAfterUpdateAPITest extends BaseClass {

	@Test(groups="CheckUpdate")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyManualConsumptionAfterUpdateAPI(String SheetName,String ProjectTypeColumn, int rownumber)  {

			
        int RowNum = 8;
		
		for (int i =2; i<= RowNum;i++) {

		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
				+ data.getCellData("DataInput", "HumanExperienceMeterID", i) + "/consumption/";
		
		CommonMethod.res = MethodCall.GETRequest(url);
		Assertion.verifyStatusCode(CommonMethod.res, 200);
				
		CommonMethod.fetchedID = CommonMethod.res.path("results.reading[0]").toString();
		double value = Double.parseDouble(CommonMethod.fetchedID);
    	int reading = (int)value;
		String Act_Reading = data.getCellData("DataInput", "HEFinalReading", i);
		Assert.assertEquals(Integer.parseInt(Act_Reading), reading);
		
	
	}
	}
	
}