package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.OtherDetails;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class InstallAllAppsAfterDeleteAPITest extends BaseClass {

	@Test(groups="CheckApp")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void InstallAllAppsAfterDeleteAPI(String SheetName,String ProjectTypeColumn, int rownumber){

        try {
			url= "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/apps/";		
			CommonMethod.testlog("Info", "Verifying Installing Dropbox");
			payload=OtherDetails.Dropbox();
			CommonMethod.res = MethodCall.POSTRequest(url, payload);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			
			CommonMethod.testlog("Info", "Verifying Installing OneDrive");
			payload=OtherDetails.OneDrive();
			CommonMethod.res = MethodCall.POSTRequest(url, payload);
			Assertion.verifyStatusCode(	CommonMethod.res, 200);
			
			CommonMethod.testlog("Info", "Verifying Installing GoogleDrive");
			payload=OtherDetails.GoogleDrive();
			CommonMethod.res = MethodCall.POSTRequest(url, payload);
			Assertion.verifyStatusCode(	CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	}
	

