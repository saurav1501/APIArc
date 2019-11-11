package com.arcapi.Buildingtestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ExcelTemplateMetersAPITest extends BaseClass {

	@Test(groups="CheckExcelData")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void ExcelTemplateMetersAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		
		url =  "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/?page_size=20";
		CommonMethod.res = MethodCall.GETRequest(url);
		
		Integer meter = CommonMethod.res.path("count");
			 
	
		for(int i=0;i<meter -1 ;i++){
		
	    Integer type = CommonMethod.res.path("results["+i+"].fuel_type.id");
	    System.out.println(type);
	    
	    if(type==47) {
	    	/* Energy*/
	    	 String meterID = CommonMethod.res.path("results["+i+"].id").toString();
	    	 data.setCellData("DataInput", "ExcelTemplateMeterID",2, meterID);	
	    	 
	    
	    }
		
	    if(type==29) {
	    	/* Water */
	    	 String meterID = CommonMethod.res.path("results["+i+"].id").toString();
	    	 data.setCellData("DataInput", "ExcelTemplateMeterID",3, meterID);
	    	 	 
	    }
	    if(type==205) {
	    	/* TVOC */
	    	 String meterID = CommonMethod.res.path("results["+i+"].id").toString();
	    	 data.setCellData("DataInput", "ExcelTemplateMeterID",5, meterID);	
	    	 
	    	 
	    }
	    if(type==206) {
	    	/* CO2 */
	    	 String meterID = CommonMethod.res.path("results["+i+"].id").toString();
	    	 data.setCellData("DataInput", "ExcelTemplateMeterID",4, meterID);
	    
	    }
	    	Assertion.verifyStatusCode(CommonMethod.res, 200);
		
		}
	}

	

    
}