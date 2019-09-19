package com.arcapi.Parksmarttestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AddAssestTimelineManualTest extends BaseClass {

	@Test(groups="CheckAggrement")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AddAssestTimeline(String SheetName,String ProjectTypeColumn, int rownumber)  {

				
		try {
			int RowNum = data.getRowCountbyColNum("Manual", 0);
			
			for(int i=2;i<=RowNum;i++) {
				
			url = "/assets/LEED:"
					+ data.getCellData("Manual", ProjectTypeColumn, i)
					+ "/timeline/";

			CommonMethod.res = MethodCall.POSTRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
}
		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}
	

}