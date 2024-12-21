package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import javafx.beans.property.SimpleBooleanProperty;

public class User {

	private String id;
	private String email;
	private String username;
	private String password;
	private String role;
	
	private SimpleBooleanProperty selected;
	
	public static List<User> viewAllUsers(){
		List<User> users = new ArrayList<User>();
		User user;
		Database db = Database.getInstance();
	    String query = "SELECT id, email, username, password, role FROM users";
	    
	    try (PreparedStatement ps = db.preparedStatement(query)){
			ResultSet resultSet = ps.executeQuery();
			 while (resultSet.next()){
				 user = new User(resultSet.getString("id"), resultSet.getString("email"), 
						 resultSet.getString("username"), resultSet.getString("password"), 
						 resultSet.getString("role"));
				 users.add(user);
			 }
			 return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	
	private static boolean isFieldExists(String field, String value) {
	    Database db = Database.getInstance();
	    String query = "SELECT COUNT(*) AS count FROM users WHERE " + field + " = ?";
	    try (PreparedStatement ps = db.preparedStatement(query)) {
	        ps.setString(1, value);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                int count = rs.getInt("count");
	                return count > 0;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	public static boolean isIdExists(String id) {
	    return isFieldExists("id", id);
	}

	public static boolean isEmailExists(String email) {
	    return isFieldExists("email", email);
	}

	public static boolean isUsernameExists(String username) {
	    return isFieldExists("username", username);
	}

	// Method to generate Id
	public static String generateId(String role) {
	    String prefix = "";

	    // Determine prefix based on role
	    switch(role) {
	        case "Guest":
	            prefix = "G";
	            break;
	        case "Event Organizer":
	            prefix = "EO";
	            break;
	        case "Vendor":
	            prefix = "V";
	            break;
	    }
	    
	    String newId = "";
	    boolean idExists = true;

	    while (idExists) {
	        int randomNumber = (int)(Math.random() * 1000);  // Generates a random number between 0 and 999
	        newId = String.format("%s%03d", prefix, randomNumber);  // Ensures the ID is always 3 digits

	        idExists = isIdExists(newId);
	    }

	    return newId;
	}

	public static boolean Register(String email, String username, String password, String role) {
	    Database db = Database.getInstance();
	    String newId = generateId(role);
	    String query = "INSERT INTO users(id, email, username, password, role) VALUES(?, ?, ?, ?, ?)";
	    
	    try (PreparedStatement ps = db.preparedStatement(query)) {
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

        this.selected = new SimpleBooleanProperty(false);
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
	
	public boolean isSelected() {
	    return selected.get();
	}

	public SimpleBooleanProperty selectedProperty() {
	    return selected;
	}

	public void setSelected(boolean selected) {
	    this.selected.set(selected);
	}
}
