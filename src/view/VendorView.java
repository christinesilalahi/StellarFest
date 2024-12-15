package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class VendorView extends GridPane {

	Stage stage;
	
	Label greetings;
	
	public void initialize() {
		greetings = new Label("Helloooo Vendor");
	}
	
	public void setLayout() {
		this.add(greetings, 1, 0);
	}

	public VendorView(Stage stage) {
		this.stage = stage;
		Scene scene = new Scene(this, 500, 500);
		stage.setScene(scene);
		stage.setTitle("Vendor");
		stage.show();
	}

}
