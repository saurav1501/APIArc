package com.arcapi.Common;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class DataSnapshotBeforeReviewGETAPITest extends BaseClass {

	@Test(groups="CheckReview")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void DataSnapshotBeforeReviewGETAPI(String SheetName,String ProjectTypeColumn, int rownumber)  {
     
		try {
			
			
			url="/assets/LEED:"+data.getCellData(SheetName, ProjectTypeColumn, rownumber)+"/snapshot/?category=energy,water,waste,transportation,humanexperience&access-token="+CommonMethod.Token+"&subscription-key="+CommonMethod.SubscriptionKey; 
							
			CommonMethod.res= MethodCall.GETRequestWithoutHeader(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}