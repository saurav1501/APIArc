package com.arcapi.Leedv4Testcases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifyActivityEnergyDataInputAPITest extends BaseClass {

	@Test(groups="CheckActivity")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void VerifyActivityEnergyDataInputAPI(String SheetName, String ProjectTypeColumn, int rownumber) {

				try {
					url = "/assets/LEED:" + data.getCellData(sheetName, ProjectTypeColumn, rownumber)
					+ "/activity/?order=desc&type=pf901";

					CommonMethod.res =MethodCall.GETRequest(url);
					Assertion.verifyStatusCode(CommonMethod.res, 200);		
					CommonMethod.fetchedID = CommonMethod.res.path("verb").toString();
					System.out.println(CommonMethod.fetchedID);
					String verb1 = "created a new meter";
					String verb2 = "has uploaded a new file with name Creditfile.pdf";
					String verb3 = "created a new meter <a class=\"handle\">EnergyFileUploadTestMeter</a>";
					
					Assert.assertTrue(CommonMethod.fetchedID.contains(verb1), "Activity log is not correct");
					Assert.assertTrue(CommonMethod.fetchedID.contains(verb2), "Activity log is not correct");
					Assert.assertTrue(CommonMethod.fetchedID.contains(verb3), "Activity log is not correct");
				} catch (Exception e) {
					e.printStackTrace();
				}

				

			}

	

}