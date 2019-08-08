package com.arcapi.Leedv4Testcases;

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

import net.minidev.json.JSONObject;

public class LOProjectRegistrationINT extends BaseClass {

	@Test
    @Parameters({ "LOSheetName","rownumber","Country" })
	public void CreateAssetPOSTAPI(String SheetName,int rownumber, String Country) throws IOException {
		

		CommonMethod.ExtentReportConfig();
		
		
		String State = null;
		String Unit = null;
		String AnticipitatedType = null;
		String OwnerType  = null;
		String ZipCode = null;

		//CommonMethod.GeneratingAuthCode(SheetName,rownumber);
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		
		
        if(Country.equals("IN"))
			
		{
			String[] StateOptions  = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17",
					"18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","TG"};
			
			State = (StateOptions[new Random().nextInt(StateOptions.length)]);
			
			data.setCellData(SheetName, "Country", rownumber, Country);
			
			data.setCellData(SheetName, "State", rownumber, State);
			
			ZipCode = "122018";
			
			data.setCellData(SheetName, "ZipCode", rownumber, ZipCode);
		}
		
        else if(Country.equals("US"))
			
		{
			String[] StateOptions  = {"AL","AK","AS","AZ","AR","CA","CO","CT","DE","DC","FL","GA","GU","HI","ID","IL","IN","IA",
					"KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","MP",
					"OH","OK","OR","PA","PR","RI","SC","SD","TN","TX","UT","VT","VI","VA","WA","WV","WI","WY"};
			
			State = (StateOptions[new Random().nextInt(StateOptions.length)]);
			
			data.setCellData(SheetName, "Country", rownumber, Country);
			
			data.setCellData(SheetName, "State", rownumber, State);
			
            ZipCode = "20037";
			
			data.setCellData(SheetName, "ZipCode", rownumber, ZipCode);
		}
		
        else if(Country.equals("CN"))
			
		{
			String[] StateOptions  = {"110","010","320","150","260","190","200","210","220","060","090","180","160","170","100","140","080",
					"070","040","270","280","250","120","020","050","230","030","240","290","300","240","130"};
			
			State = (StateOptions[new Random().nextInt(StateOptions.length)]);
			
			data.setCellData(SheetName, "Country", rownumber, Country);
			
			data.setCellData(SheetName, "State", rownumber, State);
			
            ZipCode = "518000";
			
			data.setCellData(SheetName, "ZipCode", rownumber, ZipCode);
		}
		
		
		String[] UnitOptions  = {"SI","IP"};
		
		Unit = (UnitOptions[new Random().nextInt(UnitOptions.length)]);
		
		data.setCellData(SheetName, "Unit", rownumber, Unit);
		
        String[] AnticipitatedTypeOptions  = {"Airport: Control Tower","Airport: Distribution Center","Airport: Hangar","Airport: Office","Airport: Other","Airport: Public Order/Fire/Police","Airport: Rental Car Center","Airport: Terminal/Concourse","Airport: Vehicle Service/Repair","Circulation Space","Core Learning Space: College/University","Core Learning Space: K-12 Elementary/Middle School","Core Learning Space: K-12 High School","Core Learning Space: Other classroom education","Core Learning Space: Preschool/Daycare","Data Center","Healthcare: Clinic/Other Outpatient","Healthcare: Inpatient","Healthcare: Nursing Home/ Assisted Living",
				"Healthcare: Outpatient Office (Diagnostic)","Industrial Manufacturing","Laboratory","Lodging: Dormitory","Lodging: Hotel/Motel/Resort, Full Service","Lodging: Hotel/Motel/Resort, Limited Service","Lodging: Hotel/Motel/Resort, Select Service","Lodging: Inn","Lodging: Other lodging","Multi-Family Residential: Apartment","Multi-Family Residential: Condominium","Multi-Family Residential: Lowrise","Office: Administrative/Professional","Office: Financial","Office: Government",
				"Office: Medical (Non-Diagnostic)","Office: Mixed-Use","Office: Other Office","Public Assembly: Convention Center","Public Assembly: Entertainment","Public Assembly: Library","Public Assembly: Other Assembly","Public Assembly: Recreation","Public Assembly: Social/Meeting","Public Assembly: Stadium/Arena","Public Order and Safety: Fire/Police Station","Public Order and Safety: Other Public Order","Religious Worship","Retail: Bank Branch","Retail: Convenience Store","Retail: Enclosed Mall","Retail: Fast Food","Retail: Grocery Store/Food Market","Retail: Open Shopping Center",
				"Retail: Other Retail","Retail: Restaurant/Cafeteria","Retail: Vehicle Dealership","Service: Other Service","Service: Post Office/Postal Center","Service: Repair Shop","Service: Vehicle Service/Repair","Service: Vehicle Storage/Maintenance","Single family home (attached)","Single family home (detached)","Transit: Depot","Transit: Line","Transit: Maintenance/Storage","Transit: Office","Transit: Other",
				"Transit: Station","Transit: Station/Elevated","Transit: Station/Open Air Ground Level","Transit: Station/Underground","Transit: System","Warehouse: General","Warehouse: Nonrefrigerated Distribution/Shipping","Warehouse: Refrigerated","Warehouse: Self Storage Units","Other"};
		
