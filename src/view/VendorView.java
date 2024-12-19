package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class VendorView extends GridPane {

	Stage stage;
	
	Label greetings;
	
	Button changeProfile;
	
	public void initialize() {
		greetings = new Label("Helloooo Vendor");
		changeProfile = new Button("Change Profile");
	}
	
	public void setLayout() {
		this.add(greetings, 0, 1);
		this.add(changeProfile, 0, 2);
	}
	
	public void setButton() {
		changeProfile.setOnAction(e -> {
			new ChangeProfileView();
		});
	}

	public VendorView(Stage stage) {
		this.stage = stage;
		initialize();
		setLayout();
		setButton();
		Scene scene = new Scene(this, 500, 500);
		stage.setScene(scene);
		stage.setTitle("Vendor");
		stage.show();
	}

}
