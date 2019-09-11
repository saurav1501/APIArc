package com.arcapi.Common.CityCom;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class MeasuresListAPITest extends BaseClass {

	@Test(groups="CheckMeasures")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber"})
	public void MeasuresListAPI(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			url ="/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/measures/";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res,200);
			
			CommonMethod.fetchedID = CommonMethod.res.path("measure_id").toString();
			System.out.println(CommonMethod.fetchedID);
			data.setCellData(SheetName,"MeasuresID", rownumber, CommonMethod.fetchedID);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		 
	}
	
}