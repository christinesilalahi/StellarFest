package controller;

import model.User;

public class UserController {

	public static boolean Register(String email, String username, String password, String role) {
		if(!checkRegisterInput(email, username, password, role)) {
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
	
	public static boolean checkRegisterInput(String email, String username, String password, String role) {
		if(email.isEmpty() || username.isEmpty() || password.isEmpty() || role.isEmpty()) {
			return false;
		}
		
		if(!email.contains("@")) {
			return false;
		}
		
		if(password.length() < 5) {
			return false;
		}
		
		if(User.isEmailExists(email)) {
			return false;
		}
		
		if(User.isUsernameExists(username)) {
			return false;
		}
		
		return true;
	}
	
	public static boolean changeProfile(String email, String username, String oldPassword, String newPassword) {
        User currentUser = User.Login(email, oldPassword);

        if (currentUser == null) {
            System.out.println("Invalid current email or password.");
            return false;
        }

        // Validation for email
        if (!email.isEmpty() && !email.equals(currentUser.getEmail()) && User.isEmailExists(email)) {
            System.out.println("Email is already in use.");
            return false;
        }

        // Validation for username
        if (!username.isEmpty() && !username.equals(currentUser.getUsername()) && User.isUsernameExists(username)) {
            System.out.println("Username is already in use.");
            return false;
        }

        // Validation for new password
        if (!oldPassword.isEmpty() && !newPassword.isEmpty()) {
            if (!currentUser.getPassword().equals(oldPassword)) {
                System.out.println("Old password is incorrect.");
                return false;
            }
            if (newPassword.length() < 5) {
                System.out.println("New password must be at least 5 characters long.");
                return false;
            }
        }

        // Update the fields
        if (!email.isEmpty() && !email.equals(currentUser.getEmail())) {
            currentUser.setEmail(email);
        }
        if (!username.isEmpty() && !username.equals(currentUser.getUsername())) {
            currentUser.setUsername(username);
        }
        if (!newPassword.isEmpty()) {
            currentUser.setPassword(newPassword);
        }

        return User.changeProfile(currentUser);
    }

}
