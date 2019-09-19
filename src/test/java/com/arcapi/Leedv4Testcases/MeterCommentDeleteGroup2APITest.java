package com.arcapi.Leedv4Testcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class MeterCommentDeleteGroup2APITest extends BaseClass {

	@Test(groups="CheckComment")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void MeterCommentDeleteGroup2API(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			int row = 2;
					
			 String[] type = {"waste","transport","human"};
			 
			 for(String Type : type) {
			
			 url = "/assets/LEED:" + data.getCellData(sheetName, ProjectTypeColumn, rownumber) + 
						"/"+Type+"/comment/"+data.getCellData("LEEDONLINE", "CommentIDGroup2", row)+"/";

			 CommonMethod.res = MethodCall.DELETERequest(url);
			 Assertion.verifyStatusCode(CommonMethod.res, 200);
			row++;
}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		
	}
	
}