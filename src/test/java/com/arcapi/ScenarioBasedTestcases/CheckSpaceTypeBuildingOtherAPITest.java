package com.arcapi.ScenarioBasedTestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Random;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class CheckSpaceTypeBuildingOtherAPITest extends BaseClass {

	@Test(groups ="Check SpaceType")
	@Parameters({ "SheetName", "ProjectType", "ProjectTypeColumn", "rownumber", "Country" })
	public void CreateAssetPOSTAPI(String SheetName, String ProjectType, String ProjectTypeColumn, int rownumber,
			String Country) throws IOException {

		
		String State = null;
		String Unit = null;
		String ZipCode = null;

		if (Country.equals("IN"))

		{
			String[] StateOptions = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
					"14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
					"30", "31", "32", "33", "34", "35", "TG" };

			State = (StateOptions[new Random().nextInt(StateOptions.length)]);

		
			ZipCode = "122018";
		}

		else if (Country.equals("US"))

		{
			String[] StateOptions = { "AL", "AK", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL", "GA", "GU",
					"HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT",
					"NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK", "OR", "PA", "PR", "RI", "SC",
					"SD", "TN", "TX", "UT", "VT", "VI", "VA", "WA", "WV", "WI", "WY" };

			State = (StateOptions[new Random().nextInt(StateOptions.length)]);

			ZipCode = "20037";

		}

		else if (Country.equals("CN"))

		{
			String[] StateOptions = { "110", "010", "320", "150", "260", "190", "200", "210", "220", "060", "090",
					"180", "160", "170", "100", "140", "080", "070", "040", "270", "280", "250", "120", "020", "050",
					"230", "030", "240", "290", "300", "240", "130" };

			State = (StateOptions[new Random().nextInt(StateOptions.length)]);


			ZipCode = "518000";
		}

		String[] UnitOptions = { "SI", "IP" };

		Unit = (UnitOptions[new Random().nextInt(UnitOptions.length)]);

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


		String ProjectName = "MachineTestProject-Building-Other-" + Country;

		for(String space : SpaceTypeOptions) {

		CommonMethod.res = given().log().all()
				.parameters("name", ProjectName, "rating_system", "other", "gross_area", "10000", "occupancy", "50",
						"street", "Sapphire club", "city", "Test City", "country", Country, "state", State,
						"project_type", ProjectType, "unitType", Unit, "spaceType", space, "owner_email",
						"stg-01@gmail.com", "ownerType", "Business Improvement District", "confidential", false, "sign_agreement", false,
						"zip_code", ZipCode, "organization", "T", "manageEntityCountry", Country)
				.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey).header("Authorization", header)
				.spec(reqSpec).when().post("/assets/").then().extract().response();

		System.out.println("This is " + Country + " " + "Project");

		System.out.println(CommonMethod.res.asString());

		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
		Assertion.verifyStatusCode(CommonMethod.res , 201);
	
	}
		
}}