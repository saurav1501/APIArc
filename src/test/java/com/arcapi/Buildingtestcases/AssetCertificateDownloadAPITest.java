package com.arcapi.Buildingtestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetCertificateDownloadAPITest extends BaseClass {

	@Test(groups="CheckData")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void AssetCertificateDownloadAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		String docId = data.getCellData("LEEDONLINE","LeedIDINT", 4);
		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/documents/download/?doc_id="+docId+"&doc_source=certificate&access-token="+CommonMethod.Token+"&subscription-key="+CommonMethod.SubscriptionKey;
		CommonMethod.res = MethodCall.GETRequest(url);		
		Assertion.verifyStatusCode(CommonMethod.res, 400);		
	}


}