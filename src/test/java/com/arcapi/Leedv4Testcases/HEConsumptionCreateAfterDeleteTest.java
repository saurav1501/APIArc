package com.arcapi.Leedv4Testcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

import net.minidev.json.JSONObject;

public class HEConsumptionCreateAfterDeleteTest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
    @Parameters({ "LOSheetName","LOProjectTypeColumn","rownumber" })
	public void HumanExperienceConsumptionCreateAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCodeForLOUser(SheetName,rownumber);
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);

	
		CommonMethod.test = CommonMethod.extent
				.startTest("HumanExperience Consumption CreateAPI" + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies consumption creation")
				.assignCategory("CheckConsumption");
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		int RowNum = data.getRowCountbyColNum("DataInput", 6);
		
		for (int i =2; i<= RowNum;i++) {

		JSONObject jsonAsMap1 = new JSONObject();
		jsonAsMap1.put("start_date", "2018-09-01");
		jsonAsMap1.put("end_date", "2018-09-30");
		jsonAsMap1.put("reading", "120");
		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap1).when()
				.post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
						+ data.getCellData("DataInput", "HumanExperienceMeterID", i) + "/consumption/")
				.then().extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		
		CommonMethod.res.then().assertThat().statusCode(201);

		CommonMethod.fetchedID = CommonMethod.res.path("id").toString();

		data.setCellData("DataInput", "HumanExperiencePK", i, CommonMethod.fetchedID);

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");

	}
	}

}