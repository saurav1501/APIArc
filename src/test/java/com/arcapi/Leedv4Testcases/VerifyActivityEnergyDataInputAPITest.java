package com.arcapi.Leedv4Testcases;

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

public class VerifyActivityEnergyDataInputAPITest extends BaseClass {

	@Test 
	@Parameters({ "LOSheetName", "LOProjectTypeColumn", "rownumber" })
	public void VerifyActivityEnergyDataInputAPI(String SheetName, String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCodeForLOUser(SheetName, rownumber);

		CommonMethod.test = CommonMethod.extent
				.startTest("VerifyActivityEnergyDataInputAPI", "Verifies Update asset").assignCategory("CheckAsset");

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);

				CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
						.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
						.when()
						.get("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
						 + "/activity/?order=desc&type=pf901")
						.then().extract().response();

				CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

				System.out.println(CommonMethod.responsetime);

				CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

				System.out.println(CommonMethod.res.asString());
				
				CommonMethod.res.then().spec(respSpec);
				
				CommonMethod.fetchedID = CommonMethod.res.path("logs.verb").toString();
				
				System.out.println(CommonMethod.fetchedID);
				
				String verb1 = "created a new meter";
				String verb2 = "has uploaded a new file with name Creditfile.pdf";
				String verb3 = "created a new meter <a class=\"handle\">EnergyFileUploadTestMeter</a>";
				
				Assert.assertTrue(CommonMethod.fetchedID.contains(verb1), "Activity log is not correct");
				Assert.assertTrue(CommonMethod.fetchedID.contains(verb2), "Activity log is not correct");
				Assert.assertTrue(CommonMethod.fetchedID.contains(verb3), "Activity log is not correct");

				CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

				CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");


			}

	

}