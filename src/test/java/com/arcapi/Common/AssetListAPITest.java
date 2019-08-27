package com.arcapi.Common;
import org.testng.annotations.Test;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetListAPITest extends BaseClass {

	@Test(groups="CheckAsset")
	public void AssetListAPI() {

		try {
			url= "/assets/";
			CommonMethod.res=MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}


}