package com.arcapi.Portfoliostestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class PortfoliosAssetDetailPUTAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void PortfoliosAssetDetailPUTAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		JSONObject jsonAsMap = new JSONObject();
		/*jsonAsMap.put("asset_name", "sample Asset 2");
		jsonAsMap.put("asset_country", "IN");
		jsonAsMap.put("asset_address", "Gurgaon");
		jsonAsMap.put("property_type", "RWB");*/
		
		jsonAsMap.put("asset_partner_id", data.getCellData(sheetName, ProjectTypeColumn, rowNumTwo));

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap).when().put("/portfolios/ID:" + data.getCellData(SheetName, "PortfolioID", rownumber)
						+ "/assets/" + data.getCellData(SheetName, "PortfolioPK", rownumber) + "/")
				.then().extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Portfolios AssetDetail PUT API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Portflios")
				.assignCategory("Portfolios");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		

		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);

	}


}