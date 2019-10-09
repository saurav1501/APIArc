package com.arcapi.Common.V2;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class IntensityAllOtherGetAPITest extends BaseClass {

	@Test(groups="CheckAnalysis")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void IntensityAllOtherGetAPI(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			
			String[] intensity = {"emissions","electricity","natural_gas","fuel","site_energy","source_energy", "water", "generated_waste","diverted_waste", "waste", "transport", "humanexperience"};
			for(String inten : intensity) {
			
			url = "/assets/LEED:"+data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/resampled/"+inten+"/?intensity=gross_area,occupancy";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCodeContinueOnFaliure(CommonMethod.res, 200);
			}
		   
			softAssert.assertAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
	

