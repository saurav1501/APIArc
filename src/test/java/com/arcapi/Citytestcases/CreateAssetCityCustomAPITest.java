package com.arcapi.Citytestcases;

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

public class CreateAssetCityCustomAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName", "ProjectType","CustomSheetName","rownumber","Country",})
	public void CreateAssetPOSTAPI(String SheetName, String ProjectType,String CustomSheetName, int rownumber, String Country) throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCode(SheetName,rownumber);
		
		CommonMethod.test = CommonMethod.extent
				.startTest("Create New Asset City Test","Verifies Add asset")
				.assignCategory("CheckAsset");

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		String State = null;
		String Unit = null;
		String OwnerType  = null;
		String ZipCode = null;
		
		int maxrow = Customdata.getRowCountbyColNum(CustomSheetName, 1);
		
      for(int i = 2;i<=maxrow; i++) {
		
		String[] UnitOptions  = {"SI","IP"};
		
		Unit = (UnitOptions[new Random().nextInt(UnitOptions.length)]);
		
		data.setCellData(SheetName, "Unit", rownumber, Unit);
		
		
		String[] OwnerTypeOptions  = {"Business Improvement District",
			    "Community Development Corporation or Non-profit Developer",
			    "Corporate: Privately Held",
			    "Corporate: Publicly Traded",
			    "Educational: College, Private",
			    "Educational: College, Public",
			    "Educational: Community College, Private",
			    "Educational: Community College, Public",
			    "Educational: Early Childhood Education/Daycare",
			    "Educational: K-12 School, Private",
			    "Educational: K-12 School, Public",
			    "Educational: University, Private",
			    "Educational: University, Public",
			    "Government Use: Federal",
			    "Government Use: Local, City",
			    "Government Use: Local, County",
			    "Government Use: Local, Public Housing Authority",
			    "Government Use: Other (utility, airport, etc.)",
			    "Government Use: State",
			    "Investor: Bank",
			    "Investor: Endowment",
			    "Investor: Equity Fund",
			    "Investor: Individual/Family",
			    "Investor: Insurance Company",
			    "Investor: Investment Manager",
			    "Investor: Pension Fund",
			    "Investor: REIT, Non-traded",
			    "Investor: REIT, Publicly traded",
			    "Investor: ROEC",
			    "Main Street Organization",
			    "Non-Profit (that do not fit into other categories)",
			    "Religious"
			    };
		
		OwnerType = (OwnerTypeOptions[new Random().nextInt(OwnerTypeOptions.length)]);
		
		data.setCellData(SheetName, "OwnerType", rownumber, OwnerType);
		
		String ProjectName = "MachineTestProject-City-None-" + Country;
		
		data.setCellData(SheetName, "ProjectName", rownumber, ProjectName);
		
        data.setCellData(SheetName, "Address", rownumber, "312 Wilson Streets");
		
		data.setCellData(SheetName, "OwnerOrg", rownumber, "B & B Associates, Inc.");
		
		data.setCellData(SheetName, "OwnerCountry", rownumber, Country);
		
		String City = Customdata.getCellData(CustomSheetName, "USCity", i);
		State = Customdata.getCellData(CustomSheetName, "State", i);
		ZipCode = Customdata.getCellData(CustomSheetName, "ZipCode", i);
	
		CommonMethod.res = given().log().all()
				.parameters(
						    "name", ProjectName,
						    "unitType", Unit,
						    "project_type", ProjectType,
						    "rating_system","none", 
						    "ownerType",OwnerType,
						    "organization","B & B Associates, Inc.",
						    "owner_email",data.getCellData(SheetName, "OwnerEmail", rownumber),
						    "manageEntityCountry",Country,
						    "gross_area", GrossArea,
						    "confidential", false,
						    "occupancy", "50", 
						    "operating_hours", "30",
						    "street", "312 Wilson Streets", 
						    "city",City, 
						    "country", Country, 
						    "state", State,
						    "zip_code",ZipCode,
						    "sign_agreement",true
						    )
				.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when().post("/assets/").then()
				.extract().response();
		
		System.out.println("This is " + Country + " " + "Project");

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.res.asString());

		System.out.println(CommonMethod.responsetime);

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		CommonMethod.res.then().assertThat().statusCode(201);
		
		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);

		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.fetchedID = CommonMethod.res.path("leed_id").toString();

		System.out.println(CommonMethod.fetchedID);

		Customdata.setCellData(CustomSheetName, "ProjectID", i, CommonMethod.fetchedID);

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");

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