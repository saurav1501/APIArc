package com.Utill;

public class MeterData{

	public String end_date;
	public String reading;
	public String start_date;

	
    public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getReading() {
		return reading;
	}
	public void setReading(String reading) {
		this.reading = reading;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
    
	@Override
	public String toString() {
		return "MeterData [end_date=" + end_date + ", reading=" + reading + ", start_date=" + start_date + "]";
	}

}