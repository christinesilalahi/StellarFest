package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.Database;

public class User {

	private String id;
	private String email;
	private String username;
	private String password;
	private String role;
	
	public static String generateNextId() {
	    Database db = Database.getInstance();
	    String query = "SELECT id FROM users ORDER BY id DESC LIMIT 1";
	    PreparedStatement ps = db.preparedStatement(query);
	    ResultSet rs = null;
	    String lastId = null;

	    try {
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            lastId = rs.getString("id"); // Get the last ID
	        } else {
	            return "SF000"; // If no rows exist, start with SF000
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    // Increment the numeric part of the ID
	    if (lastId != null) {
	        int num = Integer.parseInt(lastId.substring(2)); // Extract numeric part
	        num++; // Increment
	        return String.format("SF%03d", num); // Format with leading zeros
	    }

	    return "SF000"; // Fallback
	}

	
	public static boolean Register(String email, String username, String password, String role) {
		Database db = Database.getInstance();
		String newId = generateNextId();
		String query = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";
		PreparedStatement ps = db.preparedStatement(query);
		
		try {
			ps.setString(1, newId);
			ps.setString(2, email);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setString(5, role);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static User Login(String email, String password) {
		Database db = Database.getInstance();
		String query = "SELECT * FROM users WHERE email = ? and password = ?";
		ResultSet rs = null;
		PreparedStatement ps = db.preparedStatement(query);
		
		try {
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			while(rs.next()) {
				String id = rs.getString("id");
				String userEmail = rs.getString("email");
				String name = rs.getString("username");
				String pass = rs.getString("password");
				String userRole = rs.getString("role");
				User foundUser = new User(id, userEmail, name, pass, userRole);
				return foundUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public User(String id, String email, String username, String password, String role) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
