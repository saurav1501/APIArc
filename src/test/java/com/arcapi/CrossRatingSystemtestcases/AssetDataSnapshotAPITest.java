package com.arcapi.CrossRatingSystemtestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetDataSnapshotAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetDataSnapshotAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + 
				"/snapshot/?access-token="+CommonMethod.Token+"&start_date=2017-01-01&end_date=2017-12-30&email="+data.getCellData(SheetName, "NormalUserName", rownumber);
		
		CommonMethod.res= MethodCall.GETRequest(url);
		
		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + 
				"/snapshot/?access-token="+CommonMethod.Token+"&start_date=2017-01-01&end_date=2017-12-30&email="+data.getCellData(SheetName, "NormalUserName", rownumber);
		Assertion.verifyStatusCode(CommonMethod.res, 403);
		
	
	
}}