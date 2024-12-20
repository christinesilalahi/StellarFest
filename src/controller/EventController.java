package controller;

import model.Event;
import model.User;

public class EventController {
	
	private static boolean isValidDate(String date) {
	    String datePattern = "^\\d{4}-\\d{2}-\\d{2}$";
	    if (!date.matches(datePattern)) {
	        return false;
	    }
	    try {
	        java.time.LocalDate.parse(date);
	    } catch (java.time.format.DateTimeParseException e) {
	        return false;
	    }

	    return true;
	}
	
	public static String checkCreateEventInput(String name, String date, String location, String desc, String organizerID) {
		if(name.isEmpty() || date.isEmpty() || location.isEmpty() || desc.isEmpty() || organizerID.isEmpty()) {
			return "Must fill every single field";
		}if(!isValidDate(date)) {
			return "Invalid date format";
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
