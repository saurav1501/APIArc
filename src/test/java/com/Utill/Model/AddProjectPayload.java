package com.Utill.Model;

import java.io.IOException;
import java.util.HashMap;

import com.Utill.AddBuildingProject;
import com.Utill.OwnerOrg;
import com.Utill.RandomData;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AddProjectPayload extends BaseClass {
    static AddBuildingProject addProject ;
	
	public static AddBuildingProject addProjectPayload(String ProjectType,String ProjectTypeColumn,String Country ,String Rating) throws IOException {
		addProject = new AddBuildingProject();
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
        ProjectName= RandomData.projectName(Country,Rating,ProjectType);
        
        String owner_email =data.getCellData(sheetName, "OwnerEmail", rowNumTwo);
	
        addProject.setName(ProjectName);
        addProject.setRating_system(Rating);
        addProject.setGross_area(GrossArea);
        addProject.setOccupancy(occupant);
        addProject.setStreet(data.getCellData(sheetName, "Address",rowNumTwo));
        addProject.setCity(data.getCellData(sheetName, "City", rowNumTwo));
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
        addProject.setOrganization(data.getCellData(sheetName, "OwnerOrg", rowNumTwo));
        addProject.setManageEntityCountry(Country);
        addProject.setOperating_hours("100");
		return addProject;  
	}
	
	public static AddBuildingProject addProjectPayloadCityCom(String ProjectType,String ProjectTypeColumn,String Country ,String Rating) throws IOException {
		addProject = new AddBuildingProject();
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
        ProjectName= RandomData.projectName(Country,Rating,ProjectType);
        
        String owner_email =data.getCellData(sheetName, "OwnerEmail", rowNumTwo);
	
        addProject.setName(ProjectName);
        addProject.setUnitType(RandomData.Unit);
        addProject.setProject_type(ProjectType);
        addProject.setRating_system(Rating);
        addProject.setOwnerType(RandomData.OwnerType);
        addProject.setOrganization(data.getCellData(sheetName, "OwnerOrg", rowNumTwo));
        addProject.setManageEntityCountry(Country); 
        addProject.setGross_area(GrossArea);
        addProject.setConfidential(false);
        
        addProject.setOccupancy("50");
        addProject.setOperating_hours("30");
        
        addProject.setStreet(data.getCellData(sheetName, "Address",rowNumTwo));
        addProject.setCity(data.getCellData(sheetName, "City", rowNumTwo));
        addProject.setCountry(Country);
        addProject.setState(RandomData.State);
        addProject.setSpaceType(RandomData.SpaceType);
        addProject.setOwner_email(owner_email);
        addProject.setSign_agreement(false);
        addProject.setZip_code(RandomData.ZipCode); 
		return addProject;  	
	}

	public static AddBuildingProject addProjectPayloadTransit(String ProjectType,String ProjectTypeColumn,String Country ,String Rating) throws IOException {
		addProject = new AddBuildingProject();
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
        ProjectName= RandomData.projectName(Country,Rating,ProjectType);
        
        addProject.setName(ProjectName);
        addProject.setRating_system("LEED V4 O+M: TR");
        addProject.setGross_area(GrossArea);
        addProject.setOccupancy(occupant);
        addProject.setStreet(data.getCellData(sheetName, "Address",rowNumTwo));
        addProject.setCity(data.getCellData(sheetName, "City", rowNumTwo));
        addProject.setCountry(Country);
        addProject.setState(RandomData.State);
        addProject.setProject_type(ProjectType);
        addProject.setUnitType(RandomData.Unit);
        addProject.setSpaceType(RandomData.SpaceType);
        addProject.setOwner_email(data.getCellData(sheetName, "OwnerEmail", rowNumTwo));
        addProject.setOwnerType(RandomData.OwnerType);
        addProject.setConfidential(false);
        addProject.setSign_agreement(false);
        addProject.setZip_code(RandomData.ZipCode); 
        addProject.setOrganization(data.getCellData(sheetName, "OwnerOrg", rowNumTwo));
        addProject.setManageEntityCountry(Country);
        addProject.setOperating_hours("100");
        addProject.setStation_type(Rating);
        addProject.setAnnual_ridership("365");
        addProject.setFull_time_staff("7");
        addProject.setTime_spent_by_riders("120");
		return addProject;  
	}

	
    public static AddBuildingProject validationPayload() {
    	addProject = new AddBuildingProject();
    	addProject.setState("05");
    	addProject.setCountry("TH");
    	addProject.setZip_code("28293");
        return addProject;
}
    
	public static OwnerOrg addOwnerOrg() throws IOException, InterruptedException {
    	OwnerOrg ownerDeatail = new OwnerOrg();
    	ownerDeatail.setOrgName("TestOrg "+CommonMethod.randomNumber());
    	ownerDeatail.setOrgContactName("TestUser");
    	ownerDeatail.setOrgContactEmail("ne@test.com");
    	ownerDeatail.setOrgWebSite("http://example.com");
    	ownerDeatail.setOrgCategory("02");
    	ownerDeatail.setOrgSubCategory("27");
    	return ownerDeatail;
    }

	public static HashMap<Object,Object> paymentCC() {
	   map = new HashMap<Object,Object>();
	   map.put("name", "Adolph Blaine Charles David");
	   map.put("email", "usgbcarcapi1@gmail.com");
	   map.put("unit_price","0.0");
	   map.put("material_code",data.getCellData(sheetName, "MaterialCode", rowNumTwo));
	   map.put("material_description",data.getCellData(sheetName, "MaterialDescription", rowNumTwo));
	   map.put("quantity","1.0");
	   map.put("company_code",data.getCellData(sheetName, "CompanyCode", rowNumTwo));
	   map.put("street","Test Add");
	   map.put("city","Test city");
	   map.put("state","NA");
	   map.put("country","TG");
	   map.put("zip_code","22673");
	   map.put("SoReference","REGISTRATION");
	   map.put("actual_price","0.0");
	   map.put("annual_price","0.0");
	   map.put("paymetric_r","PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz48UGF5bWV0cmljUmVzcG9uc2U-PFN0YXR1cz5Ub2tlbml6ZWQ8L1N0YXR1cz48WGlQYXlOZXRUcmFuc0lEPjA8L1hpUGF5TmV0VHJhbnNJRD48TWVyY2hhbnRSZWZlcmVuY2VDb2RlIC8-PEF1dGhDb2RlPjA8L0F1dGhDb2RlPjxDYXJkSW5mbz48Q0NUb2tlbj4tRTgwMy0wMDAwLVdZODdUR0cwQlJCQzRZPC9DQ1Rva2VuPjxDQ0V4cGlyYXRpb25EYXRlPjxNb250aD4wMTwvTW9udGg-PFllYXI-MTk8L1llYXI-PC9DQ0V4cGlyYXRpb25EYXRlPjxDVlY-OTk5PC9DVlY-PENDVHlwZT40MDAwPC9DQ1R5cGU-PEJpblJhbmdlVHlwZT40MDAwPC9CaW5SYW5nZVR5cGU-PC9DYXJkSW5mbz48QW1vdW50PjA8L0Ftb3VudD48L1BheW1ldHJpY1Jlc3BvbnNlPg==");
	   map.put("paymetric_s","9pVBS5jQtnNbRGzfciD03OoBN7fP1aSFigXogT7LOaw=");				
	   return map;
	  }
   
   public static HashMap<Object,Object> paymentCheck() {
	   map = new HashMap<Object,Object>();
	   map.put("name", "Adolph Blaine Charles David");
	   map.put("email", "usgbcarcapi1@gmail.com");
	   map.put("unit_price","0.0");
	   map.put("material_code",data.getCellData(sheetName, "MaterialCode", rowNumTwo));
	   map.put("material_description",data.getCellData(sheetName, "MaterialDescription", rowNumTwo));
	   map.put("quantity","1.0");
	   map.put("company_code",data.getCellData(sheetName, "CompanyCode", rowNumTwo));
	   map.put("street","Test Add");
	   map.put("city","Test city");
	   map.put("state","NA");
	   map.put("country","TG");
	   map.put("zip_code","22673");
	   map.put("SoReference","REGISTRATION");
	   map.put("actual_price","0.0");
	   map.put("annual_price","0.0");
	   return map;
	  }
   
   public static HashMap<Object,Object> yearconstructed() {
    	map = new HashMap<Object,Object>();
    	map.put("year_constructed",data.getCellData(sheetName, "Year", rowNumTwo));
    	map.put("operating_hours",data.getCellData(sheetName, "OpHours", rowNumTwo));
    	map.put("occupancy", data.getCellData(sheetName,"Occupancy", rowNumTwo));
     	return map;
    }
   public static HashMap<Object,Object> yearconstructedCityCom() {
   	map = new HashMap<Object,Object>();
   	map.put("year_constructed",data.getCellData(sheetName, "Year", rowNumTwo));
    return map;
   }
    
    public static AddBuildingProject analyisData() {
    	addProject = new AddBuildingProject();
    	addProject.setEffective_at("2018-01-22T00:00:00");
    	addProject.setOperating_hours("62");
    	addProject.setCategory("Basic");
		return addProject;
    	
    }
    
    public static AddBuildingProject addProjectPayloadTrial(String ProjectType,String ProjectTypeColumn,String Country ,String Rating) throws IOException {
		addProject = new AddBuildingProject();
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
        ProjectName= RandomData.projectName(Country,Rating,ProjectType);
        addProject.setName(ProjectName);
        addProject.setRating_system(Rating);
        addProject.setGross_area(GrossArea);
        addProject.setOccupancy(occupant);
        addProject.setStreet(data.getCellData(sheetName, "Address",rowNumTwo));
        addProject.setCity(data.getCellData(sheetName, "City", rowNumTwo));
        addProject.setCountry(Country);
        addProject.setState(RandomData.State);
        addProject.setProject_type(ProjectType);
        addProject.setUnitType(RandomData.Unit);
        addProject.setConfidential(true);
        addProject.setSign_agreement(true);
        addProject.setZip_code(RandomData.ZipCode); 
            
		return addProject;  
	}

    
    public static AddBuildingProject addProjectPayloadTrialSync(String ProjectType,String ProjectTypeColumn,String Country ,String Rating) throws IOException {
    	addProject = new AddBuildingProject();
    	
    	RandomData.spaceType();
        RandomData.ownerType();
        addProject.setOwner_email(data.getCellData(sheetName, "OwnerEmail", rowNumTwo));
        RandomData.setOwnerCountryAddressOwnerOrg(Country); 
        addProject.setSign_agreement(true);
        addProject.setRating_system(Rating);
        addProject.setManageEntityCountry(Country);   
        addProject.setSpaceType(RandomData.SpaceType);
        addProject.setOwner_email(data.getCellData(sheetName, "OwnerEmail", rowNumTwo));
        addProject.setOwnerType(RandomData.OwnerType);
        addProject.setOrganization(data.getCellData(sheetName, "OwnerOrg", rowNumTwo));

		return addProject;  
	}
    public static AddBuildingProject addProjectParksmartPayload(String ProjectType,String ProjectTypeColumn,String Country ,String Rating) throws IOException {
    {
    	addProject = new AddBuildingProject();
    	
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
        ProjectName= RandomData.projectName(Country,Rating,ProjectType);
        
        addProject.setName(ProjectName);
        addProject.setRating_system(Rating);
        addProject.setGross_area(GrossArea);
        addProject.setOccupancy(occupant);
        addProject.setStreet(data.getCellData(sheetName, "Address",rowNumTwo));
        addProject.setCity(data.getCellData(sheetName, "City", rowNumTwo));
        addProject.setCountry(Country);
        addProject.setState(RandomData.State);
        addProject.setProject_type(ProjectType);
        addProject.setUnitType(RandomData.Unit);
        addProject.setConfidential(true);
        addProject.setSign_agreement(true);
        addProject.setZip_code(RandomData.ZipCode); 
        addProject.setNoOfParkingSpace("15");
        addProject.setNoOfParkingLevels("2");
        addProject.setOrganization(data.getCellData(sheetName, "OwnerOrg", rowNumTwo));
        addProject.setManageEntityCountry(Country);
        addProject.setOwner_email(data.getCellData(sheetName, "OwnerEmail", rowNumTwo));
        addProject.setOwnerType(RandomData.OwnerType);
  
    	
        return addProject;
  
   
     }
    }
    public static AddBuildingProject addProjectSingleCharProject(String ProjectType,String ProjectTypeColumn,String Country ,String Rating) throws IOException {
		addProject = new AddBuildingProject();
		String ProjectName="t";
        RandomData.unitType();
        RandomData.spaceType();
        RandomData.ownerType();
        RandomData.setOwnerCountryAddressOwnerOrg(Country);
         
        String owner_email =data.getCellData(sheetName, "OwnerEmail", rowNumTwo);
	
        addProject.setName(ProjectName);
        addProject.setRating_system(Rating);
        addProject.setGross_area(GrossArea);
        addProject.setOccupancy(occupant);
        addProject.setStreet(data.getCellData(sheetName, "Address",rowNumTwo));
        addProject.setCity(data.getCellData(sheetName, "City", rowNumTwo));
        addProject.setCountry(Country);
        addProject.setState(RandomData.State);
        addProject.setProject_type(ProjectType);
        addProject.setUnitType(RandomData.Unit);
        addProject.setSpaceType(RandomData.SpaceType);
        addProject.setOwner_email(owner_email);
        addProject.setOwnerType(RandomData.OwnerType);
        addProject.setConfidential(false);
        addProject.setSign_agreement(false);
        addProject.setZip_code("20037"); 
        addProject.setOrganization(data.getCellData(sheetName, "OwnerOrg", rowNumTwo));
        addProject.setManageEntityCountry(Country);
        addProject.setOperating_hours("100");
		return addProject;  
	}
	
	public static AddBuildingProject addProjectPayloadTransitNullPayload(String ProjectType,String ProjectTypeColumn,String Country ,String Rating) throws IOException {
		addProject = new AddBuildingProject();
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
        ProjectName= RandomData.projectName(Country,Rating,ProjectType);
        
        addProject.setName(ProjectName);
        addProject.setRating_system(Rating);
        addProject.setGross_area(GrossArea);
        addProject.setOccupancy(occupant);
        addProject.setStreet(data.getCellData(sheetName, "Address",rowNumTwo));
        addProject.setCity(data.getCellData(sheetName, "City", rowNumTwo));
        addProject.setCountry(Country);
        addProject.setState(RandomData.State);
        addProject.setProject_type(ProjectType);
        addProject.setUnitType(RandomData.Unit);
        addProject.setConfidential(true);
        addProject.setSign_agreement(true);
        addProject.setZip_code(RandomData.ZipCode); 
        addProject.setNoOfParkingSpace("15");
        addProject.setNoOfParkingLevels("2");
        addProject.setOrganization(data.getCellData(sheetName, "OwnerOrg", rowNumTwo));
        addProject.setManageEntityCountry(Country);
        addProject.setOwner_email(null);
        addProject.setOwnerType(null);

		return addProject;  
	}

 }
