package com.arcapi.Leedv4Testcases;

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

import net.minidev.json.JSONObject;

public class MeterCommentReplyGroup3APITest extends BaseClass {

	@Test // (dependsOnMethods = {
			// "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "LOSheetName", "LOProjectTypeColumn", "rownumber" })
	public void MeterCommentReplyGroup3API(String SheetName, String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCodeForLOUser(SheetName, rownumber);

		CommonMethod.test = CommonMethod.extent
				.startTest("MeterCommentReplyGroup3API", "Verifies Update asset").assignCategory("CheckAsset");

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		int RowNum = data.getRowCountbyColNum("DataInput", 21);

		/*int RowNum = data.getRowCountbyColNum(SheetName, 30);
        System.out.println(RowNum);
		int HERowNum = data.getRowCountbyColNum("DataInput", 8);
		int TemplateRowNum = data.getRowCountbyColNum("DataInput", 21);
		int[] row = { HERowNum,TemplateRowNum };

		for (int Row : row) {

			System.out.println("I am inside for");

			if (Row == HERowNum) {

				ColName = "HumanExperienceMeterID";
			}

			else {

				ColName = "ExcelTemplateMeterID";
			}

			System.out.println("I am outside for" + ColName);

			for (int i = 2; i <= Row; i++) {

				System.out.println("I am inside last for");*/
		for (int i = 2; i <= RowNum; i++) {

				String getParentID = data.getCellData(SheetName, "CommentIDGroup3", i);

				JSONObject jsonAsMap = new JSONObject();
				jsonAsMap.put("confidential", "false");
				jsonAsMap.put("data", "This is Machine Reply Test Comment");
				jsonAsMap.put("parent", getParentID);
				// System.out.println(data.getCellData(SheetName, ProjectTypeColumn,
				// rownumber));

				CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
						.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
						.body(jsonAsMap).when()
						.post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
								+ "/meters/ID:" + data.getCellData("DataInput", "ExcelTemplateMeterID", i) + "/comment/")
						.then().extract().response();

				CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

				System.out.println(CommonMethod.responsetime);

				CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

				System.out.println(CommonMethod.res.asString());

				CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

				CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");

				CommonMethod.res.then().spec(respSpec);

			}

		}
	
}