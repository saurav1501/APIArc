package com.arcapi.Common;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AddAssestTimelinePOSTAPITest extends BaseClass {

	@Test(groups="CheckAsset")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AddAssestTimeline(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		
		try {
			projectID= data.getCellData(SheetName, ProjectTypeColumn, rownumber);
			url="/assets/LEED:"+projectID+"/timeline/";
			CommonMethod.res = MethodCall.POSTRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			Assertion.verifyStatusMessage(CommonMethod.res, statusMessage);
			CommonMethod.res.then().assertThat().body("success", equalTo("Timeline added successfully"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}		
}
}