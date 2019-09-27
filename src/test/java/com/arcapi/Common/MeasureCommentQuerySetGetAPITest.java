package com.arcapi.Common;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class MeasureCommentQuerySetGetAPITest extends BaseClass {

	@Test(groups="CheckComment")
    @Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void MeasureCommentQuerySetGetAPI(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/waste/comment/";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			CommonMethod.fetchedID = CommonMethod.res.path("id").toString();	
			String pk= CommonMethod.fetchedID.replace("[", "").replace("]", "");
			System.out.println(CommonMethod.fetchedID);
			data.setCellData(SheetName, "PK", rownumber,pk);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}


}