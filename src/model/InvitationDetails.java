package model;

public class InvitationDetails {
    private Invitation invitation;
    private String eventName;
    private String eventDate;
    private String eventLocation;
    private String eventDescription;
    private String organizerUsername;

    public InvitationDetails(Invitation invitation, String eventName, String eventDate, 
                             String eventLocation, String eventDescription, String organizerUsername) {
        this.invitation = invitation;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.eventDescription = eventDescription;
        this.organizerUsername = organizerUsername;
    }

    public Invitation getInvitation() {
        return invitation;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getOrganizerUsername() {
        return organizerUsername;
    }
}