package controller;

import java.util.List;

import model.Event;
import model.Guest;
import model.Vendor;

public class EventOrganizerController {

	public static List<Event> viewOrganizedEvents(String organizerID){
		
		return Event.viewOrganizedEvents(organizerID);
	}
	
	public static List<Guest> getGuestByTransaction(String eventID){
		return Guest.viewOrganizedEventDetails(eventID);
	}
	
	public static List<Vendor> getVendorByTransaction(String eventID){
		return Vendor.getVendorByTransaction(eventID);
	}
	
	public static String checkEditNameInput(String eventName) {
		if(eventName.isEmpty()) {
			return "Name can't be empty";
		}
		return "Name successfully edited";
	}
	
	public static String editEventName(String eventId, String eventName) {
		String message = checkEditNameInput(eventName);
		if(message.equals("Name successfully edited")) {
			message = Event.editEventName(eventId, eventName);
		}
		return message;
	}

}
