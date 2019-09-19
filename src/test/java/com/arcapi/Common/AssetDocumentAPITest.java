package com.arcapi.Common;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetDocumentAPITest extends BaseClass {

	@Test(groups="CheckAsset")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void AssetDocumentAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		try {
			url="/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/documents/";		
			CommonMethod.res=MethodCall.GETRequest(url);
			CommonMethod.fetchedID = CommonMethod.res.path("EtFile[0].Documentnumber").toString();
			log.info(CommonMethod.fetchedID);
			data.setCellData(SheetName, "DocumentNumber", rownumber, CommonMethod.fetchedID);
			CommonMethod.fetchedID = CommonMethod.res.path("EtFile[0].ApplicationId").toString();
			log.info(CommonMethod.fetchedID);
			data.setCellData(SheetName, "ApplicationID", rownumber, CommonMethod.fetchedID);
			CommonMethod.fetchedID = CommonMethod.res.path("EtFile[0].FileId").toString();
			log.info(CommonMethod.fetchedID);
			data.setCellData(SheetName, "FileID", rownumber, CommonMethod.fetchedID);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}