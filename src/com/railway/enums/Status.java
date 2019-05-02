package com.railway.enums;

public enum Status {
	
	Confirmed("Confirmed"),
	RAC("RAC"),
	Waiting("Waiting");

	private String status;
	
	Status(String status) {
		this.status = status;
	}
	
	public String get() {
		return status;
	}
	
}
