package controller;

import java.util.List;

import model.Event;

public class EventOrganizerController {

	public static List<Event> viewOrganizedEvents(String organizerID){
		
		return Event.viewOrganizedEvents(organizerID);
	}

}
