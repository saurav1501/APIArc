package com.arcapi.TrailFlowtestcasesComplete;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.AddProjectPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

public class SyncSAPTrailProjectAPITest extends BaseClass {

	@Test(groups="TrialSync")
	@Parameters({"ProjectType","ProjectTypeColumn","Country" ,"ratings"})
	public void SyncSAPTrailProjectAPI(String ProjectType,String ProjectTypeColumn,String Country,String ratings)  {	
	
		try {
			payload = AddProjectPayload.addProjectPayloadTrialSync(ProjectType, ProjectTypeColumn, Country, ratings);
			url= "/assets/LEED:" + data.getCellData(sheetName, ProjectTypeColumn, 2) + "/syncsap/"; 
			CommonMethod.res = MethodCall.POSTRequest(url,payload);		
			CommonMethod.fetchedID = CommonMethod.res.path("leed_id").toString();

			data.setCellData(sheetName, ProjectTypeColumn, rowNumTwo, CommonMethod.fetchedID);
			
			Assertion.verifyStatusCode(CommonMethod.res , 200);
		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}