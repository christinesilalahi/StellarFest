package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Database;

public class Event {
	private String event_id;
	private String event_name;
	private String event_date;
	private String event_location;
	private String event_description;
	private String event_organizer_id;
	
//	public static String 
	
	public static List<Event> viewAllEvents(){
		List<Event> events = new ArrayList<Event>();
		Event event;
		Database db = Database.getInstance();
	    String query = "SELECT id, name, date, location, description, organizer_id FROM events";
	    
	    try (PreparedStatement ps = db.preparedStatement(query)){
			ResultSet resultSet = ps.executeQuery();
			 while (resultSet.next()){
				 event = new Event(resultSet.getString("id"), resultSet.getString("name"), 
						 resultSet.getString("date"), resultSet.getString("location"), 
						 resultSet.getString("description"), resultSet.getString("organizer_id"));
				 events.add(event);
			 }
			 return events;
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	public static List<Event> viewOrganizedEvents(String organizerID){
		List<Event> events = new ArrayList<Event>();
		Event event;
		Database db = Database.getInstance();
	    String query = "SELECT id, name, date, location, description FROM events WHERE organizer_id = ?";
	    
	    try (PreparedStatement ps = db.preparedStatement(query)){
			ps.setString(1, organizerID);
			ResultSet resultSet = ps.executeQuery();
			 while (resultSet.next()){
				 event = new Event(resultSet.getString("id"), resultSet.getString("name"), 
						 resultSet.getString("date"), resultSet.getString("location"), 
						 resultSet.getString("description"), organizerID);
				 events.add(event);
			 }
			 return events;
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	public static String editEventName(String eventId, String eventName) {
		Database db = Database.getInstance();
		String query = "UPDATE events SET name = ? WHERE id = ?";
		String messageString;
		
		try (PreparedStatement ps = db.preparedStatement(query)) {
		        ps.setString(1, eventName);
		        ps.setString(2, eventId);
		        int rowsUpdated = ps.executeUpdate();
		        
		        if (rowsUpdated > 0) {
		        	messageString = "Event name updated successfully.";
		        } else {
		        	messageString = "No event found with the given ID.";
		        }
		    } catch (SQLException e) {
		    	messageString = "Error updating event name: " + e.getMessage();
		    }
		return messageString;
	}
	
	private static String generateId() {
	    Database db = Database.getInstance();
	    String query = "SELECT id FROM events ORDER BY id DESC LIMIT 1";
	    String newId = "EV001";

	    try (PreparedStatement ps = db.preparedStatement(query);
	         ResultSet rs = ps.executeQuery()) {
	        if (rs.next()) {
	            String lastId = rs.getString("id");
	            int numericPart = Integer.parseInt(lastId.substring(2)); 
	            newId = String.format("EV%03d", numericPart + 1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return newId;
	}
	
	public static void createEvent(String name, String date, String location, String desc, String organizerID) {
	    Database db = Database.getInstance();
	    String newId = generateId();
	    String query = "INSERT INTO events(id, name, date, location, description, organizer_id) VALUES(?, ?, ?, ?, ?, ?)";
	    
	    try (PreparedStatement ps = db.preparedStatement(query)) {
	        ps.setString(1, newId);
	        ps.setString(2, name);
	        ps.setString(3, date);
	        ps.setString(4, location);
	        ps.setString(5, desc);
	        ps.setString(6, organizerID);
	        ps.executeUpdate();
	        
	        EventOrganizer.createEvent(newId, organizerID);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	

	public Event(String event_id, String event_name, String event_date, String event_location, String event_description,
			String event_organizer_id) {
		super();
		this.event_id = event_id;
		this.event_name = event_name;
		this.event_date = event_date;
		this.event_location = event_location;
		this.event_description = event_description;
		this.event_organizer_id = event_organizer_id;
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getEvent_date() {
		return event_date;
	}

	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}

	public String getEvent_location() {
		return event_location;
	}

	public void setEvent_location(String event_location) {
		this.event_location = event_location;
	}

	public String getEvent_description() {
		return event_description;
	}

	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}

	public String getEvent_organizer_id() {
		return event_organizer_id;
	}

	public void setEvent_organizer_id(String event_organizer_id) {
		this.event_organizer_id = event_organizer_id;
	}
	
	

}
