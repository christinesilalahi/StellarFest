package controller;

import model.User;

public class UserController {

	public static boolean Register(String email, String username, String password, String role) {
		if(email.isEmpty() || username.isEmpty() || password.isEmpty() || role.isEmpty()) {
			return false;
		}
		
		if(password.length() < 5) {
			return false;
		}
		
		return User.Register(email, username, password, role);
	}

	public static User Login(String email, String password) {
		if(email.isEmpty() || password.isEmpty()) {
			return null;
		}
		
		return User.Login(email, password);
	}

}
