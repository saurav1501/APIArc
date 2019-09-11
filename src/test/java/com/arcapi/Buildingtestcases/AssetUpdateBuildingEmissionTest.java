package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class AssetUpdateBuildingEmissionTest extends BaseClass {

	@Test(groups="CheckMeter")
    @Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void AssetUpdateforNewAssetPUTAPI(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber)  {

	
		try {
			JSONObject jsonAsMap = new JSONObject();		
			jsonAsMap.put("manual_emission_enabled", "true");
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/";
			CommonMethod.res = MethodCall.PUTRequest(url, jsonAsMap);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
}