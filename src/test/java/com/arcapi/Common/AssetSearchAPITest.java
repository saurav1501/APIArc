package com.arcapi.Common;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;


public class AssetSearchAPITest extends BaseClass {

	@Test(groups="Search")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetSearchAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
        
		try {
			projectID= data.getCellData(SheetName, ProjectTypeColumn, rownumber);
			url="/assets/search/?q=".concat(projectID);
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
	}


}