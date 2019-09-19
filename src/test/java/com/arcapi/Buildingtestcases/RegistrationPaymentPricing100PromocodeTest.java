package com.arcapi.Buildingtestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class RegistrationPaymentPricing100PromocodeTest extends BaseClass {

	@Test//(dependsOnMethods={"com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI"})
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber","ProjectTypePromocodeColumn" })
	public void RegistrationPaymentPricingGetAPI(String SheetName,String ProjectTypeColumn, int rownumber, String ProjectTypePromocodeColumn) throws IOException {

		
		String finalPrice;
				
		CommonMethod.res = given().log().all()
				.header("Ocp-Apim-Subscription-Key",
						CommonMethod.SubscriptionKey)
				.header("Authorization", CommonMethod.header)
				.spec(reqSpec)
				.when()
				.get("/assets/LEED:"
						+ data.getCellData(SheetName, ProjectTypeColumn, rownumber)
						+ "/payments/price/?soreference=registration&promocode="+data.getCellData("100PercentPromocode", ProjectTypePromocodeColumn, 2)).then().extract()
				.response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		
		
		
		System.out.println(CommonMethod.res.asString());
		CommonMethod.fetchingJSONResponse("/assets/LEED:"
				+ data.getCellData(SheetName, ProjectTypeColumn, rownumber)
				+ "/payments/price/?soreference=registration&promocode="+data.getCellData("100PercentPromocode", ProjectTypePromocodeColumn, 2));
		
		
		CommonMethod.fetchedID = CommonMethod.res.path("price.price_per_unit[0]");
		CommonMethod.fetchedID = CommonMethod.fetchedID.replaceAll("\\[", "").replaceAll("\\]", "");
		System.out.println(CommonMethod.fetchedID);
		
		data.setCellData("100PercentPromocode", ProjectTypePromocodeColumn, 5 , CommonMethod.fetchedID);
		
		//Double priceunit = Double.parseDouble(CommonMethod.fetchedID);
		
		finalPrice = CommonMethod.res.path("price.price[0]");
		finalPrice = finalPrice.replaceAll("\\[", "").replaceAll("\\]", "");
		System.out.println(finalPrice);
		
		data.setCellData("100PercentPromocode", ProjectTypePromocodeColumn, 7 , finalPrice);
		
		Double actualPrice = Double.parseDouble(finalPrice);
		
		Double exp_price = 0.0;
		
		Assert.assertEquals(actualPrice, exp_price);
		
		data.setCellData("100PercentPromocode", ProjectTypePromocodeColumn, 9 , "PASS");
		
	
		CommonMethod.testlog("Info", "API responded in "
				+ CommonMethod.responsetime + " Milliseconds");
	}

	

	
}