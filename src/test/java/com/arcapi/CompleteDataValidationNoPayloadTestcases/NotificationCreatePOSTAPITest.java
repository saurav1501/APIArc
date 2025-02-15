package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class NotificationCreatePOSTAPITest extends BaseClass {

	@Test(groups="CheckNotificaiton")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void NotificationCreatePOSTAPI(String SheetName, String ProjectTypeColumn, int rownumber)
	 {
		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/notifications/";
			CommonMethod.res = MethodCall.POSTRequest(url);
			CommonMethod.res.then().assertThat().statusCode(400);
			CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	}