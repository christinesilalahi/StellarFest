package controller;

import java.util.List;

import model.Event;
import model.Guest;
import model.User;
import model.Vendor;

public class AdminController {

	public static List<Event> viewAllEvents(){
		
		return Event.viewAllEvents();
	}
	
	public static List<User> viewAllUser(){
			
			return User.viewAllUsers();
		}
	
	
	public static List<Guest> getGuestByTransaction(String eventID){
		return Guest.viewOrganizedEventDetails(eventID);
	}
	

	public static List<Vendor> getVendorByTransaction(String eventID){
		return Vendor.getVendorByTransaction(eventID);
	}

}
