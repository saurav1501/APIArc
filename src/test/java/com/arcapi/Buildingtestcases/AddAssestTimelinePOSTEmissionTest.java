package com.arcapi.Buildingtestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class AddAssestTimelinePOSTEmissionTest extends BaseClass {

	@Test(groups="CheckAsset")
	@Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void AddAssestTimeline(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		
		CommonMethod.res = given().log().all()
				.header("Ocp-Apim-Subscription-Key",
						CommonMethod.SubscriptionKey)
				.header("Authorization", header)
				.spec(reqSpec)
				.when()
				.post("/assets/LEED:"
						+ data.getCellData(CustomSheetName, ProjectTypeColumn, rownumber)
						+ "/timeline/").then().contentType(ContentType.JSON)
				.extract().response();
		
	    log.info(CommonMethod.responsetime);
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
	
		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);
		
		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "verifies response from API" + "<br>"
				+ CommonMethod.res.asString());
		
		CommonMethod.testlog("Info", "API responded in "
				+ CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
	}

	

}