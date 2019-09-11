package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class MeasuresVersionGetAPITest extends BaseClass {

	@Test(groups="Measures")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void MeasuresVersionGetAPI(String SheetName, String ProjectTypeColumn, int rownumber) throws IOException {

		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn,rownumber) +"/measuresversion/"+"?user_version=V1/";

		CommonMethod.res = MethodCall.GETRequest(url);
		Assertion.verifyStatusCode(CommonMethod.res, 200);
		CommonMethod.testlog("Info","**Verified repsponse V1**");
		  for(int i=0;i<20;i++)
		  {
			String responseBody = CommonMethod.res.path("results["+i+"].user_version");
	        Assert.assertEquals(responseBody.toString(),"V1");
		  }
		  
		
			 
	}

	

}