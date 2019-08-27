package com.arcapi.Buildingtestcase;
import static com.jayway.restassured.RestAssured.given;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.Utill.CreateMeter;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

 public class CarbonDioxideCreateMeterConsumptionTest extends BaseClass {
	      
	 
	      @Test(groups="CheckMeter")
		  public void CarbonDioxideCreateMeterConsumption() throws IOException {

			String projectType = data.getCellData(sheetName, "ProjectIDBuildingNone",rowNumTwo);
			      
     		CreateMeter meterData = new CreateMeter();
			
     		meterData.setName("Default CO2 Meter");
			meterData.setNative_unit("ppm");
			meterData.setType("206");
			meterData.setIncluded(true);

			CommonMethod.res = given().log().all().headers(headerMap).header("Authorization", header).spec(reqSpec)
					.body(meterData).when().post("/assets/LEED:"+projectType+"/meters/").then()
					.extract().response();
			
			CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

			System.out.println(CommonMethod.responsetime);

			System.out.println(CommonMethod.res.asString());
		
			CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

			CommonMethod.fetchedID = CommonMethod.res.path("id").toString();

			data.setCellData(sheetName, "MeterID", rowNumTwo, CommonMethod.fetchedID);

			CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
			
			CommonMethod.res.then().assertThat().statusCode(201);

			
			
		}		
			
}

