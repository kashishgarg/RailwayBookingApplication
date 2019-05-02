package com.railway.models;

public class Coach {
	
	private static
	
	private static int MAX_CONFIRMED_SEATS = 6;
	private static int MAX_RAC_SEATS = 2;
	
	private int coachId;
	private Seat[] seats;
	private int bookedConfirmedSeats = 0;
	private int bookedRACSeats = 0;
	
	public Coach(int coachId, Seat[] seats) {
		this.coachId = coachId;
		this.seats = seats;
	}
	
	public boolean trackAvailableSeatsInCoach() {
		return this.bookedConfirmedSeats + this.bookedRACSeats < MAX_RAC_SEATS + MAX_CONFIRMED_SEATS;
	}
	
	public boolean isConfirmedSeatAvailableInCoach() {
		return this.bookedConfirmedSeats < MAX_CONFIRMED_SEATS;
	}
	
	public int getBookedConfirmedSeats() {
		return bookedConfirmedSeats;
	}

	public void setBookedConfirmedSeats(int bookedConfirmedSeats) {
		this.bookedConfirmedSeats = bookedConfirmedSeats;
	}

	public int getBookedRACSeats() {
		return bookedRACSeats;
	}

	public void setBookedRACSeats(int bookedRACSeats) {
		this.bookedRACSeats = bookedRACSeats;
	}

	public boolean isRACTicketAvailableInCoach() {
		return this.bookedConfirmedSeats == MAX_CONFIRMED_SEATS && this.bookedRACSeats < MAX_RAC_SEATS;
	}
	
	public int getCoachId() {
		return this.coachId;
	}
	
	public int getIdForBookedSeat() {
		return this.bookedConfirmedSeats + this.bookedRACSeats;
	}
	

}
