package com.Utill.Model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.arc.driver.BaseClass;

public class LEEDV4Payload extends BaseClass{
 
	public static HashMap<Object,Object> AddProjectLEEDV4(String SheetName,String Country,String ProjectName,String OwnerType,String ratings,String OwnerOrg ,int rownumber) throws IOException {
	    
	String State = null;
	String Unit = null;
	String ZipCode = null;
	
	if(Country.equals("IN"))
		
	{
		String[] StateOptions  = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17",
				"18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","TG"};
		
		State = (StateOptions[new Random().nextInt(StateOptions.length)]);
		
		data.setCellData("LEEDONLINE", "Country", rownumber, Country);
		
		data.setCellData("LEEDONLINE", "State", rownumber, State);
		
		ZipCode = "122018";
		
		data.setCellData("LEEDONLINE", "ZipCode", rownumber, ZipCode);
	}
	
    else if(Country.equals("US"))
		
	{
		String[] StateOptions  = {"AL","AK","AS","AZ","AR","CA","CO","CT","DE","DC","FL","GA","GU","HI","ID","IL","IN","IA",
				"KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","MP",
				"OH","OK","OR","PA","PR","RI","SC","SD","TN","TX","UT","VT","VI","VA","WA","WV","WI","WY"};
		
		State = (StateOptions[new Random().nextInt(StateOptions.length)]);
		
		data.setCellData("LEEDONLINE", "Country", rownumber, Country);
		
		data.setCellData("LEEDONLINE", "State", rownumber, State);
		
        ZipCode = "20037";
		
		data.setCellData("LEEDONLINE", "ZipCode", rownumber, ZipCode);
	}
	
    else if(Country.equals("CN"))
		
	{
		String[] StateOptions  = {"110","010","320","150","260","190","200","210","220","060","090","180","160","170","100","140","080",
				"070","040","270","280","250","120","020","050","230","030","240","290","300","240","130"};
		
		State = (StateOptions[new Random().nextInt(StateOptions.length)]);
		
		data.setCellData("LEEDONLINE", "Country", rownumber, Country);
		
		data.setCellData("LEEDONLINE", "State", rownumber, State);
		
        ZipCode = "518000";
		
		data.setCellData("LEEDONLINE", "ZipCode", rownumber, ZipCode);
	}
	
	String[] UnitOptions  = {"SI","IP"};
	
	Unit = (UnitOptions[new Random().nextInt(UnitOptions.length)]);
	
	data.setCellData("LEEDONLINE", "Unit", rownumber, Unit);
	
     ProjectName= ProjectName+Country+RandomStringUtils.randomNumeric(2);
	
     data.setCellData("LEEDONLINE", "ProjectName", rownumber, ProjectName);
	
