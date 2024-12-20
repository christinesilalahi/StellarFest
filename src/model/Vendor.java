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
    
    public List<InvitationDetails> fetchPendingInvitations() {
        List<InvitationDetails> invitationDetailsList = new ArrayList<>();
        Database db = Database.getInstance();
        String query = "SELECT i.id AS invitation_id, i.event_id, i.user_id, i.invitation_status, " +
                       "e.name AS event_name, e.date AS event_date, e.location AS event_location, " +
                       "e.description AS event_description, u.username AS organizer_username " +
                       "FROM invitations i " +
                       "JOIN events e ON i.event_id = e.id " +
                       "JOIN users u ON e.organizer_id = u.id " +
                       "WHERE i.user_id = ? AND i.invitation_status = 'Pending'";

        try (PreparedStatement ps = db.preparedStatement(query)) {
            ps.setString(1, this.getId());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Invitation invitation = new Invitation(
                        resultSet.getString("invitation_id"),
                        resultSet.getString("event_id"),
                        resultSet.getString("user_id"),
                        resultSet.getString("invitation_status"),
                        null
                );

                Event event = new Event(
                        resultSet.getString("event_id"),
                        resultSet.getString("event_name"),
                        resultSet.getString("event_date"),
                        resultSet.getString("event_location"),
                        resultSet.getString("event_description"),
                        resultSet.getString("user_id")
                );

                InvitationDetails details = new InvitationDetails(
                        invitation,
                        event.getEvent_name(),
                        event.getEvent_date(),
                        event.getEvent_location(),
                        event.getEvent_description(),
                        resultSet.getString("organizer_username")
                );

                invitationDetailsList.add(details);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invitationDetailsList;
    }

 }