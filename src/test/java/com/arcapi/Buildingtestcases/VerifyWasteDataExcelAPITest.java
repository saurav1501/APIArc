package com.arcapi.Buildingtestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifyWasteDataExcelAPITest extends BaseClass {

	@Test(groups="CheckWaste")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyWasteDataExcelAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/waste/?page_size=20";
		
		CommonMethod.res = MethodCall.GETRequest(url);
		 
		 for(int j=0;j<12;j++) {
				CommonMethod.fetchedID = CommonMethod.res.path("results.waste_generated["+j+"]").toString();
				String reading = CommonMethod.fetchedID.replaceAll(".0$", "");
				System.out.println(reading);
				String Act_Reading = data.getCellData("DataInput", "ExcelWasteGenReading", j+2);
				Assert.assertEquals(Act_Reading, reading);
				CommonMethod.fetchedID = CommonMethod.res.path("results.waste_diverted["+j+"]").toString();
				String wastedivreading = CommonMethod.fetchedID.replaceAll(".0$", "");
				System.out.println(wastedivreading);
				String actual_Reading = data.getCellData("DataInput", "ExcelWasteDivReading", j+2);
				Assert.assertEquals(actual_Reading, wastedivreading);
				}
		 		Assertion.verifyStatusCode(CommonMethod.res, 200);
			}
			
}