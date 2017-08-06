package com.palvision.androidpalvisionapilaravel.main;

import java.util.ArrayList;

public class Main {

	//declare private data instead of public to ensure the privacy of data field of each class
	private String name;
	private String hometown;

	public Main(String name, String hometown) {
		this.name = name;
		this.hometown = hometown;
	}

	//retrieve user's name
	public String getName(){
		return name;
	}

	//retrieve users' hometown
	public String getHometown(){
		return hometown;
	}

	public static ArrayList<Main> getUsers() {
		ArrayList<Main> users = new ArrayList<Main>();
		users.add(new Main("Customer", "San Diego"));
		users.add(new Main("Product", "San Francisco"));
		users.add(new Main("Shops", "San Marco"));
		users.add(new Main("Vendor", "San Marco"));
		users.add(new Main("Sales", "San Marco"));

		return users;
	}
}
