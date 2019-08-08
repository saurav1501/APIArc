package com.arcapi.Transittestcases;

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

import net.minidev.json.JSONObject;

public class VerifyPM25GraphValuesAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyPM25GraphValuesAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		
		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCodeForLOUser(SheetName, rownumber);
		
		CommonMethod.test = CommonMethod.extent
				.startTest("VerifyPM25GraphValuesAPI",
						"Verifies Waste Detail")
				.assignCategory("CheckWaste");
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);

       String PM25_Meter_ID = data.getCellData("Graphs", "TransitHEMeterID", rownumber+5);
			
		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/resampled/"+PM25_Meter_ID+"/").then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);
		
		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		 
		 for(int j=0;j<12;j++) {
			 
				CommonMethod.fetchedID = CommonMethod.res.path("reading["+j+"]").toString();
				
				String reading = CommonMethod.fetchedID.replaceAll(".0$", "");
				System.out.println(reading);
			    //data.setCellData("Graphs", "PM25", j+2 , reading);
				String Act_Reading = data.getCellData("Graphs", "PM25", j+2);
				Assert.assertEquals(Act_Reading, reading);
				
				/*CommonMethod.fetchedID = CommonMethod.res.path("results.waste_diverted["+j+"]").toString();
				String wastedivreading = CommonMethod.fetchedID.replaceAll(".0$", "");
				System.out.println(wastedivreading);
				String actual_Reading = data.getCellData("DataInput", "ExcelWasteDivReading", j+2);
				Assert.assertEquals(actual_Reading, wastedivreading);
				}
				CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

				CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
				*/
				CommonMethod.res.then().spec(respSpec);
			}
			
}}