package com.arcapi.Common.CityCom;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.CityComPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class MeasuresVersionPostAPITest extends BaseClass {

	@Test(groups="CheckMeasures")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void MeasuresVersionPostAPI(String SheetName,String ProjectTypeColumn, int rownumber){
	
		try {
			payload= CityComPayload.readyReview();
			url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/measuresversion/" + data.getCellData(SheetName, "MeasuresID", rownumber)+"/";
			CommonMethod.res = MethodCall.POSTRequest(url, payload);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}

			
	}
	

}