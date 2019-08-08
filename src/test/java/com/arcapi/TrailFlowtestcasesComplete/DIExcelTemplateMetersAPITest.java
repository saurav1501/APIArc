package com.arcapi.TrailFlowtestcasesComplete;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

public class DIExcelTemplateMetersAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void DIExcelTemplateMetersAPI(String SheetName, String ProjectTypeColumn, int rownumber) throws IOException {

		String ColName = null;
		CommonMethod.ExtentReportConfig();

		// CommonMethod.GeneratingAuthCodeForLOUser(SheetName,rownumber);

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		TestName = Thread.currentThread().getStackTrace()[1].getMethodName();

		data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		CommonMethod.test = CommonMethod.extent
				.startTest("DIExcelTemplateMetersAPI Test",
						"Verifies Meter details")
				.assignCategory("CheckMeter");

		String meter[] = {"energy", "water", "co2", "voc"};

		for (String metername : meter) {

			CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
					.header("Authorization", header).spec(reqSpec).when().get("/assets/LEED:"
							+ data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/?kind=" + metername)
					.then().extract().response();
			
			if(metername.equalsIgnoreCase("energy")) {
				ColName = "EnergyMeterID";
			}
			
			else if(metername.equalsIgnoreCase("water")) {
				ColName = "WaterMeterID";
			}
			
			else if(metername.equalsIgnoreCase("co2")) {
				ColName = "WasteMeterID";
			}
			
			else if(metername.equalsIgnoreCase("voc")) {
				ColName = "HEMeterID";
			}
			

			System.out.println(CommonMethod.res.asString());

				CommonMethod.fetchedID = CommonMethod.res.path("results.id").toString();

				data.setCellData("Graphs", ColName, rownumber, CommonMethod.fetchedID);

				CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

				CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");

				CommonMethod.res.then().spec(respSpec);

			
			}
		}
	}
