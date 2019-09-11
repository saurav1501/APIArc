package com.arcapi.Buildingtestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.ReviewPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class SubmitReviewPOSTAPITest extends BaseClass {

	@Test(groups="CheckSubmitReview")
	@Parameters({ "SheetName", "ProjectTypeColumn","rownumber", "ProjectType","SoReferenceSR" })
	public void SubmitReviewPOSTAPI(String SheetName, String ProjectTypeColumn, int rownumber, String ProjectType,String SoReferenceSR){

		try {
			payload = ReviewPayload.submitReview(ProjectType, ProjectTypeColumn,SoReferenceSR);		
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/review/";
			CommonMethod.res= MethodCall.POSTRequest(url, payload);
			Assertion.verifyStatusCode(CommonMethod.res, 403);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}