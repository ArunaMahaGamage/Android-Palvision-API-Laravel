package com.palvision.androidpalvisionapilaravel.secondListView;

import java.util.ArrayList;

public class CRUD {

	//declare private data instead of public to ensure the privacy of data field of each class
	private String name;
	private String hometown;

	public CRUD(String name) {
		this.name = name;
	}

	//retrieve user's name
	public String getName(){
		return name;
	}

	//retrieve users' hometown
	public String getHometown(){
		return hometown;
	}

	public static ArrayList<CRUD> getUsers() {
		ArrayList<CRUD> users = new ArrayList<CRUD>();
		users.add(new CRUD("Insert"));
		users.add(new CRUD("Update"));
		users.add(new CRUD("Retrive"));
		users.add(new CRUD("Delete"));

		return users;
	}
}
