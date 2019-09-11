package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.OtherDetails;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;


public class NotificationSubscriptionDetailDeleteTest extends BaseClass {

	@Test(groups="CheckNotification")
    @Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void NotificationSubscriptionDetailDelete(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/subscriptions/ID:"
					+ data.getCellData(SheetName, "SubscriptionPK", rownumber) + "/";
			payload = OtherDetails.notificationPut();	
			CommonMethod.res = MethodCall.DELETERequest(url, payload);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}