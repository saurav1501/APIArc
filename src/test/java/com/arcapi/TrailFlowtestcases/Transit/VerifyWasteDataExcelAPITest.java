package com.arcapi.TrailFlowtestcases.Transit;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

public class VerifyWasteDataExcelAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyWasteDataExcelAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCodeForLOUser(SheetName, rownumber);
		
		CommonMethod.test = CommonMethod.extent
				.startTest("VerifyWasteDataExcelAPI Test",
						"Verifies Waste Detail")
				.assignCategory("CheckWaste");
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);


		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/waste/?page_size=20").then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);
		
		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		 
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
				CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

				CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
				
				CommonMethod.res.then().spec(respSpec);
			}
			
}