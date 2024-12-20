package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.Database;

public class Event {
	private String id;
	private String name;
	private String date;
	private String location;
	private String description;
	private String organizer_id;
	
	public static String generateEventId() {
	    Database db = Database.getInstance();
	    String query = "SELECT id FROM events ORDER BY id DESC LIMIT 1";
	    String newId = "EV001"; // Default ID if the table is empty

	    try (PreparedStatement ps = db.preparedStatement(query);
	         ResultSet rs = ps.executeQuery()) {
	        if (rs.next()) {
	            String lastId = rs.getString("id");
	            int numericPart = Integer.parseInt(lastId.substring(2)); // Extract numeric part after "EV"
	            newId = String.format("EV%03d", numericPart + 1); // Increment and format to 3 digits
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return newId;
	}
	
	public static boolean createEvent(String name, String date, String location, String desc, String organizerID) {
	    Database db = Database.getInstance();
	    String newId = generateEventId();
	    String query = "INSERT INTO events(id, name, date, location, description, organizer_id) VALUES(?, ?, ?, ?, ?, ?)";
	    
	    try (PreparedStatement ps = db.preparedStatement(query)) {
	        ps.setString(1, newId);
	        ps.setString(2, name);
	        ps.setString(3, date);
	        ps.setString(4, location);
	        ps.setString(5, desc);
	        ps.setString(6, organizerID);
	        ps.executeUpdate();
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public Event(String id, String name, String date, String location, String description, String organizer_id) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.location = location;
		this.description = description;
		this.organizer_id = organizer_id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOrganizer_id() {
		return organizer_id;
	}
	public void setOrganizer_id(String organizer_id) {
		this.organizer_id = organizer_id;
	}
	
	

}
