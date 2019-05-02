package com.railway.models;

import com.railway.enums.Portion;
import com.railway.enums.Status;

public class BookedSeat {
	
	private int coachId;
	private int seatID;
	private int userId;
	private Portion portion;
	private Status status;
	
	public BookedSeat(int coachId, int seatId, int userId, Portion portion, Status status) {
		this.coachId = coachId;
		this.seatID = seatId;
		this.userId = userId;
		this.portion = portion;
		this.status = status;
	}

	public int getCoachId() {
		return coachId;
	}

	public int getSeatID() {
		return seatID;
	}
}
