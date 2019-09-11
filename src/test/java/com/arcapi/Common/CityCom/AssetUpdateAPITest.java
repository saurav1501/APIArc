package com.arcapi.Common.CityCom;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.AddProjectPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetUpdateAPITest extends BaseClass {

	@Test(groups="CheckUpdate")
    @Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetUpdateforNewAssetPUTAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		url= "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/";
		payload= AddProjectPayload.yearconstructedCityCom();
		
		CommonMethod.res = MethodCall.PUTRequest(url, payload);
		Assertion.verifyStatusCode(CommonMethod.res, 200);
		
	}
	}