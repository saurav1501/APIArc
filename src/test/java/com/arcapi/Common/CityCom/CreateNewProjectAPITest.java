package com.arcapi.Common.CityCom;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.AddProjectPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class CreateNewProjectAPITest extends BaseClass {

	@Test(groups="CheckCreateProject")
	@Parameters({"SheetName", "ProjectType","ProjectTypeColumn","rownumber","Country" ,"ratings"})
	public void CreateAssetPOSTAPI(String SheetName, String ProjectType,String ProjectTypeColumn, int rownumber, String Country,String ratings)  {
	
		
		try {
			payload = AddProjectPayload.addProjectPayloadCityCom(ProjectType,ProjectTypeColumn,Country,ratings);
			url = "/assets/";
			CommonMethod.res = MethodCall.POSTRequest(url,payload);
			Assertion.verifyStatusCode(CommonMethod.res, 201);
			CommonMethod.fetchedID = CommonMethod.res.path("leed_id").toString();
			log.info(CommonMethod.fetchedID);
			data.setCellData(sheetName, ProjectTypeColumn, rowNumTwo, CommonMethod.fetchedID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		}

	
}