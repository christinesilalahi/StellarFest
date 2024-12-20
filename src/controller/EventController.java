package controller;

import model.Event;
import model.User;

public class EventController {
	
	public static String checkCreateEventInput(String name, String date, String location, String desc, String organizerID) {
		if(name.isEmpty() || date.isEmpty() || location.isEmpty() || desc.isEmpty() || organizerID.isEmpty()) {
			return "Must fill every single field";
		}if(date != null) {
//			change string format thing
		}if(!User.isIdExists(organizerID)) {
			return "Unkown user";
		}
		return "Event Successfully created";
	}

	public static String createEvent(String name, String date, String location, String desc, String organizerID) {
		String message = checkCreateEventInput(name, date, location, desc, organizerID);
		
		if(message.equals("Event Successfully created")) {
			Event.createEvent(name, date, location, desc, organizerID);
		}
		
		return message;
	}

}
