package com.railway.system;

public class AvailabilityTracker {
	
	public static final int CONFIRMED_SEATS = 24;
	public static final int RAC_SEATS = 8;
	public static final int MAX_WAITING = 5;
	
	public static int confirmedSeatsAvailability = CONFIRMED_SEATS; 
	public static int racSeatsAvailability = RAC_SEATS;
	public static int waitingSeatAvailability = MAX_WAITING;
	
	
	public static boolean getconfirmedSeatsAvailability() {
		return confirmedSeatsAvailability > 0;
	}
	
	public static boolean getRacSeatsAvailabilityWhenConfirmedSeatsNotAvailable() {
		return racSeatsAvailability > 0 && confirmedSeatsAvailability == 0;
	}

	
	public static boolean isWaitingListFull() {
		return waitingSeatAvailability > MAX_WAITING;
	}

}
