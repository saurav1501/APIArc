package com.arcapi.CompleteDataValidationTestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;
import com.relevantcodes.extentreports.LogStatus;

import net.minidev.json.JSONObject;

public class AssetUpdateforNewAssetPUTAPITest extends BaseClass {

	@Test // (dependsOnMethods = {
			// "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void AssetUpdateforNewAssetPUTAPI(String SheetName, String ProjectTypeColumn, int rownumber)
			throws IOException {

		CommonMethod.ExtentReportConfig();

		// CommonMethod.GeneratingAuthCode();

		CommonMethod.test = CommonMethod.extent
				.startTest("Asset Update for new Asset API Test  ", "Verifies Update asset")
				.assignCategory("CheckAsset");

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		for (String str : value) {

			JSONObject jsonAsMap = new JSONObject();
			jsonAsMap.put("name", str);

			System.out.println(data.getCellData(SheetName, ProjectTypeColumn, rownumber));

			CommonMethod.res = given().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
					.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
					.body(jsonAsMap).when()
					.put("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/").then()
					.extract().response();

			CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

			System.out.println(CommonMethod.responsetime);

			CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

			CommonMethod.testlog("Info", "Starting Test for Test Data " + str);
			CommonMethod.testlog("Info", "Response from API" + CommonMethod.res.asString());
			CommonMethod.testlog("Info", "Content Type is : " + CommonMethod.res.getContentType());
			CommonMethod.testlog("Info", "Status Code is : " + CommonMethod.res.getStatusCode());
			System.out.println(CommonMethod.res.asString());
			System.out.println("Content Type is : " + CommonMethod.res.getContentType());
			System.out.println("Status Code is : " + CommonMethod.res.getStatusCode());

		if(str.equalsIgnoreCase(" ") || str.equalsIgnoreCase("11111111111122222222223333333333334444444444444455555555557327328412734972346236413278462178467218347623746327864237846781289127847234627384673278432472384728378423742743248423742842364432784632746324623742471897421783689127368912731892731892371892371289371289371298471329847129837192378901231321371328743894638296431784182535123546127846893748137418932984") 
				|| str.equalsIgnoreCase("weifwrefnijfwqnfiwqwuqwheuwhuefwhfhwefuiwfhwfquehwdhjewkhfkewhfjhewjkfnwejknfjndjkfnwehfwenfwjkefnwjkefhwejfnjwefnwejkfhewjkfnwejkfnewjfhwkjefnjkwnfjkwekfhwkjehfkwjenfkjwefnjwkjefhkewjfqwdqwyqywudhqweijdijewwkdfqwefdefiewhjikehjewkdfnioewfuhjqwiohdfkjqwedfnefejffekfqedsufewkejekwfnewvuhwuifirwgtrhiifwejkfdjdksdjfdsiufhweifhdasjkfhweufhweuifhweihfwjkefnfw") 
				|| str.equalsIgnoreCase("n7r823yr8x8n32xr89y3r83ery3278n&@^*&#YNSUUUUUUUU&&@^W&@%@^$^#$@TDUEH@*Y#@N*(#@&*Y@*Y#*@(@*&#Y@WN@&*%^&&&&&&C@D$@%^E^&@R^R^!@$C^#@^ED^&&#ME@(#*(ME&*(#(*U*(E&*(#@NFNF@H*JJJE*(!@(#*(#(@*HD@&^&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*#D**#DH&HMM*HJXCJNMNB@MNVDNB#VD#VDC#FXDZD#XFQCDGC@G#VDU@G#VDY@DY@B#DJK@NDI@O#JD@*()@U()*#^T&*@GDY@GDHJDG@IDI@JI@IO*@(@&T&RDS^%@#F^RDCST")) {
			
			CommonMethod.res.then().assertThat().statusCode(400);

			CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
			
		}

		else {
			
		
		CommonMethod.res.then().assertThat().statusCode(200);

		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
	}
	}
	}

	@AfterMethod
	public void teardown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			CommonMethod.test.log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			CommonMethod.test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		} else {
			CommonMethod.test.log(LogStatus.PASS, "Test passed");
		}

		CommonMethod.extent.endTest(CommonMethod.test);
		CommonMethod.extent.flush();

	}
}