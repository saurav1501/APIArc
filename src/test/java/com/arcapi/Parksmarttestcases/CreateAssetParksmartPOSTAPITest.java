package com.arcapi.Parksmarttestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.AddProjectPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class CreateAssetParksmartPOSTAPITest extends BaseClass {

	@Test(groups="Add Project Parksmart")
	@Parameters({"ProjectType","ProjectTypeColumn","Country" ,"ratings"})
	public void CreateAssetParksmartPOST(String ProjectType ,String ProjectTypeColumn,String Country,String ratings) throws IOException {	
		
		try {
			payload = AddProjectPayload.addProjectParksmartPayload(ProjectType,ProjectTypeColumn,Country,ratings);
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

