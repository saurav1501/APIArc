package com.arcapi.Portfoliostestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class PortfoliosAnalyticsCategoryDetailAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void PortfoliosAnalyticsCategoryDetailAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		JSONObject jsonAsMap = new JSONObject();
		
		jsonAsMap.put("asset_partner_id", data.getCellData(SheetName, ProjectTypeColumn, rownumber));
		

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap).when()
				.get("/portfolios/ID:" +  data.getCellData(SheetName, "PortfolioID", rownumber) + "/analytics/energy/").then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);
		
		CommonMethod.test = CommonMethod.extent
				.startTest("PortfoliosAnalyticsCategoryDetail API Test" + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Portfolios")
				.assignCategory("Portfolios");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().assertThat().statusCode(200);

	
	}


}