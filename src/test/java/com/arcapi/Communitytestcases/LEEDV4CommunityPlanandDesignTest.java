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

public class LEEDV4CommunityPlanandDesignTest extends BaseClass {

	@Test(groups ="Add Project LEED v4.1 Communities: Plan and Design")
	@Parameters({"SheetName","rownumber","Country" ,"environment"})
	public void LEEDV4CommunityPlanandDesign(String SheetName, int rownumber, String Country,String environment) {
    
		try {
			if(environment.equalsIgnoreCase("qas")) {	
			String OwnerOrg = "Ek21mBwVl4NZ";
			String ProjectName = "API LEED v4.1 Communities: Plan and Design";
			String ratings = "v4_1.cmPd";
			String OwnerType = "Service Provider";
			url = "Project/register/INP";
			payload = LEEDV4Payload.AddProjectLEEDV4(SheetName,Country,ProjectName,OwnerType,ratings,OwnerOrg,rownumber);
			CommonMethod.res = MethodCall.POSTRequestLEED(url,payload);		
			Assertion.verifyStatusCode(	CommonMethod.res, 200);
			CommonMethod.fetchedID = CommonMethod.res.path("registered.id").toString();
			data.setCellData(SheetName, "LeedIDEB", rownumber, CommonMethod.fetchedID);
		}	   
			else{
			    String OwnerOrg  = "Vk2kQI-CIb6x";
			    String ProjectName = "API LEED v4.1 Communities: Plan and Design";
				String ratings = "v4_1.cmPd";
				String OwnerType = "Service Provider";
				url = "Project/register/INP";
				payload = LEEDV4Payload.AddProjectLEEDV4(SheetName,Country,ProjectName,OwnerType,ratings,OwnerOrg,rownumber);
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