package com.arcapi.Leedv4Testcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.LEEDV4Payload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

public class LEEDv4OMExistingBuildingsAPITest extends BaseClass {

	@Test(groups ="LEED v4 O+M: Existing Buildings")
    @Parameters({"SheetName","rownumber","Country" })
	public void LEEDv4OMExistingBuildingsAPI(String SheetName, int rownumber, String Country) {
    
		try {
			String OwnerOrg  = "EymX5ZKWALZae";
			String ProjectName = "API LEED v4 O+M: Existing Buildings";
			String OwnerType= "Educational: College, Private";
			String ratings = "v4_1.oEb";
			url = "Project/register/INP";
			payload = LEEDV4Payload.AddProjectLEEDV4Bulding(SheetName,Country,ProjectName,OwnerType,ratings,OwnerOrg,rownumber);
			CommonMethod.res = MethodCall.POSTRequestLEED(url,payload);		
			Assertion.verifyStatusCode(	CommonMethod.res, 200);
			CommonMethod.fetchedID = CommonMethod.res.path("registered.id").toString();
			data.setCellData(SheetName, "LeedIDEB", rownumber, CommonMethod.fetchedID);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	

}
