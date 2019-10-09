package com.arc.driver;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.aventstack.extentreports.Status;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class CommonMethod extends BaseClass {
	
	public static long responsetime;
	public static String ZipCode = "20037";
	public static Response res;
	public static String fetchedID;
	public static String ProgramID;
    public static File formuploadfile = new File(System.getProperty("user.dir") + "/src/main/resources/Creditfile.pdf");
	public static File excelfile = new File(System.getProperty("user.dir") + "/src/main/resources/Arc_Data_Template_Underground.xlsm");
	public static File oldexcelfile = new File(System.getProperty("user.dir") + "/src/main/resources/OLDArc_Data_Template.xlsm");
	public static File v4excelfile = new File(System.getProperty("user.dir") + "/src/main/resources/Arc_Data_Template_building_v4.1.xlsm");

	public static File excelfileTransitAbove = new File(System.getProperty("user.dir") + "/src/main/resources/Arc_Data_Template_transit_above.xlsm");
	public static File file = new File(System.getProperty("user.dir") + "/src/main/resources/Certification_Agreement.htm");
	public static File Jsonfile =  new File(System.getProperty("user.dir") + "/src/main/resources/AdditionalData.json");
	public static String ResponseJsonfile =  System.getProperty("user.dir") + "/src/main/resources/Response.json";
	
	public static File gresb = new File(System.getProperty("user.dir") + "/src/main/resources/Gresb.xlsx");

	public static void GeneratingAuthCode(String SheetName, int rownumber) {
		
		String UserName = data.getCellData(SheetName, "NormalUserName", rownumber);
		String Password = data.getCellData(SheetName, "NormalPassword", rownumber);
		
		Token = given().log().all() 
				.header("Ocp-Apim-Subscription-Key",
						CommonMethod.SubscriptionKey)
				.spec(reqSpec)
				.parameters("username", UserName, "password",
						Password).expect().statusCode(200).when()
				.post("/auth/login/").then().contentType(ContentType.JSON)
				.extract().response().path("authorization_token").toString();
		
		header = "Bearer " + Token;
			
		
	}
	
public static void GeneratingAuthCodeForLOUser(String SheetName, int rownumber) {
		
	String UserName = data.getCellData(SheetName, "NormalUserName", rownumber);
	String Password = data.getCellData(SheetName, "NormalUserName", rownumber);

		Token = given().log().all()
				.header("Ocp-Apim-Subscription-Key",
						CommonMethod.SubscriptionKey)
				.spec(reqSpec)
				.parameters("username", UserName, "password",
						Password).expect().statusCode(200).when()
				.post("/auth/login/").then().contentType(ContentType.JSON)
				.extract().response().path("authorization_token").toString();
		
		header = "Bearer " + Token;
		
		
	}
	
	public static void GeneratingAuthCodeAdminUser(String SheetName, int rownumber) {

		String UserName = data.getCellData(SheetName, "AdminUserName", rownumber);
		String Password = data.getCellData(SheetName, "AdminPassword", rownumber);
		Token = given().log().all()
				.header("Ocp-Apim-Subscription-Key",
						SubscriptionKey)
				.spec(reqSpec)
				.parameters("username", UserName, "password",
						Password).when()
				.post("/auth/login/").then()
				.extract().response().path("authorization_token").toString();
		
		header = "Bearer " + Token;
		
		
	}
	
	public static void GeneratingAuthCodesAdminUser() {

		Token = given()
				.header("Ocp-Apim-Subscription-Key",
						SubscriptionKey)
				.spec(reqSpec)
				.parameters("username", "qasusgbcadmin@usgbc.com", "password",
						"initpass").expect().statusCode(200).when()
				.post("/auth/login/").then().contentType(ContentType.JSON)
				.extract().response().path("authorization_token").toString();
		
		header = "Bearer " + Token;
			
		
	}
	
	public static String getLabel(long responsetime) {
		
		if (responsetime <= 4000){
			
	    return "<span class='label outline info'>" + CommonMethod.responsetime + " Milliseconds" + "</span>";
		}
		
		else
		{
		return "<span class='label outline fatal'>" + CommonMethod.responsetime + " Milliseconds" + "</span>";
		}
	    
	    
	}
	
	public static void fetchingJSONResponse(String Url) {
        testlog("Pass", "Authorization Token generated"+ "<br>" +header);
		Response res = given()
				.header("Ocp-Apim-Subscription-Key",
						SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).expect().statusCode(200).when().get(Url).then()
				.extract().response();

		System.out.println(res.asString());
		testlog("Pass", "Response received from API"+ "<br>" +res.asString());

	}
	
	public static void responsetimevalidation(String Url) {
		given().header("Ocp-Apim-Subscription-Key",
				SubscriptionKey)
				.header("Authorization", CommonMethod.header).spec(reqSpec).when()
				.get(Url).then().spec(respSpec)
				.and().time(lessThan(50000L));
	}
	
	public static void filewriteID(String url) throws IOException{
		
		File file = new File(url);

		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(fetchedID);
		bw.close();
	}
	
public static void filewriteResponse(String url, String response) throws IOException{
		
		File file = new File(url);

		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(response);
		bw.close();
	}
	
	public static void trimLeedID(){
		
        System.out.println(res.asString());
		String responseBody = res.getBody().asString();
		System.out.println(responseBody);
		fetchedID = responseBody.trim().substring(274, 284);
		System.out.println(fetchedID);
	}
	
	public static void trimMeterID(){
			
	        System.out.println(res.asString());
			String responseBody = res.getBody().asString();
			System.out.println(responseBody);
			fetchedID = responseBody.trim().substring(6, 10);
			System.out.println(fetchedID);
		}

private static String modifyDateLayout(String inputDate) throws ParseException{
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(inputDate);
    return new SimpleDateFormat("dd-MM-yyyy").format(date);
}


public static String randomNumber() throws IOException, InterruptedException{
	
	 int random_num = 1;
	    Random t = new Random();
	    // random integers in [1000, 800000]
	    random_num=	(t.nextInt(800000));
	    ProgramID = String.valueOf(random_num);		    
	    System.out.println(ProgramID);
		Thread.sleep(1000);
		return ProgramID;
		
}

public static String randomNumberMeterReading() throws IOException, InterruptedException{
	
	 int random_num = 1;
	    Random t = new Random();
	    // random integers in [1000, 800000]
	    random_num=	(t.nextInt(200));
	    ProgramID = String.valueOf(random_num);		    
	    System.out.println(ProgramID);
		Thread.sleep(1000);
		return ProgramID;
		
}
	public static void testlog(String log, String message){
		System.out.println(test);
		switch(log){
		
		case "Pass":
			test.log(Status.PASS, message);
			break;
			
		case "Info":
			test.log(Status.INFO, message);
			break;
			
		 default:
	     	
	     	System.out.println("Not Valid Input");
		}
		
	}
		
	
		
	public static String filereadID(String url) throws IOException{
		
		 FileReader inputFile = new FileReader(url);
		
	     //Instantiate the BufferedReader Class
	     BufferedReader bufferReader = new BufferedReader(inputFile);

	     //Variable to hold the one line data
	     
	     String text;
	     // Read file line by line and print on the console
	     while ((text = bufferReader.readLine()) != null)   {
	        
	          fetchedID=text;
	      	}
	        
	        bufferReader.close();
	        return fetchedID;
	}
	
}

