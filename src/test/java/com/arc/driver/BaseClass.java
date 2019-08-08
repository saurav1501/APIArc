package com.arc.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.lang.reflect.Method;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.Utill.LoginArc;
import com.Utill.RandomData;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {

	public static XlsReader data;
	public static XlsReader Customdata;
	public static RequestSpecification reqSpec;
	public static RequestSpecification reqSpecjson;
	public static ResponseSpecification respSpec;
	public static String Token;
	public static String header;
	public Properties prop;
	public static String SubscriptionKey = null;
	public static ExtentReports extent;
	public static String SuiteName;
	public static JSONObject jsonObject;
	public static int reportrownum = 2;
	public static String TestName = null;
	public static String GrossArea = "100000";
	public static String occupant = "3";
	public static String sheetName=null;
	public static int rowNumOne=1;
    public static int rowNumTwo=2;
    public static int rowNumThree=3;
    public static int rowNumFour=4;
    public static int rowNumFive=5;
    public static String username;
    public static String password;
    public LoginArc loginArc = new LoginArc();
    public static RandomData randomData= new RandomData();
    public static Map<String,String> headerMap = new HashMap<String,String>();
    public static String modeValue1="1";
    public static String modeValue2="2";
	
	public static String[] value = { "-200", "ABC123", "200-094", "~!@#$%^&*()-_=+[];:\\\"<,>.?/", "200902", " ",
			"900-",
			"11111111111122222222223333333333334444444444444455555555557327328412734972346236413278462178467218347623746327864237846781289127847234627384673278432472384728378423742743248423742842364432784632746324623742471897421783689127368912731892731892371892371289371289371298471329847129837192378901231321371328743894638296431784182535123546127846893748137418932984",
			"weifwrefnijfwqnfiwqwuqwheuwhuefwhfhwefuiwfhwfquehwdhjewkhfkewhfjhewjkfnwejknfjndjkfnwehfwenfwjkefnwjkefhwejfnjwefnwejkfhewjkfnwejkfnewjfhwkjefnjkwnfjkwekfhwkjehfkwjenfkjwefnjwkjefhkewjfqwdqwyqywudhqweijdijewwkdfqwefdefiewhjikehjewkdfnioewfuhjqwiohdfkjqwedfnefejffekfqedsufewkejekwfnewvuhwuifirwgtrhiifwejkfdjdksdjfdsiufhweifhdasjkfhweufhweuifhweihfwjkefnfw",
			"n7r823yr8x8n32xr89y3r83ery3278n&@^*&#YNSUUUUUUUU&&@^W&@%@^$^#$@TDUEH@*Y#@N*(#@&*Y@*Y#*@(@*&#Y@WN@&*%^&&&&&&C@D$@%^E^&@R^R^!@$C^#@^ED^&&#ME@(#*(ME&*(#(*U*(E&*(#@NFNF@H*JJJE*(!@(#*(#(@*HD@&^&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*#D**#DH&HMM*HJXCJNMNB@MNVDNB#VD#VDC#FXDZD#XFQCDGC@G#VDU@G#VDY@DY@B#DJK@NDI@O#JD@*()@U()*#^T&*@GDY@GDHJDG@IDI@JI@IO*@(@&T&RDS^%@#F^RDCST",
			"723 328238 2382", "Ajkwdjjw fwfnwf jnwffcn" };

	public static String[] FuelTypeValue = { "kWh", "MWh", "MBtu", "kBtu", "GJ", "gal", "cf", "ccf", "kcf", "kGal",
			"MGal", "cu m", "l", "mcf", "gal(UK)", "kGal(UK)", "MGal(UK)", "cGal (UK)", "cGal (US)", "Kcm", "therms",
			"GJ", "tons", "ppm", "ug/m3", "miles", "person", "days", "dollar", "" };

	public static String[] RatingSystemValue = { "LEED V4 O+M: EB WP", "LEED-CT", "LEED-CM", "PARKSMART",
			"LEED V4 O+M: TR" };

	@BeforeClass 
	@Parameters({ "environment" })
	public void setBaseUri(String environment) throws IOException {

		data = new XlsReader(System.getProperty("user.dir") + "/src/main/resources/ARC-API-STG.xlsx");
		Customdata = new XlsReader(System.getProperty("user.dir") + "/src/main/resources/CityScore-reference-set.xlsx");

		reqSpecjson = new RequestSpecBuilder().setBaseUri("https://5b927ev4hj.execute-api.us-west-1.amazonaws.com")
				.build();
		respSpec = new ResponseSpecBuilder().expectStatusCode(200).build();

		prop = new Properties();
		/*FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/Environment.properties");

		prop.load(file);*/
		
		FileInputStream file1 = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/stg.properties");
		FileInputStream file2 = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/qas.properties");
		FileInputStream file3 = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/dev.properties");

		
		if(environment.equalsIgnoreCase("stg")) {
			prop.load(file1);
			String STG = prop.getProperty("env");
			sheetName= prop.getProperty("sheetName");
			username=data.getCellData(sheetName, "NormalUserName", rowNumTwo);
			password=data.getCellData(sheetName, "NormalPassword", rowNumTwo);
			rowNumOne= Integer.parseInt(prop.getProperty("rowNumOne"));
			rowNumTwo= Integer.parseInt(prop.getProperty("rowNumTwo"));
			rowNumThree= Integer.parseInt(prop.getProperty("rowNumThree"));
			rowNumFour= Integer.parseInt(prop.getProperty("rowNumFour"));
			rowNumFive= Integer.parseInt( prop.getProperty("rowNumFive"));
			reqSpec = new RequestSpecBuilder().setBaseUri(STG).build();
			SubscriptionKey = "06c50a1570bd4d40a7859ec28514b185";
			
			
		}
		else if(environment.equalsIgnoreCase("dev")) {
			prop.load(file3);
			String DEV = prop.getProperty("env");
			sheetName= prop.getProperty("sheetName");
			username=data.getCellData(sheetName, "NormalUserName", rowNumTwo);
			password=data.getCellData(sheetName, "NormalPassword", rowNumTwo);
			rowNumOne= Integer.parseInt(prop.getProperty("rowNumOne"));
			rowNumTwo= Integer.parseInt(prop.getProperty("rowNumTwo"));
			rowNumThree= Integer.parseInt(prop.getProperty("rowNumThree"));
			rowNumFour= Integer.parseInt(prop.getProperty("rowNumFour"));
			rowNumFive= Integer.parseInt( prop.getProperty("rowNumFive"));
			reqSpec = new RequestSpecBuilder().setBaseUri(DEV).build();
			SubscriptionKey = "450fc0393ae747638659258f5ed26485";
			
		}
		else {
			prop.load(file2);
			String QAS = prop.getProperty("env");
			sheetName= prop.getProperty("sheetName");
			username=data.getCellData(sheetName, "NormalUserName", rowNumTwo);
			password=data.getCellData(sheetName, "NormalPassword", rowNumTwo);
			rowNumOne= Integer.parseInt(prop.getProperty("rowNumOne"));
			rowNumTwo= Integer.parseInt(prop.getProperty("rowNumTwo"));
			rowNumThree= Integer.parseInt(prop.getProperty("rowNumThree"));
			rowNumFour= Integer.parseInt(prop.getProperty("rowNumFour"));
			rowNumFive= Integer.parseInt( prop.getProperty("rowNumFive"));
			reqSpec = new RequestSpecBuilder().setBaseUri(QAS).build();
			SubscriptionKey = "37fee8fbf7c84994a40df7346bf2f684";	
		}
		
	    loginArc.setUsername(username);
		loginArc.setPassword(password);
		headerMap.put("Ocp-Apim-Subscription-Key",SubscriptionKey);
		headerMap.put("content-type", "application/json");
		/*String SBX03 = prop.getProperty("ENV_SBX03");
		String DEV = prop.getProperty("ENV_DEV");
		String STG = prop.getProperty("ENV_STG");
		String QAS = prop.getProperty("ENV_QAS");
*/
		/*	if (environment.equalsIgnoreCase("sbx03")) {

			reqSpec = new RequestSpecBuilder().setBaseUri(SBX03).build();
			SubscriptionKey = "3b8ad5678ac8489084b2fcd7a2422a18";

		} else if (environment.equalsIgnoreCase("dev")) {
			reqSpec = new RequestSpecBuilder().setBaseUri(DEV).build();
			SubscriptionKey = "450fc0393ae747638659258f5ed26485";

		} else if (environment.equalsIgnoreCase("staging")) {
			reqSpec = new RequestSpecBuilder().setBaseUri(STG).build();
			SubscriptionKey = "06c50a1570bd4d40a7859ec28514b185";
		}

		else if (environment.equalsIgnoreCase("qas")) {
			reqSpec = new RequestSpecBuilder().setBaseUri(QAS).build();
			SubscriptionKey = "37fee8fbf7c84994a40df7346bf2f684";

		}
		*/
		

	}

	@BeforeClass
	public static void getSuiteName(ITestContext context) {
		SuiteName = context.getCurrentXmlTest().getSuite().getName();
	}

	@BeforeMethod
	public static void ExtentReportConfig() {
		try {

			/*
			 * Format formatter = new SimpleDateFormat("YYYY-MM-dd"); Date date = new
			 * Date();
			 */
			RestAssured.defaultParser = Parser.JSON;
			extent = new ExtentReports(
					System.getProperty("user.dir") + "/Advance/ARC_API-AutomationReport_" + SuiteName + ".html", false);
			// Locale.setDefault(Locale.ENGLISH);
			extent.loadConfig(new File(System.getProperty("user.dir") + "/src/main/resources/extent-config.xml"));
			Map<String, String> sysInfo = new HashMap<String, String>();
			sysInfo.put("Environment", "Staging");  
			extent.addSystemInfo(sysInfo);
						       
		} catch (Exception e) {
				e.printStackTrace();
		}

	}

	@BeforeMethod
	protected void startTest(Method method) throws Exception {
	    String testName = method.getName(); 
	    System.out.println("Executing test: " + testName);
	}
	
	@AfterMethod
	public void teardown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			CommonMethod.test.log(LogStatus.FAIL, result.getThrowable());
			//data.setCellData("Report", "Status", reportrownum, "Fail");
		} else if (result.getStatus() == ITestResult.SKIP) {
			CommonMethod.test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		} else {
			CommonMethod.test.log(LogStatus.PASS, "Test passed");
			//data.setCellData("Report", "Status", reportrownum, "Pass");
		}

		extent.endTest(CommonMethod.test);
	

	}
	
	@AfterClass
	public void end() {
		extent.flush();
		//reportrownum++;

	}

}
