package com.arcapi.Leedv4Testcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class LOProjectSyncAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "LOSheetName","LOProjectTypeColumn","rownumber" })
	public void LOProjectSyncAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.GeneratingAuthCodeForLOUser(SheetName,rownumber);
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		
        TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/syncproject/").then().extract()
				.response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("LOProjectSyncAPI Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Asset document detail")
				.assignCategory("CheckAsset");

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
	

	}

}