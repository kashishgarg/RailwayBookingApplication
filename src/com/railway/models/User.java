package com.railway.models;

import com.railway.enums.Gender;
import com.railway.enums.Portion;

public class User {
	
	private static int idCounter = 0;
	private String name;
	private int age;
	private Gender gender;
	private Boolean isMajor;
	private int userId;
	private Portion berthChoice;
	
	public User(String name, int age, Gender gender, Portion berthChoice) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.isMajor = (age > 5)? true: false;
		this.berthChoice = berthChoice;
		this.userId = createID();
	}
	
	private static synchronized int createID() {
	    return idCounter++;
	}
	
	public int getUserId() {
		return this.userId;
	}

}
