package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class AssetNotificationSubscriptionPostTest extends BaseClass {

	@Test(groups="CheckNotification")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void AssetNotificationSubscriptionPost(String SheetName, String ProjectTypeColumn, int rownumber)
	 {

	    try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/subscriptions/";
			CommonMethod.res = MethodCall.POSTRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 400);
			CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
		} catch (Exception e) {
			e.printStackTrace();
		}

	 }}