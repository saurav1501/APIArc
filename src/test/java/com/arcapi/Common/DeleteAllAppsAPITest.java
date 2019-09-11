package com.arcapi.Common;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class DeleteAllAppsAPITest extends BaseClass {

	@Test(groups="CheckApp")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void DeleteAllAppsAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		
	    for(int i=2;i<5;i++) {
		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/apps/?app="+i;
		CommonMethod.res = MethodCall.DELETERequest(url);
		Assertion.verifyStatusCode(	CommonMethod.res, 200);
	   }
	}

}