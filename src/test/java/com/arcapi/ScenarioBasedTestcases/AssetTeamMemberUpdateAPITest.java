package com.arcapi.ScenarioBasedTestcases;

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

public class AssetTeamMemberUpdateAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber","UserName" })
	public void AssetTeamMemberUpdateAPI(String SheetName,String ProjectTypeColumn, int rownumber,String UserName) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode();
		
		CommonMethod.test = CommonMethod.extent
				.startTest("AssetTeamMember Update API Test" + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Team member updation")
				.assignCategory("CheckTeam");
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		String Role[] = {"ZRPO80","ZRPO81"};
		
		for (String str : Role) {

		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("user_email", data.getCellData(SheetName, UserName, rownumber));
		jsonAsMap.put("Reltyp", str);
		jsonAsMap.put("Responsibility", data.getCellData(SheetName, ProjectTypeColumn, rownumber));

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap).when()
				.put("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/teams/update/").then()
				.extract().response();

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

	

	CommonMethod.res.then().assertThat().statusCode(403);

	CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
}
	}
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