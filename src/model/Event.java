package model;

public class Event {
	private String id;
	private String name;
	private String date;
	private String location;
	private String description;
	private String organizer_id;
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
