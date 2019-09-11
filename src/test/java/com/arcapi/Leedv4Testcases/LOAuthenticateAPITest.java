package com.arcapi.Leedv4Testcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class LOAuthenticateAPITest extends BaseClass {

	@Test
	@Parameters({ "LOSheetName","rownumber"})
	public void LOAuthenticateAPI(String SheetName,int rownumber) throws IOException {

		data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		String UserName = data.getCellData(SheetName, "LODEVUSER", rownumber);
		String Password = data.getCellData(SheetName, "LODEVPASS", rownumber);
		
		CommonMethod.res = given().log().all()
				.header("Content-Type","application/x-www-form-urlencoded")
				.params(
						"username", UserName,
						"password", Password,
						"guid", "").spec(reqSpecLEED)
				.when()
				.post("authenticate")
				.then()
				.extract()
				.response();
		

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.res.asString());

		System.out.println(CommonMethod.responsetime);

	
		
		CommonMethod.fetchedID = CommonMethod.res.path("token").toString();

		System.out.println(CommonMethod.fetchedID);

		header = "Basic " + CommonMethod.fetchedID;
		System.out.println(header);


		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);

		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");

	}

	

}