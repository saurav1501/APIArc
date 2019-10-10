package com.arcapi.ScenarioBasedTestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.AddProjectPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class CreateProjectBySingleCharacterAPITest extends BaseClass {

	@Test(groups="Add Project")
	@Parameters({"ProjectType","ProjectTypeColumn","Country" ,"ratings"})
	public void CreateProjectBySingleCharacterAPI(String ProjectType,String ProjectTypeColumn,String Country,String ratings) {	
		
		try {
			payload = AddProjectPayload.addProjectSingleCharProject(ProjectType, ProjectTypeColumn, Country, ratings);
			url = "/assets/";
			CommonMethod.res = MethodCall.POSTRequest(url,payload);
			Assertion.verifyStatusCode(CommonMethod.res, 400);
		 } catch (Exception e) {
			e.printStackTrace();
		}
        	
	}
}

