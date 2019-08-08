package com.arcapi.Portfoliostestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class PortfoliosAssetPostAPITest extends BaseClass {

	@Test(priority=1)
	public void PortfoliosAssetPostAPI() throws IOException {

		JSONObject jsonAsMap = new JSONObject();
		
		String projectID1 = data.getCellData(sheetName, "ProjectIDBuildingNone", rowNumTwo);
		
		jsonAsMap.put("asset_partner_id", projectID1);
		
		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap).when()
				.post("/portfolios/ID:" +  data.getCellData(sheetName, "PortfolioID", rowNumTwo) + "/assets/").then()
				.extract().response();
	
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Portfolios Asset Post API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Portfolios")
				.assignCategory("Portfolios");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());

		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().assertThat().statusCode(200);
		
		CommonMethod.fetchedID = CommonMethod.res.path("id").toString();
		CommonMethod.fetchedID = CommonMethod.fetchedID.replaceAll("\\[", "").replaceAll("\\]", "");
		data.setCellData(sheetName, "PortfolioPK", rowNumTwo, CommonMethod.fetchedID);
		

	
	}
   
	@Test(priority=2)
	public void CreatePortfoliosAsset2PostAPI() throws IOException {
		
		String projectID2 = data.getCellData(sheetName, "ProjectIDBuildingNone", rowNumThree);
		System.out.println(projectID2);
		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("asset_partner_id", projectID2);
		
		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap).when()
				.post("/portfolios/ID:" +  data.getCellData(sheetName, "PortfolioID", rowNumTwo) + "/assets/").then()
				.extract().response();

		CommonMethod.res.then().assertThat().statusCode(200);

		
	}
}