package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetSnapshotDownloadAPITest extends BaseClass {

	@Test(groups="SnapshotDownload")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetSnapshotDownloadAPI(String SheetName,String ProjectTypeColumn, int rownumber){
		
		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + 
					"/review/snapshot/?access-token="+CommonMethod.Token+"&subscription-key="+CommonMethod.SubscriptionKey;
			CommonMethod.res= MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(	CommonMethod.res, 200);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
	}


}