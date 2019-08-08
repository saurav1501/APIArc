package com.arcapi.CompleteDataValidationTestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;
import com.relevantcodes.extentreports.LogStatus;

import net.minidev.json.JSONObject;

public class MeasuresUpdateAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void MeasuresUpdateAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode();
		
		CommonMethod.test = CommonMethod.extent
				.startTest("MeasuresUpdate API Test",
						"MeasuresUpdateAPITest")
				.assignCategory("Measures");
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		for (String str : value) {
		
		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("measure_id", str);
		jsonAsMap.put("data", str);

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header)
				.header("content-type","application/json")
				.spec(reqSpec).body(jsonAsMap).when()
				.put("/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/measures/" + 
				data.getCellData(SheetName, "MeasuresID", rownumber)+"/?points_pursued=6")
				.then().extract()
				.response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		CommonMethod.testlog("Info", "Starting Test for Test Data " + str);
		CommonMethod.testlog("Info", "Response from API" + CommonMethod.res.asString());
		CommonMethod.testlog("Info", "Content Type is : " + CommonMethod.res.getContentType());
		CommonMethod.testlog("Info", "Status Code is : " + CommonMethod.res.getStatusCode());
		System.out.println(CommonMethod.res.asString());
		System.out.println("Content Type is : " + CommonMethod.res.getContentType());
		System.out.println("Status Code is : " + CommonMethod.res.getStatusCode());

       if(str.equalsIgnoreCase(" ")) {
			
			CommonMethod.res.then().assertThat().statusCode(404);

			CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
			
		}

		else {

	CommonMethod.res.then().assertThat().statusCode(200);

	CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
}
	}}

	@AfterMethod
	public void teardown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			CommonMethod.test.log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			CommonMethod.test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		} else {
			CommonMethod.test.log(LogStatus.PASS, "Test passed");
		}

		CommonMethod.extent.endTest(CommonMethod.test);
		CommonMethod.extent.flush();

	}

}