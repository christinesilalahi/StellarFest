package model;

public class EventOrganizer extends User {
	private String events_created;

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
