package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.Database;

public class EventOrganizer extends User {
	private String events_created;
	
	public static String generateId() {
	    Database db = Database.getInstance();
	    String query = "SELECT id FROM events_created ORDER BY id DESC LIMIT 1";
	    String newId = "EC001";

	    try (PreparedStatement ps = db.preparedStatement(query);
	         ResultSet rs = ps.executeQuery()) {
	        if (rs.next()) {
	            String lastId = rs.getString("id");
	            int numericPart = Integer.parseInt(lastId.substring(2)); 
	            newId = String.format("EC%03d", numericPart + 1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return newId;
	}
	
	public static void createEvent(String events_created, String organizerID) {
		Database db = Database.getInstance();
	    String newId = generateId();
	    String query = "INSERT INTO events_created(id, organizer_id, events_created) VALUES(?, ?, ?)";
	    
	    try (PreparedStatement ps = db.preparedStatement(query)) {
	        ps.setString(1, newId);
	        ps.setString(2, organizerID);
	        ps.setString(3, events_created);
	        ps.executeUpdate();
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public EventOrganizer(String id, String email, String username, String password, String role,
			String events_created) {
		super(id, email, username, password, role);
		this.events_created = events_created;
	}	
	
	public String getEvents_created() {
		return events_created;
	}

	public void setEvents_created(String events_created) {
		this.events_created = events_created;
	}
	
}
