package com.railway.helpers;

import com.railway.enums.Portion;
import com.railway.models.Coach;
import com.railway.models.Seat;

public class CoachMaker {
	
	private static int NO_OF_COACHES = 4;
	private static int NO_OF_SEATS = 8;
	private static int coachIdCounter = 0;
	private static int seatIdCounter = 0;
	
	private static Portion[] portions = { Portion.LOWER, 
										  Portion.MIDDLE, 
										  Portion.UPPER, 
										  Portion.SIDE };
	
	private Coach[] coaches;
	
	public CoachMaker() {
		this.coaches = new Coach[NO_OF_COACHES];
	}
	
	public Coach[] getCoaches() {
		return coaches;
	}

	public void allocateCoaches() {
		for(int i = 0; i < NO_OF_COACHES; i++)
			coaches[i] = new Coach(createCoachID(), createSeats());
	}
	
	private Seat[] createSeats() {
		Seat[] seats = new Seat[NO_OF_SEATS];
		for(int i = 0; i < NO_OF_SEATS; i++) 
			seats[i] = new Seat(createSeatID(), portions[i/2]);
		return seats;
	}
	
	private static synchronized int createCoachID() {
	    return coachIdCounter++;
	}
	
	private static synchronized int createSeatID() {
		return seatIdCounter++;
	}
	
}
