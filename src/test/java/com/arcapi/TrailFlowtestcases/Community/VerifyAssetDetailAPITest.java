package com.arcapi.TrailFlowtestcases.Community;

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
import com.jayway.restassured.path.json.JsonPath;
import com.relevantcodes.extentreports.LogStatus;
import static org.hamcrest.Matchers.*;

public class VerifyAssetDetailAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetDetailAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCodeForLOUser(SheetName,rownumber);
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/").then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);
		
		CommonMethod.test = CommonMethod.extent
				.startTest("Asset Detail API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Asset detail")
				.assignCategory("CheckAsset");

		System.out.println(CommonMethod.res.asString());
		
		JsonPath jp = new JsonPath(CommonMethod.res.asString()); 
		String occupancy = jp.get("occupancy").toString();
		String LeedID = jp.get("leed_id").toString();
		String Gross = jp.get("gross_area").toString();
		double value = Double.parseDouble(Gross);
    	int GFA = (int)value;
		//String OpHours = jp.get("operating_hours").toString();
		
		
		CommonMethod.res.then().assertThat().body("name", equalTo(data.getCellData(SheetName, "ProjectName", rownumber).toString()));
		CommonMethod.res.then().assertThat().body("unitType", equalTo(data.getCellData(SheetName, "Unit", rownumber).toString()));
		//CommonMethod.res.then().assertThat().body("spaceType", equalTo(data.getCellData(SheetName, "AnticipitatedType", rownumber).toString()));
		CommonMethod.res.then().assertThat().body("street", equalTo(data.getCellData(SheetName, "Address", rownumber).toString()));
		CommonMethod.res.then().assertThat().body("city", equalTo(data.getCellData(SheetName, "City", rownumber).toString()));
		CommonMethod.res.then().assertThat().body("state", equalTo(data.getCellData(SheetName, "State", rownumber).toString()));
		CommonMethod.res.then().assertThat().body("country", equalTo(data.getCellData(SheetName, "Country", rownumber).toString()));
		CommonMethod.res.then().assertThat().body("ownerType", equalTo(data.getCellData(SheetName, "OwnerType", rownumber).toString()));
		CommonMethod.res.then().assertThat().body("organization", equalTo(data.getCellData(SheetName, "OwnerOrg", rownumber).toString()));
		CommonMethod.res.then().assertThat().body("owner_email", equalTo(data.getCellData(SheetName, "OwnerEmail", rownumber).toString()));
		CommonMethod.res.then().assertThat().body("manageEntityCountry", equalTo(data.getCellData(SheetName, "OwnerCountry", rownumber).toString()));
		CommonMethod.res.then().assertThat().body("year_constructed", equalTo(data.getCellData(SheetName, "Year", rownumber).toString()));
		
		Assert.assertEquals(occupancy, data.getCellData(SheetName, "Occupancy", rownumber));
		Assert.assertEquals(LeedID, data.getCellData(SheetName, ProjectTypeColumn, rownumber));
		Assert.assertEquals(GFA, Integer.parseInt(data.getCellData(SheetName, "GFA", rownumber)));
		//Assert.assertEquals(OpHours, data.getCellData(SheetName, "OpHours", rownumber));
		
		CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
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