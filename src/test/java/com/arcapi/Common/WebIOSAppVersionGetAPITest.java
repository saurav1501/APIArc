package com.arcapi.Common;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class WebIOSAppVersionGetAPITest extends BaseClass {


@Test(groups="WebIOSAppVersion")
@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
public void WebIOSAppVersionGetAPI(String SheetName,String ProjectTypeColumn, int rownumber)  {
 
	try {
		url="/version/";
		CommonMethod.res= MethodCall.GETRequest(url);
		Assertion.verifyStatusCode(CommonMethod.res, 200);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
}
}

