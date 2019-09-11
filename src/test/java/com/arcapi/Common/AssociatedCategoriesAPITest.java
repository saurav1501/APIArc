package com.arcapi.Common;

import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssociatedCategoriesAPITest extends BaseClass {

	@Test(groups="CheckAssociatedCategories")
	public void AssociatedCategoriesAPI() {

		try {
			url = "/categories/";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(	CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}