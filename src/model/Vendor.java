package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.Database;

public class Vendor extends User {
	private String accepted_invatations;
	
	public static List<User> getGuest(String eventID){
		List<User> vendors = new ArrayList<User>();
		User vendor;
		Database db = Database.getInstance();
	    String query =  "SELECT DISTINCT u.id, u.email, u.username, u.password, u.role\r\n"
	    		+ "        FROM users u\r\n"
	    		+ "        LEFT JOIN accepted_invitation ai ON u.id = ai.user_id\r\n"
	    		+ "        WHERE u.role = 'Vendor'\r\n"
	    		+ "          AND (ai.accepted_invitation != ? OR ai.accepted_invitation IS NULL)";
	    
	    try (PreparedStatement ps = db.preparedStatement(query)){
			ps.setString(1, eventID);
			ResultSet resultSet = ps.executeQuery();
			 while (resultSet.next()){
				 vendor = new User(
			                resultSet.getString("id"),
			                resultSet.getString("email"),
			                resultSet.getString("username"),
			                resultSet.getString("password"),
			                resultSet.getString("role")
			            );
				 vendors.add(vendor);
			 }
			 return vendors;
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	public static List<Vendor> getVendorByTransaction(String eventID){
		List<Vendor> vendors = new ArrayList<Vendor>();
		Vendor vendor;
		Database db = Database.getInstance();
	    String query =  "SELECT u.id, u.email, u.username, u.password, u.role, ai.accepted_invitation " +
                "FROM users u " +
                "JOIN accepted_invitation ai ON u.id = ai.user_id " +
                "WHERE ai.accepted_invitation = ? AND u.role = 'Vendor'";
	    
	    try (PreparedStatement ps = db.preparedStatement(query)){
			ps.setString(1, eventID);
			ResultSet resultSet = ps.executeQuery();
			 while (resultSet.next()){
				 vendor = new Vendor(
			                resultSet.getString("id"),
			                resultSet.getString("email"),
			                resultSet.getString("username"),
			                resultSet.getString("password"),
			                resultSet.getString("role"),
			                resultSet.getString("accepted_invitation")
			            );
				 vendors.add(vendor);
			 }
			 return vendors;
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	public Vendor(String id, String email, String username, String password, String role, String accepted_invatations) {
		super(id, email, username, password, role);
		this.accepted_invatations = accepted_invatations;
	}

	public String getAccepted_invatations() {
		return accepted_invatations;
	}

	public void setAccepted_invatations(String accepted_invatations) {
		this.accepted_invatations = accepted_invatations;
	}
	
}
