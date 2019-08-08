package com.arcapi.Buildingtestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.AddBuildingProject;
import com.Utill.RandomData;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;


public class CreateAssetBuildingNoneAPITest extends BaseClass {

	@Test(groups = { "Certification", "Precertification","PerformanceScore","Recertification" })
	@Parameters({ "ProjectType","ProjectTypeColumn","Country" })
	public void CreateAssetPOSTAPI(String ProjectType,String ProjectTypeColumn,String Country) throws IOException {

			
		String ProjectName=null;

        if(Country.equals("IN"))
		
		{
        	RandomData.selectIndState(Country);
		
		}
		
        else if(Country.equals("US"))
			
		{
        	RandomData.selectUSState(Country);
			
		}
		
        else if(Country.equals("CN"))
			
		{
        	RandomData.selectCNState(Country);
		}
	
        RandomData.unitType();
        RandomData.spaceType();
        RandomData.ownerType();
        RandomData.setOwnerCountryAddressOwnerOrg(Country);
        ProjectName= RandomData.projectName(Country);
        System.out.println(ProjectName);
        System.out.println(ProjectType);
        System.out.println(RandomData.ZipCode);
        System.out.println(Country);
        
        String owner_email =data.getCellData(sheetName, "OwnerEmail", rowNumTwo);
	
        AddBuildingProject addProject = new AddBuildingProject();
        
        addProject.setName(ProjectName);
        addProject.setRating_system("none");
        addProject.setGross_area(GrossArea);
        addProject.setOccupancy(occupant);
        addProject.setStreet("2099 Pennsylvania Avenue");
        addProject.setCity("Washington");
        addProject.setCountry(Country);
        addProject.setState(RandomData.State);
        addProject.setProject_type(ProjectType);
        addProject.setUnitType(RandomData.Unit);
        addProject.setSpaceType(RandomData.SpaceType);
        addProject.setOwner_email(owner_email);
        addProject.setOwnerType(RandomData.OwnerType);
        addProject.setConfidential(false);
        addProject.setSign_agreement(false);
        addProject.setZip_code(RandomData.ZipCode); 
        addProject.setOrganization("Usgbc Arc");
        addProject.setManageEntityCountry(Country);
        addProject.setOperating_hours("100");
   
       
		CommonMethod.res = given().log().all()
				.headers(headerMap)
				.header("Authorization",header).spec(reqSpec).body(addProject).when().post("/assets/").then()
				.extract().response();
		
		System.out.println("This is " + Country + " " + "Project");

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.res.asString());

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Create New Building Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Add asset")
				.assignCategory("CheckAsset");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);

		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		
		CommonMethod.res.then().assertThat().statusCode(201);

		CommonMethod.fetchedID = CommonMethod.res.path("leed_id").toString();

		System.out.println(CommonMethod.fetchedID);

		data.setCellData(sheetName, ProjectTypeColumn, rowNumTwo, CommonMethod.fetchedID);

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");

	}

	

}