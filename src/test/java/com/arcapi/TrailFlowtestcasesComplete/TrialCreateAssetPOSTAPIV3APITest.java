package com.arcapi.TrailFlowtestcasesComplete;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.AddProjectPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class TrialCreateAssetPOSTAPIV3APITest extends BaseClass {

	@Test(groups="Add Project")
	@Parameters({"ProjectType","ProjectTypeColumn","Country" ,"ratings"})
	public void TrialCreateAssetPOSTAPIV3(String ProjectType,String ProjectTypeColumn,String Country,String ratings) throws IOException {	
	
try {
	payload = AddProjectPayload.addProjectPayloadTrial(ProjectType,ProjectTypeColumn,Country,ratings);
	url = "/assets/registration/";
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