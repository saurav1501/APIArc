package com.arcapi.Parksmarttestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.AddBuildingProject;
import com.Utill.RandomData;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class CreateAssetParksmartManualReqTest extends BaseClass {

	@Test(groups="AddProjectParksmart")
	@Parameters({ "SheetName", "ProjectType","ProjectTypeColumn","rownumber" })
	public void CreateAssetPOSTAPI(String SheetName, String ProjectType,String ProjectTypeColumn, int rownumber) throws IOException {

				
		int RowNum = 12;
		
		for(int i=2;i<=RowNum;i++) {
			
		String ProjectName = "API-Parksmart-" + data.getCellData("Manual", "CountryCode", i);
			
		String CountryCode = data.getCellData("Manual", "CountryCode", i);
		
		String StateCode = data.getCellData("Manual", "StateCode", i);
		
		String ZipCode = data.getCellData("Manual", "ZipCode", i);
		
		AddBuildingProject addProject = new AddBuildingProject();
		addProject.setName(ProjectName);
        addProject.setRating_system("PARKSMART");
        addProject.setGross_area(GrossArea);
        addProject.setOccupancy(occupant);
        addProject.setStreet(data.getCellData(sheetName, "Address",rowNumTwo));
        addProject.setCity(data.getCellData(sheetName, "City", rowNumTwo));
        addProject.setCountry(CountryCode);
        addProject.setState(StateCode);
        addProject.setProject_type(ProjectType);
        addProject.setUnitType(RandomData.Unit);
        addProject.setConfidential(true);
        addProject.setSign_agreement(true);
        addProject.setZip_code(ZipCode); 
        addProject.setNoOfParkingSpace("15");
        addProject.setNoOfParkingLevels("2");
        addProject.setOrganization(data.getCellData(sheetName, "OwnerOrg", rowNumTwo));
        addProject.setManageEntityCountry(CountryCode);
        addProject.setOwner_email(data.getCellData(sheetName, "OwnerEmail", rowNumTwo));
        addProject.setOwnerType(RandomData.OwnerType);
        addProject.setYear_constructed("2018-04-30");
        
        url = "/assets/";
        CommonMethod.res = MethodCall.POSTRequest(url, addProject);
		
/*		CommonMethod.res = given().log().all()
				.parameters(
					    "name", ProjectName,
					    "project_type", ProjectType,
					    "rating_system","PARKSMART", 
					    "ownerType", OwnerType,
					    "organization","A & A Dealers",
					    "owner_email","usgbcarcapi@gmail.com",
					    "manageEntityCountry",CountryCode,
					    "confidential", false,
					    "noOfParkingSpace", "15", 
					    "noOfParkingLevels","2",
					    "year_constructed", "2018-04-30",
					    "street", "1 Mount Road", 
					    "city","Test City", 
					    "country", CountryCode, 
					    "state", StateCode,
					    "zip_code",ZipCode
					    )
				.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when().post("/assets/").then()
				.extract().response();*/
		
		
		
		Assertion.verifyStatusCode(CommonMethod.res, 201);	
		
		CommonMethod.fetchedID = CommonMethod.res.path("leed_id").toString();
		
		data.setCellData("Manual", ProjectTypeColumn, i, CommonMethod.fetchedID);

		
	}
	}



}