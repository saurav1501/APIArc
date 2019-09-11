package com.arcapi.Common.CityCom;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.CityComPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class MeasuresPostAPITest extends BaseClass {

	@Test(groups="CheckMeasures")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void MeasuresPostAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

		try {
			url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/measures/" +  data.getCellData(SheetName, "MeasuresID", rownumber)+"/?points_pursued=10";
			String basescore[] = {"base_score.A","base_score.A.1.1","base_score.A.2.1","base_score.A.3.1","base_score.A.4.1","base_score.A.5.1","base_score.A.6.1","base_score.A.7.1","base_score.A.8.1","base_score.A.9.1","base_score.A.10.1","base_score.B"};
			for(String base : basescore) {
			payload = CityComPayload.checkMeasure(base);
			CommonMethod.res = MethodCall.POSTRequest(url, payload);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
}
	

	
