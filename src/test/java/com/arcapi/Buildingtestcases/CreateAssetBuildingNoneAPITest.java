package com.arcapi.Buildingtestcases;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.MethodCall;
import com.Utill.Controller.URL;
import com.Utill.Model.AddProjectPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class CreateAssetBuildingNoneAPITest extends BaseClass {

	@Test(groups = { "Certification", "Precertification","PerformanceScore","Recertification" })
	@Parameters({ "ProjectType","ProjectTypeColumn","Country" ,"ratings"})
	public void CreateAssetPOSTAPI(String ProjectType,String ProjectTypeColumn,String Country,String ratings) throws IOException {
		
		Object addProject = AddProjectPayload.addProjectPayload(ProjectType,ProjectTypeColumn,Country,ratings);
		
		
	    url = URL.getEndPoint("/assets/");
		CommonMethod.res = MethodCall.POSTRequest(url,addProject);
	
		
		/*CommonMethod.res = given().log().all()
				.headers(headerMap)
				.header("Authorization",header).spec(reqSpec).body(addProject).when().post("/assets/").then()
				.extract().response();*/
		log.info("This is " + Country + " " + "Project");

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		log.info(CommonMethod.res.asString());

		log.info(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Create New Building Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Add asset")
				.assignCategory("CheckAsset");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);

		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		
		CommonMethod.res.then().assertThat().statusCode(201);

		CommonMethod.fetchedID = CommonMethod.res.path("leed_id").toString();

		System.out.println(CommonMethod.fetchedID);

		data.setCellData(sheetName, ProjectTypeColumn, rowNumTwo, CommonMethod.fetchedID);

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");

	}

	

}