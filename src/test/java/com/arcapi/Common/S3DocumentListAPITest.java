 package com.arcapi.Common;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class S3DocumentListAPITest extends BaseClass {

	@Test(groups="CheckS3DocumenList")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void S3DocumentListAPI(String SheetName,String ProjectTypeColumn, int rownumber){
		
		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/s3documents/";	
			CommonMethod.res = MethodCall.GETRequest(url);
			CommonMethod.fetchedID = CommonMethod.res.path("id[1]").toString();
			data.setCellData(SheetName, "S3DocID", rownumber, CommonMethod.fetchedID);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
}