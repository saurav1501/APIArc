package com.arcapi.Common;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetDetailAPITest extends BaseClass {

	@Test(groups="CheckAsset")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void AssetDetailAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		try {
			url="/assets/LEED:/" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/";
			CommonMethod.res = MethodCall.GETRequest(url);
			CommonMethod.fetchedID = CommonMethod.res.path("key");
			data.setCellData(SheetName, "BuildingKeyID", rownumber, CommonMethod.fetchedID);	
			CommonMethod.res.then().spec(respSpec);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}



}