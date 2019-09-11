package com.arcapi.Common;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.OtherDetails;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class FeedbackSubmitPostAPITest extends BaseClass {

	@Test(groups="CheckFeedback")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void FeedbackSubmitPostAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

		try {
			url= "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/feedbacks/";
			payload = OtherDetails.submitFeedback();
			CommonMethod.res= MethodCall.POSTRequest(url, payload);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
}