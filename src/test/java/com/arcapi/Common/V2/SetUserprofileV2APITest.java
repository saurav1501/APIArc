package com.arcapi.Common.V2;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class SetUserprofileV2APITest extends BaseClass{
	@Test(groups="SetUserProfileV2")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void SetUserprofileV2API(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		    
		    url = "/auth/userprofile/";
		    map = new HashMap<>();
		    map.put("ui_version", "v2");    
		    CommonMethod.res = MethodCall.PUTRequest(url, map);
		    Assertion.verifyStatusCode(CommonMethod.res, 200);
		    
	
	}
}

