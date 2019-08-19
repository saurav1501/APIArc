package com.Utill.Model;

import java.io.IOException;

import com.Utill.AddBuildingProject;
import com.Utill.RandomData;
import com.arc.driver.BaseClass;

public class AddProjectPayload extends BaseClass {
	
	
	public static AddBuildingProject addProjectPayload(String ProjectType,String ProjectTypeColumn,String Country ,String Rating) throws IOException {
			
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
        addProject.setRating_system(Rating);
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
		return addProject;
		
     
       
	}

}
