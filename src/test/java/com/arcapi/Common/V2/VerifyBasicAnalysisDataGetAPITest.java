package com.arcapi.Common.V2;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifyBasicAnalysisDataGetAPITest extends BaseClass {

	@Test(groups="CheckAnalysis")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyBasicAnalysisDataGetAPI(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			url = "/assets/basic/analytics/";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			
			CommonMethod.fetchedID = CommonMethod.res.path("total_projects").toString();	
			Integer totalproject = Integer.parseInt(data.getCellData(sheetName, ProjectTypeColumn, 4));
			Integer totalProject=(totalproject+1);
			String TotalProject = totalProject.toString();
			Assertion.verifyDataContinueOnFaliluare(CommonMethod.fetchedID, TotalProject);
			
			
			
			CommonMethod.fetchedID = CommonMethod.res.path("bst_projects").toString();
			Integer bsttotalproject = Integer.parseInt(data.getCellData(sheetName, ProjectTypeColumn, 5));
			Integer bsttotalProject=(bsttotalproject+1);
			String bstTotalProject = bsttotalProject.toString();
			Assertion.verifyDataContinueOnFaliluare(CommonMethod.fetchedID, bstTotalProject);
		
		
			CommonMethod.fetchedID = CommonMethod.res.path("certified_projects").toString();
			Integer ctotalproject = Integer.parseInt(data.getCellData(sheetName, ProjectTypeColumn, 6));
			Integer ctotalProject=(ctotalproject+1);
			String cTotalProject = ctotalProject.toString();
			Assertion.verifyDataContinueOnFaliluare(CommonMethod.fetchedID, cTotalProject);
			
			CommonMethod.fetchedID = CommonMethod.res.path("building_project").toString();
			Integer btotalproject = Integer.parseInt(data.getCellData(sheetName, ProjectTypeColumn, 7));
			Integer btotalProject=(btotalproject+1);
			String bTotalProject = btotalProject.toString();
			Assertion.verifyDataContinueOnFaliluare(CommonMethod.fetchedID, bTotalProject);
	
			
			CommonMethod.fetchedID = CommonMethod.res.path("bst_floor_area").toString();
			double atotalproject = new Double(data.getCellData(sheetName, ProjectTypeColumn, 8)).doubleValue();
			double atotalProject=atotalproject + 1000;
			String aTotalProject=String.valueOf(atotalProject);
			Assertion.verifyDataContinueOnFaliluare(CommonMethod.fetchedID, aTotalProject);
	
		

			CommonMethod.fetchedID = CommonMethod.res.path("bst_occupants").toString();
			Integer ototalproject = Integer.parseInt(data.getCellData(sheetName, ProjectTypeColumn, 9));
			Integer ototalProject=ototalproject+50;
			String oTotalProject = ototalProject.toString();
			Assertion.verifyDataContinueOnFaliluare(CommonMethod.fetchedID, oTotalProject);
			
			
			
			CommonMethod.fetchedID = CommonMethod.res.path("bst_certified_area").toString();
			double bstctotalproject = new Double(data.getCellData(sheetName, ProjectTypeColumn, 10)).doubleValue();
			double bstctotalProject=bstctotalproject + 1000;
			String bstcTotalProject=String.valueOf(bstctotalProject);
			Assertion.verifyDataContinueOnFaliluare(CommonMethod.fetchedID, bstcTotalProject);
	
		
			softAssert.assertAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	

}