package com.arcapi.Leedv4Testcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class MeterCommentPostGroup2APITest extends BaseClass {

	@Test(groups="CheckComment")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void MeterCommentPostGroup2API(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		int row = 2;
		
		String[] type = {"waste","transport","human"};
		 
		for(String Type : type) {
	     
		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("confidential", "false");
		jsonAsMap.put("data", "This is Test Comment");
		
		url = "/assets/LEED:" + data.getCellData(sheetName, ProjectTypeColumn, rownumber) + "/"+Type+"/comment/";

		CommonMethod.res = MethodCall.POSTRequest(url, jsonAsMap);
		
		 CommonMethod.fetchedID = CommonMethod.res.path("id[0]").toString();

	     data.setCellData("LEEDONLINE", "CommentIDGroup2", row, CommonMethod.fetchedID);
	     Assertion.verifyStatusCode(CommonMethod.res, 200);
		row++;
	}
		 
		
	}
	
}