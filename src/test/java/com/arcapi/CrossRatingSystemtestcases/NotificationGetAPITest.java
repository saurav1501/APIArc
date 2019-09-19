package com.arcapi.CrossRatingSystemtestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class NotificationGetAPITest extends BaseClass {

	@Test(groups="CheckNotification")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void NotificationGetAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/notifications/";

			CommonMethod.res = MethodCall.GETRequest(url);
			
			Assertion.verifyStatusCode(CommonMethod.res , 404);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}}