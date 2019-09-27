package com.arcapi.Common.Neg;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ReturnReviewGetAPITest extends BaseClass {

	@Test(groups="CheckSubmitReview")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void ReturnReviewGetAPI(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/review/return/";
			CommonMethod.res= MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(	CommonMethod.res, 400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}