package com.arcapi.Leedv4Testcases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifyActivityWasteDataInputAPITest extends BaseClass {

	@Test 
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void VerifyActivityWasteDataInputAPI(String SheetName, String ProjectTypeColumn, int rownumber){

		        try {
					url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
					 + "/activity/?order=desc&type=pf903";
					
					CommonMethod.res = MethodCall.GETRequest(url);

					Assertion.verifyStatusCode(CommonMethod.res, 200);
					CommonMethod.fetchedID = CommonMethod.res.path("verb").toString();
						
					String verb1 = "added a new";
					String verb2 = "has uploaded a new file with name Creditfile.pdf";
					
					Assert.assertTrue(CommonMethod.fetchedID.contains(verb1), "Activity log is not correct");
					Assert.assertTrue(CommonMethod.fetchedID.contains(verb2), "Activity log is not correct");
				} catch (Exception e) {
					e.printStackTrace();
				}
			

				

			}

}