package com.arcapi.Buildingtestcases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifyProjectRegisteredAssetListTest extends BaseClass {

	@Test(groups="CheckAsset")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyProjectRegisteredAssetList(String SheetName,String ProjectTypeColumn, int rownumber) {

		try {
			String LeedID = data.getCellData(SheetName, ProjectTypeColumn, rownumber);
			url="/assets/";
			CommonMethod.res= MethodCall.GETRequest(url);
			ArrayList<String> Leed_ID_List = CommonMethod.res.path("results.leed_id");
			Set<String> set = new HashSet<String>(Leed_ID_List); 
			Assert.assertTrue((set.toString().contains(LeedID)),"List does not have LEED ID");		
			CommonMethod.res.then().spec(respSpec);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}