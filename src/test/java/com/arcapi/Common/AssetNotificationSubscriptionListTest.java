package com.arcapi.Common;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetNotificationSubscriptionListTest extends BaseClass {

	@Test(groups="CheckNotification")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetNotificationSubscriptionList(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/subscriptions/";
		CommonMethod.res = MethodCall.GETRequest(url);		
		Assertion.verifyStatusCode(CommonMethod.res, 200);		
	}


}