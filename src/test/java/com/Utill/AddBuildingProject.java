package com.Utill;

import java.io.Serializable;

public class AddBuildingProject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public String rating_system; 
	public String gross_area;
	public String occupancy;
	public String street;
	public String city;
	public String country;
	public String state;
	public String project_type;
	public String unitType;
	public String spaceType;
	public String owner_email;
	public String ownerType;
	public boolean confidential;
	public boolean sign_agreement;
	public String organization;
	public String manageEntityCountry;
	public String zip_code;
	public String operating_hours;
	
	public String getOperating_hours() {
		return operating_hours;
	}
	public void setOperating_hours(String operating_hours) {
		this.operating_hours = operating_hours;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRating_system() {
		return rating_system;
	}
	public void setRating_system(String rating_system) {
		this.rating_system = rating_system;
	}
	public String getGross_area() {
		return gross_area;
	}
	public void setGross_area(String gross_area) {
		this.gross_area = gross_area;
	}
	public String getOccupancy() {
		return occupancy;
	}
	public void setOccupancy(String occupancy) {
		this.occupancy = occupancy;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getProject_type() {
		return project_type;
	}
	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getSpaceType() {
		return spaceType;
	}
	public void setSpaceType(String spaceType) {
		this.spaceType = spaceType;
	}
	public String getOwner_email() {
		return owner_email;
	}
	public void setOwner_email(String owner_email) {
		this.owner_email = owner_email;
	}
	public String getOwnerType() {
		return ownerType;
	}
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	public boolean isConfidential() {
		return confidential;
	}
	public void setConfidential(boolean confidential) {
		this.confidential = confidential;
	}
	public boolean isSign_agreement() {
		return sign_agreement;
	}
	public void setSign_agreement(boolean sign_agreement) {
		this.sign_agreement = sign_agreement;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getManageEntityCountry() {
		return manageEntityCountry;
	}
	public void setManageEntityCountry(String manageEntityCountry) {
		this.manageEntityCountry = manageEntityCountry;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	
}
