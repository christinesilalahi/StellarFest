package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.Database;

public class Invitation {
	private String id;
	private String event_id;
	private String user_id;
	private String invitation_status;
	private String invitation_role;
	
	private static String generateId() {
	    Database db = Database.getInstance();
	    String query = "SELECT id FROM invitations ORDER BY id DESC LIMIT 1";
	    String newId = "IV001";

	    try (PreparedStatement ps = db.preparedStatement(query);
	         ResultSet rs = ps.executeQuery()) {
	        if (rs.next()) {
	            String lastId = rs.getString("id");
	            int numericPart = Integer.parseInt(lastId.substring(2)); 
	            newId = String.format("IV%03d", numericPart + 1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return newId;
	}
	
	public static String sendInvitation(User user, Event event) {
		Database db = Database.getInstance();
		String newId = generateId();
	    String query = "INSERT INTO invitations(id, event_id, user_id, invitation_status, invitation_role) VALUES(?, ?, ?, ?, ?)";
	    
	    try (PreparedStatement ps = db.preparedStatement(query)) {
	        ps.setString(1, newId);
	        ps.setString(2, event.getEvent_id());
	        ps.setString(3, user.getId());
	        ps.setString(4, "Pending");
	        ps.setString(5, user.getRole());
	        ps.executeUpdate();
	        
	        return "successfully sent invitation to " + user.getEmail();
	        
	    } catch (Exception e) {
		    return "failed sending invitation to " + user.getEmail();
	    }
	    
	}
	
	public Invitation(String id, String event_id, String user_id, String invitation_status, String invitation_role) {
		super();
		this.id = id;
		this.event_id = event_id;
		this.user_id = user_id;
		this.invitation_status = invitation_status;
		this.invitation_role = invitation_role;
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getInvitation_status() {
		return invitation_status;
	}
	public void setInvitation_status(String invitation_status) {
		this.invitation_status = invitation_status;
	}
	public String getInvitation_role() {
		return invitation_role;
	}
	public void setInvitation_role(String invitation_role) {
		this.invitation_role = invitation_role;
	}
	
	

}
