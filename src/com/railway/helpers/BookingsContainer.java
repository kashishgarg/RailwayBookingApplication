package com.railway.helpers;

import java.util.HashMap;
import java.util.Map;

import com.railway.enums.Status;
import com.railway.models.Booking;

public class BookingsContainer {
	
	private static Map<Integer, Booking> bookings = new HashMap<>();
	
	public static void add(int bookingId, Booking booking) {
		bookings.put(bookingId, booking);
	}
	
	public static void showBookings() {
		System.out.println("Here are all the bookings: ");
		bookings.forEach((bookingId, booking) -> {
			System.out.println("#Booking " + bookingId);
			System.out.println(booking.toString());
		});
	}
	
	public static void delete(int bookingId) {
		if(bookings.containsKey(bookingId))
			bookings.remove(bookingId);
	}
	
	public static Status getBookingStatus(int bookingId) {
		if(bookings.containsKey(bookingId))
			return bookings.get(bookingId).getStatus();
		return null;
	}
	
	public static Booking getBooking(int bookingId) {
		if(bookings.containsKey(bookingId))
			return bookings.get(bookingId);
		return null;
	}
	
	public static Booking findRACBooking() {
		for(int i = 0; i < bookings.size(); i++) {
			if(bookings.get(i).getStatus() == Status.RAC)
				return bookings.get(i);
		}
		return null;
	}
	
	public static Booking findWaitingBooking() {
		for(int i = 0; i < bookings.size(); i++) {
			if(bookings.get(i).getStatus() == Status.Waiting)
				return bookings.get(i);
		}
		return null;
	}
	
}
