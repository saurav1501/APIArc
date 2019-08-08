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

public class PaymentOrderSubmitPOSTAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void PaymentOrderSubmitPOSTAPI(String SheetName, String ProjectTypeColumn, int rownumber)
			throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode();
		
		CommonMethod.test = CommonMethod.extent
				.startTest("Payment Order Submit API Test  ",
						"Verifies Payment done for registration")
				.assignCategory("CheckPayment");

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		for (String str : value) {

		CommonMethod.res = given()
				.parameters("name", str,
						"email", str,
						"unit_price", str,
						"material_code",str,
						"material_description", str,
						"quantity", str,
						"company_code", str,
						"street", str,
						"city", str,
						"state",str, 
						"country", str,
						"zip_code", str,
						"SoReference", str,
						"actual_price",str,
						"annual_price", str
						,"paymetric_r",str,
						"paymetric_s",str
						)
				.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/payments/order/")
				.then().extract().response();

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

	

	CommonMethod.res.then().assertThat().statusCode(400);

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