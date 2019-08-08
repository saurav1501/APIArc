package com.arcapi.Portfoliostestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class PortfoliosAnalyticsDetailAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void PortfoliosAnalyticsDetailAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		JSONObject jsonAsMap = new JSONObject();
		
		jsonAsMap.put("asset_partner_id", data.getCellData(sheetName, ProjectTypeColumn, rownumber));
		

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap).when()
				.get("/portfolios/ID:" +  data.getCellData(SheetName, "PortfolioID", rownumber) + "/analytics/").then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);


		CommonMethod.test = CommonMethod.extent
				.startTest("PortfoliosAnalyticsDetail API Test" + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Portfolios")
				.assignCategory("Portfolios");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		 String jsonAsString = CommonMethod.res.asString();
		 System.out.println(jsonAsString);
		 
		 List<String> data = CommonMethod.res.path("reading");
		 System.out.println(data.toString());

		    for (String readingData : data)
		    {
		    
		    	System.out.println(readingData.toString());
		    	String dataReading = "100";
				Assert.assertEquals(readingData, dataReading);
		   
		    }
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().assertThat().statusCode(200);

		


	}


}