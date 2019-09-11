package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class MetersListEmissionTest extends BaseClass {

	@Test(groups="CheckEmission")
	@Parameters({"SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void MetersAPI(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber)  {

	
		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/?kind=emission_factor";
			CommonMethod.res = MethodCall.GETRequest(url);
			CommonMethod.fetchedID = CommonMethod.res.path("results.id").toString().replaceAll("\\[", "").replaceAll("\\]","");
			data.setCellData(CustomSheetName, "EmissionMeterID", rownumber, CommonMethod.fetchedID);
			Assertion.verifyStatusCode(CommonMethod.res , 200);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
}