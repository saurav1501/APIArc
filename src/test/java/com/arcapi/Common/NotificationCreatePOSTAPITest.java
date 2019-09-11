package com.arcapi.Common;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.OtherDetails;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class NotificationCreatePOSTAPITest extends BaseClass {

	@Test(groups="CheckNotification")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void NotificationCreatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber){
		
		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/notifications/";
			payload= OtherDetails.notification();
			CommonMethod.res= MethodCall.POSTRequest(url, payload);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	
}