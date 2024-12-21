package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import database.Database;

public class Vendor extends User {

    private String acceptedInvitations;

    public Vendor(String id, String email, String username, String password, String role, String acceptedInvitations) {
        super(id, email, username, password, role);
        this.acceptedInvitations = acceptedInvitations;
    }

    public String getAcceptedInvitations() {
        return acceptedInvitations;
    }

    public void setAcceptedInvitations(String acceptedInvitations) {
        this.acceptedInvitations = acceptedInvitations;
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
    
    public boolean manageVendor(String name, String description) {
        Database db = Database.getInstance();
        String query = "INSERT INTO products(vendor_id, product_name, product_description) VALUES(?, ?, ?)";
        
        try (PreparedStatement ps = db.preparedStatement(query)) {
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