        AnticipitatedType = (AnticipitatedTypeOptions[new Random().nextInt(AnticipitatedTypeOptions.length)]);
		
		data.setCellData(SheetName, "AnticipitatedType", rownumber, AnticipitatedType);
		
		
		String[] OwnerTypeOptions  = {"Business Improvement District","Community Development Corporation or Non-profit developer","Corporate: Privately Held","Corporate: Publicly Traded","Educational: College, Private","Educational: College, Public","Educational: Community College, Private","Educational: Community College, Public","Educational: Early Childhood Education/Daycare","Educational: K-12 School, Private","Educational: K-12 School, Public","Educational: University, Private","Educational: University, public","Government Use: Federal","Government Use: Local, City","Government Use: Local, Public Housing Authority","Government Use: Other (utility, airport, etc.)",
				"Government Use: State","Investor: Bank","Investor: Endowment","Investor: Equity Fund","Investor: Individual/Family","Investor: Insurance Company","Investor: Investment Manager","Investor: Pension Fund","Investor: REIT, Non-traded","Investor: REIT, Publicly traded","Investor: ROEC","Main Street Organization","Non-Profit (that do not fit into other categories)","Religious"};
		
		OwnerType = (OwnerTypeOptions[new Random().nextInt(OwnerTypeOptions.length)]);
		
		data.setCellData(SheetName, "OwnerType", rownumber, OwnerType);
		
		String ProjectName = "Test LeedOnline O+M:Int Project " + Country;
		
		data.setCellData(SheetName, "ProjectName", rownumber, ProjectName);
		
		JSONObject jsonAsMap = new JSONObject();			
		jsonAsMap.put("cmd", "register");
		jsonAsMap.put("name", ProjectName);
		jsonAsMap.put("description", "Register from LO API");
		jsonAsMap.put("ratingSystem", "v4_1.oInt");
		jsonAsMap.put("anticipatedType", AnticipitatedType);
		jsonAsMap.put("unitType", Unit);
		jsonAsMap.put("grossFootage", GrossArea);
		jsonAsMap.put("date.start", "2018-06-01");
		jsonAsMap.put("date.end", "2028-07-01");
		jsonAsMap.put("address.address1", "Test Address");
		jsonAsMap.put("address.city", "Test City");
		jsonAsMap.put("address.country", Country);
		jsonAsMap.put("address.state", State);
		jsonAsMap.put("address.postalCode", ZipCode);
		jsonAsMap.put("address.geoPoints.latitude", "120.12345678");
		jsonAsMap.put("address.geoPoints.longitude", "10.46467895");
		jsonAsMap.put("owner.name", "Test Owner");
		jsonAsMap.put("owner.email", "newserver2@gmail.com");
		jsonAsMap.put("owner.organization", "N1K4QrwEeVVW");
		jsonAsMap.put("owner.country", "IN");
		jsonAsMap.put("owner.type", OwnerType);
	
        	        
		CommonMethod.res = given().log().all()
				.header("Content-type", "application/json")
				.header("Authorization", header)
				.header("X-Caller-Id", "20297672fa1247ccf00ce8e0a14013ac")
				.body(jsonAsMap)
				.when()
				.post("https://leedonline-api-qas.usgbc.org/v1/json/Project/register/INP")
				.then()
				.extract()
				.response();	
		

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
		
		CommonMethod.res.then().assertThat().statusCode(200);

		CommonMethod.fetchedID = CommonMethod.res.path("registered.id").toString();

		System.out.println(CommonMethod.fetchedID);

		data.setCellData(SheetName, "LeedIDINT", rownumber, CommonMethod.fetchedID);

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");

	}

}