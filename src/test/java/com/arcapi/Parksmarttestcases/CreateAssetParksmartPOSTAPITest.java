package com.arcapi.Parksmarttestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;
import com.relevantcodes.extentreports.LogStatus;

public class CreateAssetParksmartPOSTAPITest extends BaseClass {

	@Test(groups = { "Certification", "Precertification","PerformanceScore","Recertification" })
	@Parameters({ "SheetName", "ProjectType","ProjectTypeColumn","rownumber" })
	public void CreateAssetPOSTAPI(String SheetName, String ProjectType,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCode(SheetName,rownumber);
		
String[] CountryOptions  = {"US"};
		
		String StateCode = null;
		
		String Country = (CountryOptions[new Random().nextInt(CountryOptions.length)]);
		
		if(Country.equalsIgnoreCase("US")){
			
			StateCode = "DC";
		}
		
		else{
			
			StateCode = "10";
		}
		
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		CommonMethod.res = given().log().all()
				.parameters(
					    "name", "MachineTestProject-Parksmart",
					    "project_type", ProjectType,
					    "rating_system","PARKSMART", 
					    "ownerType","Investor: Bank",
					    "organization","A & A Dealers",
					    "owner_email","stg-01@gmail.com",
					    "manageEntityCountry",Country,
					    "confidential", false,
					    "noOfParkingSpace", "15", 
					    "noOfParkingLevels","2",
					    "year_constructed", "2018-04-30",
					    "street", "1 Mount Road", 
					    "city","DC", 
					    "country", Country, 
					    "state", StateCode,
					    "zip_code","20037"
					    )
				.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when().post("/assets/").then()
				.extract().response();
		
		System.out.println("This is " + Country + " " + "Project");

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.res.asString());

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Create New Parksmart Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Add asset")
				.assignCategory("CheckAsset");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		CommonMethod.res.then().assertThat().statusCode(201);
		
		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);

		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.fetchedID = CommonMethod.res.path("leed_id").toString();

		System.out.println(CommonMethod.fetchedID);

		data.setCellData(SheetName, ProjectTypeColumn, rownumber, CommonMethod.fetchedID);

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");

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