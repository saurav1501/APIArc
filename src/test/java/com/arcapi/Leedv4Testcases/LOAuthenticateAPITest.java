package com.arcapi.Leedv4Testcases;

import static com.jayway.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class LOAuthenticateAPITest extends BaseClass {

	@Test(groups="CheckLogin")
	@Parameters({"SheetName","rownumber"})
	public void LOAuthenticateAPI(String SheetName,int rownumber){
		try {
			String UserName = data.getCellData("LEEDONLINE", "LODEVUSER", rownumber);
			String Password = data.getCellData("LEEDONLINE", "LODEVPASS", rownumber);
			CommonMethod.res = given().log().all().header("Content-Type","application/x-www-form-urlencoded").params("username", UserName,"password", Password,"guid", "").spec(reqSpecLEED).when().post("authenticate").then().extract().response();
			CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);	
			CommonMethod.fetchedID = CommonMethod.res.path("token").toString();
			header = "Basic " + CommonMethod.fetchedID;
			CommonMethod.testlog("Pass","userName : " +UserName+ "Password : "+Password);
			CommonMethod.testlog("Pass","Authorization Token generated" + "<br>" + header);
			CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
			CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
			CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

}