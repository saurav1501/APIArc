package com.arcapi.Leedv4Testcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class MeterCommentPostGroup1APITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void MeterCommentPostGroup1API(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
	
		int RowNum = 10;
		
	    for (int i =2; i<= RowNum;i++) {
		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("confidential", "false");
		jsonAsMap.put("data", "This is Test Comment");
		
		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
				+  data.getCellData("DataInput", "HumanExperienceMeterID", i) + "/comment/";

		CommonMethod.res = MethodCall.POSTRequest(url, jsonAsMap);
		
		CommonMethod.fetchedID = CommonMethod.res.path("id[0]").toString();

	    data.setCellData("LEEDONLINE", "CommentIDGroup1", i, CommonMethod.fetchedID);
			
		Assertion.verifyStatusCode(CommonMethod.res, 200);
		
	}
	}
	
}