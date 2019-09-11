package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.OtherDetails;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetNotificationSubscriptionPostTest extends BaseClass {

	@Test(groups= "CheckNotification")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetNotificationSubscriptionPost(String SheetName,String ProjectTypeColumn, int rownumber){
        
		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/subscriptions/";
			payload =OtherDetails.notification();	
			CommonMethod.res = MethodCall.POSTRequest(url, payload);
			CommonMethod.fetchedID = CommonMethod.res.path("id").toString();
			data.setCellData(SheetName, "SubscriptionPK", rownumber, CommonMethod.fetchedID);
			Assertion.verifyStatusCode(CommonMethod.res , 201);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}