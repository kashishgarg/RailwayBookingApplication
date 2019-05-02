package com.railway.models;

import com.railway.enums.Portion;

public class Seat {
	
	private int seatId;
	private Portion type;
	
	public Seat(int seatId, Portion type) {
		this.seatId = seatId;
		this.type = type;
	}
	

}
