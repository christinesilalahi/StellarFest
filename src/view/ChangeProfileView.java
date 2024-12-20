package view;

import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;

public class ChangeProfileView extends GridPane {

	private Stage stage;
	private User user;

    private Label titleLabel, emailLabel, usernameLabel, oldPasswordLabel, newPasswordLabel;
    private TextField emailTF, usernameTF, oldPasswordTF, newPasswordTF;
    private Button submit, backToHomePage;

    public void initialize() {
        titleLabel = new Label("Change Profile");
        emailLabel = new Label("Email:");
        usernameLabel = new Label("Username:");
        oldPasswordLabel = new Label("Old Password:");
        newPasswordLabel = new Label("New Password:");

        emailTF = new TextField();
        usernameTF = new TextField();
        oldPasswordTF = new TextField();
        newPasswordTF = new TextField();

        submit = new Button("Submit");
        backToHomePage = new Button("Back to Home");
    }

    public void setLayout() {
        this.setHgap(10); 
        this.setVgap(10); 

        this.add(titleLabel, 0, 0, 2, 1);
        this.add(emailLabel, 0, 1);
        this.add(emailTF, 1, 1);
        this.add(usernameLabel, 0, 2);
        this.add(usernameTF, 1, 2);
        this.add(oldPasswordLabel, 0, 3);
        this.add(oldPasswordTF, 1, 3);
        this.add(newPasswordLabel, 0, 4);
        this.add(newPasswordTF, 1, 4);
        this.add(submit, 0, 5);
        this.add(backToHomePage, 1, 5);
    }

    public void setButton() {
        submit.setOnAction(e -> {
            String email = emailTF.getText();
            String username = usernameTF.getText();
            String oldPassword = oldPasswordTF.getText();
            String newPassword = newPasswordTF.getText();

            boolean success = UserController.changeProfile(email, username, oldPassword, newPassword);
            if (success) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Profile Updated");
                alert.setHeaderText(null);
                alert.setContentText("Your profile has been updated successfully!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to update profile. Please check your input and try again.");
                alert.showAndWait();
            }
        });

        backToHomePage.setOnAction(e -> {
        	String role = user.getRole();
        	if ("Admin".equals(role)) {
                new AdminView(stage);
            } else if("Vendor".equals(role)) {
                new VendorView(stage);
            } else if("Event Organizer".equals(role)) {
            	new EventOrganizerView(stage, user);
            } else if("Guest".equals(role)) {
            	new GuestView();
            }
        });
    }

    public ChangeProfileView(Stage stage) {
        this.stage = stage;

        initialize();
        setLayout();
        setButton();

        Scene scene = new Scene(this, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Change Profile");
        stage.show();
    }

}
