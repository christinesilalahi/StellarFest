package controller;

import model.Event;

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
	
	private static boolean isFutureDate(String date) {
	    try {
	        java.time.LocalDate eventDate = java.time.LocalDate.parse(date);
	        java.time.LocalDate today = java.time.LocalDate.now();
	        return eventDate.isAfter(today);
	    } catch (java.time.format.DateTimeParseException e) {
	        return false;
	    }
	}
	
	public static String checkCreateEventInput(String name, String date, String location, String desc, String organizerID) {
		if(name.isEmpty() || date.isEmpty() || location.isEmpty() || desc.isEmpty() || organizerID.isEmpty()) {
			return "Must fill every single field";
		}if(!isValidDate(date)) {
			return "Invalid date format";
		}if (!isFutureDate(date)) {
	        return "The date must be set in the future.";
	    }if(location.length()<5) {
			return "Location must be a minimum of 5 character";
		}
		if(desc.length()>200) {
			return "Descriptions must be a maximum of 200 character";
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
