package com.arcapi.Common.CityCom;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.CityComPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class MeasuresUpdateAPITest extends BaseClass {

	@Test(groups="CheckMeasures")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void MeasuresUpdateAPI(String SheetName,String ProjectTypeColumn, int rownumber){
	
	    try {
			url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/measures/" + data.getCellData(SheetName, "MeasuresID", rownumber)+"/?points_pursued=10";
			payload = CityComPayload.uploadMeasure();
			CommonMethod.res=MethodCall.PUTRequest(url, payload);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		
	}
	

}