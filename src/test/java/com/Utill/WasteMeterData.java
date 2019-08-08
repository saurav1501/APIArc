package com.Utill;

public class WasteMeterData{

	public static final long serialVersionUID = 3403190392144669393L;
	
	public String unit;
	public String start_date;
	public String end_date;
	public String waste_generated;
	public String waste_diverted;
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getWaste_generated() {
		return waste_generated;
	}
	public void setWaste_generated(String waste_generated) {
		this.waste_generated = waste_generated;
	}
	public String getWaste_diverted() {
		return waste_diverted;
	}
	public void setWaste_diverted(String waste_diverted) {
		this.waste_diverted = waste_diverted;
	}

	
	
}
