package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdminView extends GridPane {
	
	Stage stage;
	
	Label greetings;
	
	public void initialize() {
		greetings = new Label("Helloooo Admin");
	}
	
	public void setLayout() {
		this.add(greetings, 0, 0);
	}

	public AdminView(Stage stage) {
		this.stage = stage;
		Scene scene = new Scene(this, 500, 500);
		stage.setScene(scene);
		stage.setTitle("Admin");
		stage.show();
	}

}
