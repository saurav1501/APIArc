package com.arcapi.Common.V2;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class BasicAnalysisDataGetAPITest extends BaseClass {

	@Test(groups="CheckAnalysis")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void BasicAnalysisDataGetAPI(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			url = "/assets/basic/analytics/";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			
			CommonMethod.fetchedID = CommonMethod.res.path("total_projects").toString();
			System.out.println(CommonMethod.fetchedID);
			data.setCellData(sheetName, ProjectTypeColumn,4,CommonMethod.fetchedID);

			CommonMethod.fetchedID = CommonMethod.res.path("bst_projects").toString();
			data.setCellData(sheetName, ProjectTypeColumn, 5, CommonMethod.fetchedID);
			System.out.println(CommonMethod.fetchedID);
			
			CommonMethod.fetchedID = CommonMethod.res.path("certified_projects").toString();
			data.setCellData(sheetName, ProjectTypeColumn, 6, CommonMethod.fetchedID);
			System.out.println(CommonMethod.fetchedID);
			
			CommonMethod.fetchedID = CommonMethod.res.path("building_project").toString();
			data.setCellData(sheetName, ProjectTypeColumn, 7, CommonMethod.fetchedID);
			System.out.println(CommonMethod.fetchedID);
			
			CommonMethod.fetchedID = CommonMethod.res.path("bst_floor_area").toString();
			data.setCellData(sheetName, ProjectTypeColumn, 8, CommonMethod.fetchedID);
			System.out.println(CommonMethod.fetchedID);
			
			CommonMethod.fetchedID = CommonMethod.res.path("bst_occupants").toString();
			data.setCellData(sheetName, ProjectTypeColumn, 9, CommonMethod.fetchedID);

			CommonMethod.fetchedID = CommonMethod.res.path("bst_certified_area").toString();
			data.setCellData(sheetName, ProjectTypeColumn, 10, CommonMethod.fetchedID);

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	

}