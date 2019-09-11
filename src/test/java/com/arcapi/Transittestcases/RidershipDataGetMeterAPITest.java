package com.arcapi.Transittestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class RidershipDataGetMeterAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void RidershipDataGetMeterAPI(String SheetName,String ProjectTypeColumn, int rownumber){
		
		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/?kind=ridership";;
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			
			
			CommonMethod.fetchedID = CommonMethod.res.path("results.id[1]").toString();
			
			data.setCellData("Graphs", "TransitHEMeterID", 10, CommonMethod.fetchedID);
			
			CommonMethod.fetchedID = CommonMethod.res.path("results.name[]").toString();
			
			data.setCellData("Graphs", "TransitHEMeterName", 10, CommonMethod.fetchedID);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}



}