package com.railway.system;

import java.util.Scanner;

import com.railway.enums.Gender;
import com.railway.enums.Portion;
import com.railway.enums.Status;
import com.railway.helpers.BookingsContainer;
import com.railway.helpers.UsersContainers;
import com.railway.models.BookedSeat;
import com.railway.models.Booking;
import com.railway.models.User;

public class RailwayBookingService {
	
	private static int MAX_BOOKINGS  = AvailabilityTracker.confirmedSeatsAvailability +
									   AvailabilityTracker.racSeatsAvailability +
									   AvailabilityTracker.waitingSeatAvailability;
	private SeatAllocator seatAllocator;
	
	public RailwayBookingService(SeatAllocator seatAllocator) {
		this.seatAllocator = seatAllocator;
	}
	
	public void bookTicket() {
		showStatusOfTickets();
		System.out.println("How many tickets do you want to book");
		Scanner input = new Scanner(System.in);
		int tickets = input.nextInt();
		BookedSeat[] bookedSeats = new BookedSeat[tickets];
		Booking booking = new Booking(bookedSeats);
		int i = 0;
		while(tickets > 0) {
			if(AvailabilityTracker.confirmedSeatsAvailability > 0) {
				User user = takeUserDetailsForBooking(i);
				UsersContainers.add(user.getUserId(), user);
				BookedSeat bookedSeat = seatAllocator.allocateConfirmedSeat(user.getUserId());
				booking.getBookedSeats()[i] = bookedSeat;
				System.out.println("Booking Confirmed - Coach: " + bookedSeat.getCoachId() + " seat: " + bookedSeat.getSeatID() + " bookingId: " + booking.getBookingId());
				BookingsContainer.add(booking.getBookingId(), booking);
				AvailabilityTracker.confirmedSeatsAvailability--;
				tickets--;
				i++;
				continue;
			} else if(AvailabilityTracker.getRacSeatsAvailabilityWhenConfirmedSeatsNotAvailable()) {
				User user = takeUserDetailsForBooking(i);
				UsersContainers.add(user.getUserId(), user);
				BookedSeat bookedSeat = seatAllocator.allocateConfirmedSeat(user.getUserId());
				booking.getBookedSeats()[i] = bookedSeat;
				BookingsContainer.add(booking.getBookingId(), booking);
				AvailabilityTracker.racSeatsAvailability--;
				i++;
				tickets--;
				continue;
			} else if(!AvailabilityTracker.isWaitingListFull()) {
				User user = takeUserDetailsForBooking(i);
				UsersContainers.add(user.getUserId(), user);
				bookedSeat = new BookedSeat(-1, -1, user.getUserId(), -1, Status.Waiting);
				Booking booking = new Booking(Status.Waiting, user.getUserId(), -1, -1);
				BookingsContainer.add(booking.getBookingId(), booking);
				AvailabilityTracker.waitingSeatAvailability--;
				i++;
				tickets--;
				continue;
			} else {
				System.out.println("NO TICKETS AVAILABLE!!!");
			}
		}	
		
	}
	
	public void cancelTicket() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the booking id: ");
		int bookingId = input.nextInt();
		Status status = BookingsContainer.getBookingStatus(bookingId);
		if(status == null) {
			System.out.println("There are no bookings with this booking id");
			return;
		} else if(status == Status.Confirmed) {
			System.out.println("Booking cancelled with booking id: " + bookingId);
			if(AvailabilityTracker.racSeatsAvailability != AvailabilityTracker.RAC_SEATS) {
				reallocateTicketsForConfirmedStatus(bookingId);
				AvailabilityTracker.racSeatsAvailability++;
			} else 
				AvailabilityTracker.confirmedSeatsAvailability++;
			BookingsContainer.delete(bookingId);
			return;
		} else if (status == Status.RAC) {
			System.out.println("Booking cancelled with booking id: " + bookingId);
			if(AvailabilityTracker.waitingSeatAvailability != AvailabilityTracker.MAX_WAITING) {
				reallocateTicketsForRACStatus(bookingId);
				AvailabilityTracker.waitingSeatAvailability++;
			} else
				AvailabilityTracker.racSeatsAvailability++;
			AvailabilityTracker.racSeatsAvailability++;
			return;
		} else {
			System.out.println("Booking cancelled with booking id: " + bookingId);
			AvailabilityTracker.waitingSeatAvailability++;
			BookingsContainer.delete(bookingId);
		}	
	}
	
	public void reallocateTicketsForConfirmedStatus(int bookingId) {
			Booking bookingToBeDeleted = BookingsContainer.getBooking(bookingId);
			Booking bookingToBePromoted = BookingsContainer.findRACBooking();
			if(bookingToBePromoted != null) {
				bookingToBePromoted.setStatus(Status.Confirmed);
				bookingToBePromoted.setSeatId(bookingToBeDeleted.getSeatId());
				bookingToBePromoted.setCoachId(bookingToBeDeleted.getCoachId());
			}
	}
	
	public void reallocateTicketsForRACStatus(int bookingId) {
		Booking bookingToBeDeleted = BookingsContainer.getBooking(bookingId);
		Booking bookingToBePromoted = BookingsContainer.findWaitingBooking();
		if(bookingToBePromoted != null) {
			bookingToBePromoted.setStatus(Status.RAC);
			bookingToBePromoted.setSeatId(bookingToBeDeleted.getSeatId());
			bookingToBePromoted.setCoachId(bookingToBeDeleted.getCoachId());
		}
	}

	private void showStatusOfTickets() {
		System.out.println("Available Tickets: " + AvailabilityTracker.confirmedSeatsAvailability +
							" RAC Tickets: " + AvailabilityTracker.racSeatsAvailability +
							" Waiting Tickets: " + AvailabilityTracker.waitingSeatAvailability);
	}
	
	
	public void printAvailableTickets() {
		
	}
	
	public void printBookedTickets() {
		
	}
	
	private User takeUserDetailsForBooking(int i) {
		Scanner input = new Scanner(System.in);
		System.out.println("User " + (i + 1) + ":");
		System.out.println("Username:");
		String name = input.nextLine();
		System.out.println("Age:");
		int age = input.nextInt();
		input.nextLine();
		System.out.println("Gender(m/f):");
		String genderString = input.nextLine();
		Gender gender = genderString.toLowerCase() == "m"? Gender.Male: Gender.Female;
		System.out.println("Portion(U/M/L): ");
		String portionString = input.nextLine();
		Portion berth = getPortion(portionString);
		return new User(name, age, gender, berth);
	}
	
	Portion getPortion(String choice) {
		switch(choice) {
			case "U": return Portion.UPPER;
			case "M": return Portion.MIDDLE;
			case "L": return Portion.LOWER;
			default: return null;
		}
	}

}
