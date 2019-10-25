package com.arcapi.Communitytestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.LEEDV4Payload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

public class LeedExistingCommunityCreateAssetAPITest extends BaseClass {

	@Test(groups ="LEED v4.1 Communities: Existing")
    @Parameters({"SheetName","rownumber","Country" })
	public void LeedExistingCommunityCreateAssetAPI(String SheetName, int rownumber, String Country) {
    
		try {
			String OwnerOrg  = "4kXGQBPNeEEb";
			String ProjectName = "API LEED v4.1 Communities: Existing";
			String OwnerType= "Public Sector: Airports";
			String ratings = "v4_1.cmEx";
			url = "Project/register/INP";
			payload = LEEDV4Payload.AddProjectLEEDV4(SheetName,Country,ProjectName,OwnerType,ratings,OwnerOrg,rownumber);
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