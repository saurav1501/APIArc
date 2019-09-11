package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.AddProjectPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetUpdateBuildingAPITest extends BaseClass {

	@Test(groups="CheckUpdate")
    @Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetUpdateforNewAssetPUTAPI(String SheetName,String ProjectTypeColumn, int rownumber){
        
		try {
			url= "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/";
			payload= AddProjectPayload.yearconstructed();
			CommonMethod.res = MethodCall.PUTRequest(url,payload);
			Assertion.verifyStatusCode(CommonMethod.res,200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}