	 map = new HashMap<Object,Object>();
	 map.put("name" ,ProjectName);
	 map.put("ratingSystem",ratings);
	 map.put("unitType" ,"SI");   
	 map.put("grossFootage", "1000");
	 map.put("address.postalCode",ZipCode);
	 map.put("address.city","Test City");
	 map.put("address.state",State);
	 map.put("date.start", "2018-06-01");
	 map.put("owner.organization",OwnerOrg);
	 map.put("address.geoPoints.latitude", "120.12345678");
	 map.put("address.geoPoints.longitude", "10.46467895");
	 map.put("address.country", Country);
	 map.put("date.end", "2028-07-01");
	 map.put("address.address1", "Test Address");
	 map.put("address.address2", "Test Address");
	 map.put("cmd", "register");
	 map.put("owner.name", "Test Owner");
	 map.put("owner.email", "newserver2@gmail.com");
	 map.put("owner.country", Country);
	 map.put("owner.phone", "9080251825");
	 map.put("owner.city","Test City");
	 map.put("owner.address1", "Test Address");
	 map.put("owner.address2", "Test Address");
	 map.put("owner.state",State);
	 map.put("owner.postalCode",ZipCode);
	 map.put("population","20");
   	 map.put("owner.type", OwnerType);
   	 return map;
}
	
	public static HashMap<Object,Object> AddProjectLEEDV4Bulding(String SheetName,String Country,String ProjectName,String OwnerType,String ratings,String OwnerOrg ,int rownumber) throws IOException {
	    
		String State = null;
		String Unit = null;
		String ZipCode = null;
		
		if(Country.equals("IN"))
			
		{
			String[] StateOptions  = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17",
					"18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","TG"};
			
			State = (StateOptions[new Random().nextInt(StateOptions.length)]);
			
			data.setCellData("LEEDONLINE", "Country", rownumber, Country);
			
			data.setCellData("LEEDONLINE", "State", rownumber, State);
			
			ZipCode = "122018";
			
			data.setCellData("LEEDONLINE", "ZipCode", rownumber, ZipCode);
		}
		
	    else if(Country.equals("US"))
			
		{
			String[] StateOptions  = {"AL","AK","AS","AZ","AR","CA","CO","CT","DE","DC","FL","GA","GU","HI","ID","IL","IN","IA",
					"KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","MP",
					"OH","OK","OR","PA","PR","RI","SC","SD","TN","TX","UT","VT","VI","VA","WA","WV","WI","WY"};
			
			State = (StateOptions[new Random().nextInt(StateOptions.length)]);
			
			data.setCellData("LEEDONLINE", "Country", rownumber, Country);
			
			data.setCellData("LEEDONLINE", "State", rownumber, State);
			
	        ZipCode = "20037";
			
			data.setCellData("LEEDONLINE", "ZipCode", rownumber, ZipCode);
		}
		
	    else if(Country.equals("CN"))
			
		{
			String[] StateOptions  = {"110","010","320","150","260","190","200","210","220","060","090","180","160","170","100","140","080",
					"070","040","270","280","250","120","020","050","230","030","240","290","300","240","130"};
			
			State = (StateOptions[new Random().nextInt(StateOptions.length)]);
			
			data.setCellData("LEEDONLINE", "Country", rownumber, Country);
			
			data.setCellData("LEEDONLINE", "State", rownumber, State);
			
	        ZipCode = "518000";
			
			data.setCellData("LEEDONLINE", "ZipCode", rownumber, ZipCode);
		}
		
		String[] UnitOptions  = {"SI","IP"};
		
		Unit = (UnitOptions[new Random().nextInt(UnitOptions.length)]);
		
		data.setCellData("LEEDONLINE", "Unit", rownumber, Unit);
		String[] AnticipitatedTypeOptions  = {"Airport: Control Tower","Airport: Distribution Center","Airport: Hangar","Airport: Office","Airport: Other","Airport: Public Order/Fire/Police","Airport: Rental Car Center","Airport: Terminal/Concourse","Airport: Vehicle Service/Repair","Circulation Space","Core Learning Space: College/University","Core Learning Space: K-12 Elementary/Middle School","Core Learning Space: K-12 High School","Core Learning Space: Other classroom education","Core Learning Space: Preschool/Daycare","Data Center","Healthcare: Clinic/Other Outpatient","Healthcare: Inpatient","Healthcare: Nursing Home/ Assisted Living",
					"Healthcare: Outpatient Office (Diagnostic)","Industrial Manufacturing","Laboratory","Lodging: Dormitory","Lodging: Hotel/Motel/Resort, Full Service","Lodging: Hotel/Motel/Resort, Limited Service","Lodging: Hotel/Motel/Resort, Select Service","Lodging: Inn","Lodging: Other lodging","Multi-Family Residential: Apartment","Multi-Family Residential: Condominium","Multi-Family Residential: Lowrise","Office: Administrative/Professional","Office: Financial","Office: Government",
					"Office: Medical (Non-Diagnostic)","Office: Mixed-Use","Office: Other Office","Public Assembly: Convention Center","Public Assembly: Entertainment","Public Assembly: Library","Public Assembly: Other Assembly","Public Assembly: Recreation","Public Assembly: Social/Meeting","Public Assembly: Stadium/Arena","Public Order and Safety: Fire/Police Station","Public Order and Safety: Other Public Order","Religious Worship","Retail: Bank Branch","Retail: Convenience Store","Retail: Enclosed Mall","Retail: Fast Food","Retail: Grocery Store/Food Market","Retail: Open Shopping Center",
					"Retail: Other Retail","Retail: Restaurant/Cafeteria","Retail: Vehicle Dealership","Service: Other Service","Service: Post Office/Postal Center","Service: Repair Shop","Service: Vehicle Service/Repair","Service: Vehicle Storage/Maintenance","Single family home (attached)","Single family home (detached)","Transit: Depot","Transit: Line","Transit: Maintenance/Storage","Transit: Office","Transit: Other",
					"Transit: Station","Transit: Station/Elevated","Transit: Station/Open Air Ground Level","Transit: Station/Underground","Transit: System","Warehouse: General","Warehouse: Nonrefrigerated Distribution/Shipping","Warehouse: Refrigerated","Warehouse: Self Storage Units","Other"};
			
	     String AnticipitatedType = (AnticipitatedTypeOptions[new Random().nextInt(AnticipitatedTypeOptions.length)]);
		 data.setCellData("LEEDONLINE", "AnticipitatedType", rownumber, AnticipitatedType);
			
	     ProjectName= ProjectName+Country+RandomStringUtils.randomNumeric(2);
		
	     data.setCellData("LEEDONLINE", "ProjectName", rownumber, ProjectName);
		
		 map = new HashMap<Object,Object>();
			   	  
	   	map.put("cmd", "register");
		map.put("name", ProjectName);
		map.put("description", "Register from LO API");
		map.put("ratingSystem", "v4_1.oEb");
		map.put("anticipatedType", AnticipitatedType);
		map.put("unitType", Unit);
		map.put("grossFootage", GrossArea);
		map.put("date.start", "2018-06-01");
		map.put("date.end", "2028-07-01");
		map.put("address.address1", "Test Address");
		map.put("address.city", "Test City");
		map.put("address.country", Country);
		map.put("address.state", State);
		map.put("address.postalCode", ZipCode);
		map.put("address.geoPoints.latitude", "120.12345678");
		map.put("address.geoPoints.longitude", "10.46467895");
		map.put("owner.name", "Test Owner");
		map.put("owner.email", "newserver2@gmail.com");
		map.put("owner.organization", "EymX5ZKWALZae");
		map.put("owner.country", Country);
		map.put("owner.type", "Non-Profit (that do not fit into other categories)");
	   	return map;
	}

}
