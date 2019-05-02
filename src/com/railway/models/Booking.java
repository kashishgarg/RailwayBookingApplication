package com.railway.models;

import com.railway.enums.Status;

public class Booking {
	
	private static int idCounter = 0;
	private int bookingId;
	private BookedSeat[] bookedSeats;
	
	public Booking(BookedSeat[] bookedSeats) {
		this.bookingId = createBookingID();
		this.bookedSeats = bookedSeats;
	}

	public int getBookingId() {
		return bookingId;
	}

	public BookedSeat[] getBookedSeats() {
		return bookedSeats;
	}

	private static synchronized int createBookingID() {
	    return idCounter++;
	}

//	@Override
//	public String toString() {
//		return "BookingId: " + String.valueOf(this.bookingId) + " Status: " + this.status.get() + " Coach: " + String.valueOf(this.coachId) + " Seat: " + String.valueOf(this.seatId);
//	}
	
}
