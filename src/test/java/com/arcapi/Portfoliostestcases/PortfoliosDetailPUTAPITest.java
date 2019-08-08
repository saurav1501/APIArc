package com.arcapi.Portfoliostestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.RandomData;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class PortfoliosDetailPUTAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","rownumber" })
	public void PortfoliosDetailPUTAPI(String SheetName, int rownumber) throws IOException {

		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("name", RandomData.protfolioName());
		jsonAsMap.put("organization", "USGBCARC");
		jsonAsMap.put("organization_contact", "1234567890");
		jsonAsMap.put("organization_country", "US");

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap).when()
				.put("/portfolios/ID:" + data.getCellData(SheetName, "PortfolioID", rownumber) + "/").then().extract()
				.response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);


		CommonMethod.test = CommonMethod.extent
				.startTest("Portfolios Detail PUT API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Portflios")
				.assignCategory("Portfolios");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		
		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);

	}

}