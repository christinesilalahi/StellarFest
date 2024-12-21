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
		this.setAccepted_invatations(accepted_invatations);
	}
    
    public boolean acceptInvitation(String eventID) {
        Database db = Database.getInstance();
        String query = "UPDATE invitations SET invitation_status = 'Accepted' WHERE event_id = ? AND user_id = ?";
        
        try (PreparedStatement ps = db.preparedStatement(query)) {
            ps.setString(1, eventID);
            ps.setString(2, this.getId());
            int rowsUpdated = ps.executeUpdate();
            
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

	public String getAccepted_invatations() {
		return accepted_invatations;
	}

	public void setAccepted_invatations(String accepted_invatations) {
		this.accepted_invatations = accepted_invatations;
	}
	
	public static List<Invitation> fetchInvitationsByVendorId(String vendorId) {
        List<Invitation> invitations = new ArrayList<>();
        String query = "SELECT * FROM invitation WHERE vendor_id = ? AND invitation_status IN ('pending', 'accepted')";

        try (PreparedStatement ps = Database.getInstance().preparedStatement(query)) {
            ps.setString(1, vendorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Invitation invitation = new Invitation();
                invitation.setId(rs.getString("id"));
                invitation.setVendorId(rs.getString("vendor_id"));
                invitation.setEventId(rs.getString("event_id"));
                invitation.setStatus(rs.getString("invitation_status"));
                invitations.add(invitation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invitations;
    }

    public static boolean updateInvitationStatus(String invitationId, String status) {
        String query = "UPDATE invitation SET invitation_status = ? WHERE id = ?";

        try (PreparedStatement ps = Database.getInstance().preparedStatement(query)) {
            ps.setString(1, status);
            ps.setString(2, invitationId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean manageProduct(String name, String description) {
        String query = "INSERT INTO products(vendor_id, product_name, product_description) VALUES(?, ?, ?)";

        try (PreparedStatement ps = Database.getInstance().preparedStatement(query)) {
            ps.setString(1, this.getId());
            ps.setString(2, name);
            ps.setString(3, description);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}