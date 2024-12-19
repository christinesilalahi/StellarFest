package model;

public class Guest extends User {
	private String accepted_invatations;

	public Guest(String id, String email, String username, String password, String role, String accepted_invatations) {
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
