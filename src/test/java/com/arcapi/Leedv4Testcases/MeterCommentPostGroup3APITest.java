package com.arcapi.Leedv4Testcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class MeterCommentPostGroup3APITest extends BaseClass {

	@Test(groups="CheckComment")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void MeterCommentPostGroup3API(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		
		 int RowNum = 5;
		
	     for (int i =2; i<= RowNum;i++) {

		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("confidential", "false");
		jsonAsMap.put("data", "This is Test Comment");
		
		url = "/assets/LEED:" + data.getCellData(sheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
				+  data.getCellData("DataInput", "ExcelTemplateMeterID", i) + "/comment/";
		
		CommonMethod.res = MethodCall.POSTRequest(url, jsonAsMap);
		
		Assertion.verifyStatusCode(CommonMethod.res, 200);
		
		CommonMethod.fetchedID = CommonMethod.res.path("id[0]").toString();

	    data.setCellData("LEEDONLINE", "CommentIDGroup3", i, CommonMethod.fetchedID);
		
		
		
	}
	}
	
}