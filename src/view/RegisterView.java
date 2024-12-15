package view;

import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegisterView extends GridPane {
	
	Stage stage;
	
	Label emailLabel, usernameLabel, passwordLabel, roleLabel;
	TextField emailTF, usernameTF, passwordTF;
	ComboBox<String> roleComboBox;
	Button register, login;
	
	public void initialize() {
		emailLabel = new Label("Email: ");
		usernameLabel = new Label("Username: ");
		passwordLabel = new Label("Password: ");
		roleLabel = new Label("Role: ");
		
		emailTF = new TextField();
		usernameTF = new TextField();
		passwordTF = new TextField();
		roleComboBox = new ComboBox<>();
		roleComboBox.getItems().addAll("Admin", "Event Organizer", "Guest", "Vendor");
		
		register = new Button("Register");
		login = new Button("Already have an account? Login");
	}
	
	public void setLayout() {
		this.add(emailLabel, 0, 0);
		this.add(emailTF, 1, 0);
		this.add(usernameLabel, 0, 1);
		this.add(usernameTF, 1, 1);
		this.add(passwordLabel, 0, 2);
		this.add(passwordTF, 1, 2);
		this.add(roleLabel, 0, 3);
		this.add(roleComboBox, 1, 3);
		this.add(register, 0, 4);
		this.add(login, 0, 5);
	}

	public void setButton() {
		login.setOnAction(e -> {
			new LoginView(stage);
		});
		
		register.setOnAction(e -> {
			String email = emailTF.getText();
			String username = usernameTF.getText();
			String password = passwordTF.getText();
			String role = roleComboBox.getValue();
			boolean result = UserController.Register(email, username, password, role);
			
			if(!result) {
				System.out.println("Something is wrong!");
			} else {
				new LoginView(stage);
			}
		});
	}
	
	public RegisterView(Stage stage) {
		this.stage = stage;
		initialize();
		setLayout();
		setButton();
		Scene scene = new Scene(this, 500, 500);
		stage.setScene(scene);
		stage.setTitle("Register");
		stage.show();
	}

}
