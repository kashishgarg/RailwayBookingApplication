package com.railway.system;

import java.util.Scanner;

import com.railway.helpers.BookingsContainer;
import com.railway.helpers.CoachMaker;

public class RailwayBookingSystem {
	
	public static void main(String[] args) {
		
		CoachMaker coachMaker = new CoachMaker();
		coachMaker.allocateCoaches();		
		
		SeatAllocator seatAllocator = new SeatAllocator(coachMaker.getCoaches());
		
		int choice = 1;
		RailwayBookingService service = new RailwayBookingService(seatAllocator);
		
		while(choice == 1) {
			
			System.out.println("===========Railway System==========");
			System.out.println("1. book the ticket");
			System.out.println("2. cancel the ticket");
			System.out.println("3. print the available tickets");
			System.out.println("4. print the booked tickets");

			Scanner input = new Scanner(System.in);
			int userChoice = input.nextInt();
			
			switch(userChoice) {
				case 1:	service.bookTicket();
						break;
				case 2: service.cancelTicket();
						break;
				case 3: service.printAvailableTickets();
						break;
				case 4: BookingsContainer.showBookings();
						break;
				default: System.out.println("Choose correct option");
			}
			input.nextLine();
			System.out.println("Want to continue (yes/no)??");
			choice = input.nextInt();
		}
	}

}
