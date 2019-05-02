package com.railway.system;

import java.io.ObjectInputFilter.Status;

import com.railway.models.BookedSeat;
import com.railway.models.Coach;

public class SeatAllocator {
	
	private Coach[] coaches;
	
	public SeatAllocator(Coach[] coaches) {
		this.coaches = coaches;
	}
	
	public BookedSeat allocateConfirmedSeat(int userId) {
		
		int noOfCoaches= coaches.length;
		int seatId = 0, coachId = 0;
		for(int i = 0; i < noOfCoaches; i++) {
			if(coaches[i].isConfirmedSeatAvailableInCoach()) {
				int bookedConfirmedSeats = coaches[i].getBookedConfirmedSeats();
				coaches[i].setBookedConfirmedSeats(bookedConfirmedSeats + 1);
				seatId = coaches[i].getIdForBookedSeat();
				coachId = i;
				break;
			}
		}
		return new BookedSeat(coachId, seatId, userId, , Status.Confirmed);
	}
	
	public BookedSeat allocateRACTicket(int userId) {
		int noOfCoaches= coaches.length;
		int seatId = 0, coachId = 0;
		for(int i = 0; i < noOfCoaches; i++) {
			if(coaches[i].isRACTicketAvailableInCoach()) {
				int bookedRACSeats = coaches[i].getBookedRACSeats();
				coaches[i].setBookedRACSeats(bookedRACSeats + 1);
				seatId = coaches[i].getIdForBookedSeat();
				coachId = i;
				break;
			}
		}
		return new BookedSeat(coachId, seatId, userId);
	}

}
