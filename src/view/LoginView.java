package view;

import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;

public class LoginView extends GridPane {
	
	Stage stage;
	
	User user;
	Label emailLabel, passwordLabel;
	TextField emailTF, passwordTF;
	Button register, login;
	
	public void initialize() {
		emailLabel = new Label("Email: ");
		passwordLabel = new Label("Password: ");
		
		emailTF = new TextField();
		passwordTF = new TextField();
		
		login = new Button("Login");
		register = new Button("Don't have an account? Register");
	}
	
	public void setLayout() {
		this.add(emailLabel, 0, 0);
		this.add(emailTF, 1, 0);
		this.add(passwordLabel, 0, 1);
		this.add(passwordTF, 1, 1);
		this.add(login, 0, 2);
		this.add(register, 0, 3);
	}

	public void setButton() {
		register.setOnAction(e -> {
			new RegisterView(stage);
		});
		
		login.setOnAction(e -> {
			String email = emailTF.getText();
			String password = passwordTF.getText();
			user = UserController.Login(email, password);
			
			if(user == null) {
				System.out.println("Something is wrong!");
			} else {
	            String role = user.getRole();
	            if ("Admin".equals(role)) {
	                new AdminView(stage, user);
	            } else if("Vendor".equals(role)) {
	                new VendorView(stage);
	            } else if("Event Organizer".equals(role)) {
	            	new EventOrganizerView(stage, user);
	            } else if("Guest".equals(role)) {
	            	new GuestView();
	            }
	        }
		});
	}

	public LoginView(Stage stage) {
		this.stage = stage;
		initialize();
		setLayout();
		setButton();
		Scene scene = new Scene(this, 500, 500);
		stage.setScene(scene);
		stage.setTitle("Login");
		stage.show();
	}

}
