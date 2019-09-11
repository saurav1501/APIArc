package com.arcapi.Common;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class NotificationSubscriptionDetailGetTest extends BaseClass {

	@Test(groups="CheckNotification")
    @Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void NotificationSubscriptionDetail(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/subscriptions/ID:"
					+ data.getCellData(SheetName, "SubscriptionPK", rownumber) + "/";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}


}