package com.arcapi.TrailFlowtestcasesComplete;

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
import com.relevantcodes.extentreports.LogStatus;

import net.minidev.json.JSONObject;

public class SyncSAPTrailProjectAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
    @Parameters({ "SheetName","ProjectTypeColumn","rownumber", "Country"  })
	public void AssetUpdateforNewAssetPUTAPI(String SheetName,String ProjectTypeColumn, int rownumber,String Country) throws IOException {

		String SpaceType = null;
		String OwnerType = null;
		
		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode();
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		String[] SpaceTypeOptions = { "Airport: Control Tower",
			    "Airport: Distribution Center",
			    "Airport: Hangar",
			    "Airport: Office",
			    "Airport: Other",
			    "Airport: Public Order/Fire/Police",
			    "Airport: Rental Car Center",
			    "Airport: Terminal/Concourse",
			    "Airport: Vehicle Service/Repair",
			    "Circulation Space",
			    "Core Learning Space: College/University",
			    "Core Learning Space: K-12 Elementary/Middle School",
			    "Core Learning Space: K-12 High School",
			    "Core Learning Space: Other classroom education",
			    "Core Learning Space: Preschool/Daycare",
			    "Data Center",
			    "Healthcare: Clinic/Other Outpatient",
			    "Healthcare: Inpatient",
			    "Healthcare: Nursing Home/ Assisted Living",
			    "Healthcare: Outpatient Office (Diagnostic)",
			    "Industrial Manufacturing",
			    "Laboratory",
			    "Lodging: Dormitory",
			    "Lodging: Hotel/Motel/Resort, Full Service",
			    "Lodging: Hotel/Motel/Resort, Limited Service",
			    "Lodging: Hotel/Motel/Resort, Select Service",
			    "Lodging: Inn",
			    "Lodging: Other lodging",
			    "Multi-Family Residential: Apartment",
			    "Multi-Family Residential: Condominium",
			    "Multi-Family Residential: Lowrise",
			    "Office: Administrative/Professional",
			    "Office: Financial",
			    "Office: Government",
			    "Office: Medical (Non-Diagnostic)",
			    "Office: Mixed-Use",
			    "Office: Other Office",
			    "Public Assembly: Convention Center",
			    "Public Assembly: Entertainment",
			    "Public Assembly: Library",
			    "Public Assembly: Other Assembly",
			    "Public Assembly: Recreation",
			    "Public Assembly: Social/Meeting",
			    "Public Assembly: Stadium/Arena",
			    "Public Order and Safety: Fire/Police Station",
			    "Public Order and Safety: Other Public Order",
			    "Religious Worship",
			    "Retail: Bank Branch",
			    "Retail: Convenience Store",
			    "Retail: Enclosed Mall",
			    "Retail: Fast Food",
			    "Retail: Grocery Store/Food Market",
			    "Retail: Open Shopping Center",
			    "Retail: Other Retail",
			    "Retail: Restaurant/Cafeteria",
			    "Retail: Vehicle Dealership",
			    "Service: Other Service",
			    "Service: Post Office/Postal Center",
			    "Service: Repair Shop",
			    "Service: Vehicle Service/Repair",
			    "Service: Vehicle Storage/Maintenance",
			    "Single family home (attached)",
			    "Single family home (detached)",
			    "Transit: Depot",
			    "Transit: Line",
			    "Transit: Maintenance/Storage",
			    "Transit: Office",
			    "Transit: Other",
			    "Transit: Station",
			    "Transit: Station/Elevated",
			    "Transit: Station/Open Air Ground Level",
			    "Transit: Station/Underground",
			    "Transit: System",
			    "Warehouse: Nonrefrigerated Distribution/Shipping",
			    "Warehouse: Refrigerated",
			    "Warehouse: Self Storage Units",
			    "Warehouse: General",
			    "Other" };

		SpaceType = (SpaceTypeOptions[new Random().nextInt(SpaceTypeOptions.length)]);

		data.setCellData(SheetName, "SpaceType", rownumber, SpaceType);

		String[] OwnerTypeOptions = { "Business Improvement District",
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
			    "Investor: REIT,Non-traded",
			    "Investor: REIT, Publicly traded",
			    "Investor: ROEC",
			    "Main Street Organization",
			    "Non-Profit (that do not fit into other categories)",
			    "Religious" };

		OwnerType = (OwnerTypeOptions[new Random().nextInt(OwnerTypeOptions.length)]);

		data.setCellData(SheetName, "OwnerType", rownumber, OwnerType);

		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("spaceType", SpaceType);
		jsonAsMap.put("ownerType", OwnerType);
		jsonAsMap.put("owner_email", "ne@test.com");
		jsonAsMap.put("rating_system", "other");
		jsonAsMap.put("sign_agreement", "true");
		jsonAsMap.put("organization", "T");
		jsonAsMap.put("manageEntityCountry", Country);
		
	
		
		System.out.println(data.getCellData(SheetName, ProjectTypeColumn, rownumber));

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap).when().post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/syncsap/")
				.then().extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);


		CommonMethod.test = CommonMethod.extent
				.startTest("Asset Update for new Asset API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Update asset")
				.assignCategory("CheckAsset");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.fetchedID = CommonMethod.res.path("leed_id").toString();

		System.out.println(CommonMethod.fetchedID);

		data.setCellData(SheetName, ProjectTypeColumn, rownumber, CommonMethod.fetchedID);
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

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