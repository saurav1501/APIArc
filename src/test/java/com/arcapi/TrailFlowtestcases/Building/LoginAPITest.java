package com.arcapi.TrailFlowtestcases.Building;

import static com.jayway.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.response.Headers;
import com.relevantcodes.extentreports.LogStatus;
import static org.hamcrest.Matchers.equalTo;

public class LoginAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName", "rownumber" })
	public void LoginAPI(String SheetName, int rownumber) {

		CommonMethod.ExtentReportConfig();

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", SubscriptionKey).spec(reqSpec)
				.parameters("username", data.getCellData(SheetName, "NormalUserName", rownumber), "password",
						data.getCellData(SheetName, "NormalPassword", rownumber))
				.when().post("/auth/login/").then().extract().response();
		
		/*Headers ResponseHeader = CommonMethod.res.getHeaders();
		System.out.println(ResponseHeader);
		
		System.out.println(ResponseHeader.getValue("X-Frame-Options"));*/

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		CommonMethod.test = CommonMethod.extent
				.startTest("Login API Test  " + CommonMethod.getLabel(CommonMethod.responsetime), "Verifies Login")
				.assignCategory("CheckLogin");

		System.out.println(CommonMethod.res.asString());

		CommonMethod.fetchedID = CommonMethod.res.path("authorization_token").toString();

		System.out.println(CommonMethod.fetchedID);

		header = "Bearer " + CommonMethod.fetchedID;
		System.out.println(header);

		data.setCellData(SheetName, "BearerToken", rownumber, header);

		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		
		System.out.println(CommonMethod.res.path("token_type"));
		
		CommonMethod.res.then().assertThat().body("token_type", equalTo("Bearer"));
		
		CommonMethod.res.then().assertThat().statusCode(200);

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
