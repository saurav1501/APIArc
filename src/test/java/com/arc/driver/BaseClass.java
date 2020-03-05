package com.arc.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.Utill.LoginArc;
import com.Utill.RandomData;
import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class BaseClass {
	public static ExtentTest test;
	public static String response;
	public static XlsReader data;
	public static XlsReader Customdata;
	public static RequestSpecification reqSpec;
	public static RequestSpecification reqSpecLEED;
	public static RequestSpecification reqSpecjson;
	public static ResponseSpecification respSpec;
	public static String Token;
	public static String header;
	public static Properties prop;
	public static String SubscriptionKey = null;
	public static ExtentReports extent;
	public static String SuiteName;
	public static JSONObject jsonObject;
	public static int reportrownum = 2;
	public static String TestName = null;
	public static String GrossArea = "1000";
	public static String occupant = "3";
	public static String sheetName=null;
	public static int rowNumOne=1;
	public static int rowNumTwo=2;
	public static int rowNumThree=3;
	public static int rowNumFour=4;
	public static int rowNumFive=5;
	public static String username;
	public static String password;
	public static LoginArc loginArc = new LoginArc();
	public static RandomData randomData= new RandomData();
	public static Map<String,String> headerMap = new HashMap<String,String>();
	public static String modeValue1="1";
	public static String modeValue2="2";
	public static Logger log; 	
	public static String url;
    public static ArrayList<net.minidev.json.JSONObject> listpayload;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentTest testlog;
	public static List<ISuite> SuiteName1;
	public static String projectID;
	public static Object payload=null;
	public static HashMap<Object, Object> map;
	public static String statusMessage="HTTP/1.1 200 OK";
	public static String baseURL;
	public static String baseURLLEED;
	public static SoftAssert softAssert = new SoftAssert();
	
	public static File certification = new File(System.getProperty("user.dir")+"/src/main/resources/Certificate/certificate.html");
	public static File extentconfigfile = new File(System.getProperty("user.dir") +"/src/main/resources/extent-config1.xml");
	public static String[] value = { "-200", "ABC123", "200-094", "~!@#$%^&*()-_=+[];:\\\"<,>.?/", "200902", " ",
			"900-",
			"11111111111122222222223333333333334444444444444455555555557327328412734972346236413278462178467218347623746327864237846781289127847234627384673278432472384728378423742743248423742842364432784632746324623742471897421783689127368912731892731892371892371289371289371298471329847129837192378901231321371328743894638296431784182535123546127846893748137418932984",
			"weifwrefnijfwqnfiwqwuqwheuwhuefwhfhwefuiwfhwfquehwdhjewkhfkewhfjhewjkfnwejknfjndjkfnwehfwenfwjkefnwjkefhwejfnjwefnwejkfhewjkfnwejkfnewjfhwkjefnjkwnfjkwekfhwkjehfkwjenfkjwefnjwkjefhkewjfqwdqwyqywudhqweijdijewwkdfqwefdefiewhjikehjewkdfnioewfuhjqwiohdfkjqwedfnefejffekfqedsufewkejekwfnewvuhwuifirwgtrhiifwejkfdjdksdjfdsiufhweifhdasjkfhweufhweuifhweihfwjkefnfw",
			"n7r823yr8x8n32xr89y3r83ery3278n&@^*&#YNSUUUUUUUU&&@^W&@%@^$^#$@TDUEH@*Y#@N*(#@&*Y@*Y#*@(@*&#Y@WN@&*%^&&&&&&C@D$@%^E^&@R^R^!@$C^#@^ED^&&#ME@(#*(ME&*(#(*U*(E&*(#@NFNF@H*JJJE*(!@(#*(#(@*HD@&^&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*#D**#DH&HMM*HJXCJNMNB@MNVDNB#VD#VDC#FXDZD#XFQCDGC@G#VDU@G#VDY@DY@B#DJK@NDI@O#JD@*()@U()*#^T&*@GDY@GDHJDG@IDI@JI@IO*@(@&T&RDS^%@#F^RDCST",
			"723 328238 2382", "Ajkwdjjw fwfnwf jnwffcn" };
	public static String[] FuelTypeValue = { "kWh", "MWh", "MBtu", "kBtu", "GJ", "gal", "cf", "ccf", "kcf", "kGal",
			"MGal", "cu m", "l", "mcf", "gal(UK)", "kGal(UK)", "MGal(UK)", "cGal (UK)", "cGal (US)", "Kcm", "therms",
			"GJ", "US tons", "ppm", "ug/m3", "miles", "person", "days", "dollar"};
	public static String[] RatingSystemValue = { "LEED V4 O+M: EB WP", "LEED-CT", "LEED-CM", "PARKSMART",
	"LEED V4 O+M: TR" };


	
	@BeforeClass 
	@Parameters({"environment"})
	public void setBaseUri(String environment) throws IOException {

		data = new XlsReader(System.getProperty("user.dir") + "/src/main/resources/ARC-API-STG1.xlsx");
		Customdata = new XlsReader(System.getProperty("user.dir") + "/src/main/resources/CityScore-reference-set.xlsx");

		reqSpecjson = new RequestSpecBuilder().setBaseUri("https://5b927ev4hj.execute-api.us-west-1.amazonaws.com").build();
		prop = new Properties();

		FileInputStream file1 = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/stg.properties");
		FileInputStream file2 = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/qas.properties");
		
		FileInputStream file3 = new FileInputStream(
		System.getProperty("user.dir") + "/src/main/resources/dev.properties");

		FileInputStream file4 = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/dev2.properties");
		
		FileInputStream file5 = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/stg2.properties");
		
		FileInputStream file6 = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/qas2.properties");
		
		if(environment.equalsIgnoreCase("stg")) {

			prop.load(file1);
			baseURL = prop.getProperty("env");
			sheetName= prop.getProperty("sheetName");
			username=data.getCellData(sheetName, "NormalUserName", rowNumTwo);
			password=data.getCellData(sheetName, "NormalPassword", rowNumTwo);
			rowNumTwo= Integer.parseInt(prop.getProperty("rowNumTwo"));
			rowNumThree= Integer.parseInt(prop.getProperty("rowNumThree"));
			reqSpec = new RequestSpecBuilder().setBaseUri(baseURL).build();
			SubscriptionKey = "06c50a1570bd4d40a7859ec28514b185";
			baseURLLEED = prop.getProperty("envleed");
			reqSpecLEED = new RequestSpecBuilder().setBaseUri(baseURLLEED).build();

		}
		else if(environment.equalsIgnoreCase("dev")) {
			prop.load(file3);
			baseURL = prop.getProperty("env");
			sheetName= prop.getProperty("sheetName");
			username=data.getCellData(sheetName, "NormalUserName", rowNumTwo);
			password=data.getCellData(sheetName, "NormalPassword", rowNumTwo);
			rowNumTwo= Integer.parseInt(prop.getProperty("rowNumTwo"));
			rowNumThree= Integer.parseInt(prop.getProperty("rowNumThree"));
			reqSpec = new RequestSpecBuilder().setBaseUri(baseURL).build();
			SubscriptionKey = "450fc0393ae747638659258f5ed26485";
			baseURLLEED = prop.getProperty("envleed");
			reqSpecLEED = new RequestSpecBuilder().setBaseUri(baseURLLEED).build();

		}
		else if(environment.equalsIgnoreCase("dev2")) {
			prop.load(file4);
			baseURL = prop.getProperty("env");
			sheetName= prop.getProperty("sheetName");
			username=data.getCellData(sheetName, "NormalUserName", rowNumTwo);
			password=data.getCellData(sheetName, "NormalPassword", rowNumTwo);
			rowNumTwo= Integer.parseInt(prop.getProperty("rowNumTwo"));
			rowNumThree= Integer.parseInt(prop.getProperty("rowNumThree"));
			reqSpec = new RequestSpecBuilder().setBaseUri(baseURL).build();
			SubscriptionKey = "450fc0393ae747638659258f5ed26485";
			baseURLLEED = prop.getProperty("envleed");
			reqSpecLEED = new RequestSpecBuilder().setBaseUri(baseURLLEED).build();

		}
		
		else if(environment.equalsIgnoreCase("stg2")) {
			prop.load(file5);
			baseURL = prop.getProperty("env");
			sheetName= prop.getProperty("sheetName");
			username=data.getCellData(sheetName, "NormalUserName", rowNumTwo);
			password=data.getCellData(sheetName, "NormalPassword", rowNumTwo);
			rowNumTwo= Integer.parseInt(prop.getProperty("rowNumTwo"));
			rowNumThree= Integer.parseInt(prop.getProperty("rowNumThree"));
			reqSpec = new RequestSpecBuilder().setBaseUri(baseURL).build();
			SubscriptionKey = "06c50a1570bd4d40a7859ec28514b185";
			baseURLLEED = prop.getProperty("envleed");
			reqSpecLEED = new RequestSpecBuilder().setBaseUri(baseURLLEED).build();

		}
		else if(environment.equalsIgnoreCase("qas2")) {
			prop.load(file6);
			baseURL = prop.getProperty("env");
			sheetName= prop.getProperty("sheetName");
			username=data.getCellData(sheetName, "NormalUserName", rowNumTwo);
			password=data.getCellData(sheetName, "NormalPassword", rowNumTwo);
			rowNumTwo= Integer.parseInt(prop.getProperty("rowNumTwo"));
			rowNumThree= Integer.parseInt(prop.getProperty("rowNumThree"));
			reqSpec = new RequestSpecBuilder().setBaseUri(baseURL).build();
			SubscriptionKey = "5a41833022f046dc951383ead38d16d3";
			baseURLLEED = prop.getProperty("envleed");
			reqSpecLEED = new RequestSpecBuilder().setBaseUri(baseURLLEED).build();

		}
		else {
			prop.load(file2);
			baseURL = prop.getProperty("env");
			sheetName= prop.getProperty("sheetName");
			username=data.getCellData(sheetName, "NormalUserName", rowNumTwo);
			password=data.getCellData(sheetName, "NormalPassword", rowNumTwo);
			rowNumTwo= Integer.parseInt(prop.getProperty("rowNumTwo"));
			rowNumThree= Integer.parseInt(prop.getProperty("rowNumThree"));
			reqSpec = new RequestSpecBuilder().setBaseUri(baseURL).build();
			SubscriptionKey = "37fee8fbf7c84994a40df7346bf2f684";

			baseURLLEED = prop.getProperty("envleed");
			reqSpecLEED = new RequestSpecBuilder().setBaseUri(baseURLLEED).build();

		}

		loginArc.setUsername(username);
		loginArc.setPassword(password);
		headerMap.put("Ocp-Apim-Subscription-Key",SubscriptionKey);	
		Object className = this.getClass().getName();
		log = LogManager.getLogger((String) className);


	}

	@BeforeClass
	public static void getSuiteName(ITestContext context) {
		SuiteName =context.getCurrentXmlTest().getSuite().getName().toString();
		

	}

	@BeforeMethod
	public static ExtentReports ExtentReportConfig() {
		if (extent == null){
			htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Advance/ARC_API-AutomationReport_" +SuiteName + ".html");       	
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			htmlReporter.setAppendExisting(true);
			extent.setSystemInfo("Host Name","jenkins");
			extent.setSystemInfo("OS","Linux 64bit");
			extent.setSystemInfo("Environment", sheetName);
			extent.setSystemInfo("User Name", "Saurav Sinha");
			extent.setAnalysisStrategy(AnalysisStrategy.TEST);

			// class view:
			//extent.setAnalysisStrategy(AnalysisStrategy.CLASS);

			htmlReporter.config().setChartVisibilityOnOpen(false);
			htmlReporter.config().setDocumentTitle("AutomationTesting Report");
			htmlReporter.config().setReportName("ExtentReports");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.loadXMLConfig(extentconfigfile);	
		}
		return extent;
	}			

	@BeforeMethod
	public static void start(Method result) {	
		test = extent.createTest(result.getName()+"Test",SuiteName);	
		Object testName = result.getName(); 
		log.info("Executing test: " + testName);      
	}
	
	
	@AfterMethod
	public void teardown(ITestResult result) {
		test.getModel().setStartTime(getTime(result.getStartMillis()));
		test.getModel().setEndTime(getTime(result.getEndMillis())); 
		String[] group = result.getMethod().getGroups();
		test.assignCategory(group);
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable().toString().toLowerCase()+"Test"+ " : FAILED", ExtentColor.RED));

		} else if (result.getStatus() == ITestResult.SKIP) {

			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName().toString()+"Test" + " : SKIPPED",ExtentColor.ORANGE));


		} else {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName().toString()+"Test"+" : PASSED",ExtentColor.GREEN));

		}

	}	 
	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();      
	}
	@AfterClass
	public void end() {
		extent.flush();

	}

}
