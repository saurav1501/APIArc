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

public class HumanExperienceMeterDeleteAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "LOSheetName","LOProjectTypeColumn","rownumber" })
	public void HumanExperienceMeterDeleteAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

	    CommonMethod.GeneratingAuthCodeForLOUser(SheetName, rownumber);
	    
	    CommonMethod.test = CommonMethod.extent
				.startTest("HumanExperience Meter Delete API" + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Delete existing meter")
				.assignCategory("CheckMeter");

		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		
		int RowNum = data.getRowCountbyColNum("DataInput", 6);
        //int PKRowNum = data.getRowCountbyColNum("DataInput", 1);
		
		for (int i =2; i<= RowNum;i++) {

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.when()
				.delete("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
						+ data.getCellData("DataInput", "HumanExperienceMeterID", i) + "/")
				.then().extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);


		
		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().assertThat().statusCode(403);

	}
	}

}