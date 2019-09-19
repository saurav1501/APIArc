package com.arcapi.Leedv4Testcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

import net.minidev.json.JSONObject;

public class MeterCommentReplyGroup1APITest extends BaseClass {

	@Test(groups="CheckComment")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void MeterCommentReplyGroup1API(String SheetName, String ProjectTypeColumn, int rownumber) {

		
		try {
			int RowNum = 10;

			for (int i = 2; i <= RowNum; i++) {

					String getParentID = data.getCellData("LEEDONLINE", "CommentIDGroup1", i);

					JSONObject jsonAsMap = new JSONObject();
					jsonAsMap.put("confidential", "false");
					jsonAsMap.put("data", "This is Reply Test Comment");
					jsonAsMap.put("parent", getParentID);
					
					url = "/assets/LEED:" + data.getCellData(sheetName, ProjectTypeColumn, rownumber)
					+ "/meters/ID:" + data.getCellData("DataInput", "HumanExperienceMeterID", i) + "/comment/";
					
					CommonMethod.res = MethodCall.POSTRequest(url, jsonAsMap);

					Assertion.verifyStatusCode(CommonMethod.res, 200);

				}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		}
	
}