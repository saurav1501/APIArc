package com.arcapi.Buildingtestcases;

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

public class CreateAssetTransitAboveEmissionFactorTest extends BaseClass {

	@Test
	@Parameters({ "SheetName","CustomSheetName","ProjectType","ProjectTypeColumn","rownumber"})
	public void CreateAssetPOSTAPI(String SheetName,String CustomSheetName, String ProjectType, String ProjectTypeColumn, int rownumber) throws IOException {

		//CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCode(SheetName,rownumber);

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		
		String Unit = null;
		String SpaceType = null;
		String OwnerType  = null;
		

		String[] UnitOptions  = {"SI","IP"};
		
		Unit = (UnitOptions[new Random().nextInt(UnitOptions.length)]);
	
		
        String[] SpaceTypeOptions  = { "Airport: Control Tower",
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
		
		
		String ProjectName = "MachineTestProject-TransitAbove-US";
		
		CommonMethod.res = given().log().all()
				.parameters("name", ProjectName,
					    "unitType", Unit,
					    "project_type", ProjectType,
					    "rating_system","LEED V4 O+M: TR", 
					    "sign_agreement", true,
					    "occupancy", "50",
					    "spaceType",SpaceType,
					    "station_type", "aboveground",
					    "annual_ridership", "365",
					    "operating_hours", "14",
					    "full_time_staff", "7",
					    "time_spent_by_riders", "120",
					    "ownerType",OwnerType,
					    "organization","A & A Dealers",
					    "owner_email",data.getCellData(SheetName, "NormalUserName", rownumber),
					    "manageEntityCountry","US",
					    "gross_area", GrossArea,
					    "confidential", false, 
					    "street", "1 mount road", 
					    "city","Test City", 
					    "country", "US", 
					    "state", "NC",
					    "zip_code","20037")
				.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when().post("/assets/").then()
				.extract().response();
	
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.res.asString());

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Create New Building Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Add asset")
				.assignCategory("CheckAsset");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);
		
		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);

		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		
		CommonMethod.res.then().assertThat().statusCode(201);

		CommonMethod.fetchedID = CommonMethod.res.path("leed_id").toString();

		System.out.println(CommonMethod.fetchedID);

		data.setCellData(CustomSheetName, ProjectTypeColumn, rownumber, CommonMethod.fetchedID);

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

		extent.endTest(CommonMethod.test);
		extent.flush();

	}

}