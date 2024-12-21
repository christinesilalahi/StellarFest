package controller;

import java.util.List;

import model.Event;
import model.User;

public class AdminController {

	public static List<Event> viewAllEvents(){
		
		return Event.viewAllEvents();
	}
	
public static List<User> viewAllUser(){
		
		return User.viewAllUsers();
	}

}
