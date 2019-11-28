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

public class LEEDv4OMInteriorsBuildingsAPITest extends BaseClass {

	@Test(groups ="LEED v4.1 O+M: Interiors")
	 @Parameters({"SheetName","rownumber","Country" ,"environment"})
	public void LEEDv4OMInteriorsBuildingsAPI(String SheetName, int rownumber, String Country,String environment) {
    
		try {
			 if(environment.startsWith("qas"))
			 {		
			String OwnerOrg = "Ek21mBwVl4NZ";
			String ProjectName = "API LEED v4 O+M: Existing Buildings";
			String OwnerType= "Educational: College, Private";
			String ratings = "oInt";
			url = "Project/register/INP";
			payload = LEEDV4Payload.AddProjectLEEDV4Bulding(SheetName,Country,ProjectName,OwnerType,ratings,OwnerOrg,rownumber);
			CommonMethod.res = MethodCall.POSTRequestLEED(url,payload);		
			Assertion.verifyStatusCode(	CommonMethod.res, 200);
			CommonMethod.fetchedID = CommonMethod.res.path("registered.id").toString();
			data.setCellData(SheetName, "LeedIDEB", rownumber, CommonMethod.fetchedID);
		}
				   
				   else{
						String OwnerOrg  = "Vk2kQI-CIb6x";
						String ProjectName = "API LEED v4 O+M: Existing Buildings";
						String OwnerType= "Educational: College, Private";
						String ratings = "oInt";
						url = "Project/register/INP";
						payload = LEEDV4Payload.AddProjectLEEDV4Bulding(SheetName,Country,ProjectName,OwnerType,ratings,OwnerOrg,rownumber);
						CommonMethod.res = MethodCall.POSTRequestLEED(url,payload);		
						Assertion.verifyStatusCode(	CommonMethod.res, 200);
						CommonMethod.fetchedID = CommonMethod.res.path("registered.id").toString();
						data.setCellData(SheetName, "LeedIDEB", rownumber, CommonMethod.fetchedID);
					}

		
		
		}
		
		
		catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	

}
