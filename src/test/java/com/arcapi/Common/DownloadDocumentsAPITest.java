package com.arcapi.Common;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class DownloadDocumentsAPITest extends BaseClass {

	@Test(groups="CheckDownloadDocuments")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void DownloadDocumentsAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		try {
			String DocFileName = "Agreement.pdf";
			String IDocNumber = data.getCellData(SheetName, "DocumentNumber", rownumber);
			String IFileApplicationId = data.getCellData(SheetName, "ApplicationID", rownumber);
			String IFileId = data.getCellData(SheetName, "FileID", rownumber);

			url= "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + 
				"/documents/download/?Docfile="+DocFileName+"&IDocNumber="+IDocNumber+"&IDocPart=000&IDocType=Z02&IDocVersion=00&IFileApplicationId="+IFileApplicationId+""
				+ "&IFileId="+IFileId+"&subscription-key="+CommonMethod.SubscriptionKey+"&access-token="+CommonMethod.Token;
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}


}