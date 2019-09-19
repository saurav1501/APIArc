package com.arcapi.TrailFlowtestcasesComplete;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class AssetUpdateBuildingTrailActAPITest extends BaseClass {

	@Test(groups="CheckTrial")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetUpdateforNewAssetPUTAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

		
		try {
			JSONObject jsonAsMap = new JSONObject();
			jsonAsMap.put("is_trial_selected", "true");
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/?sync_sap=False";
			CommonMethod.res = MethodCall.PUTRequest(url,jsonAsMap);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}