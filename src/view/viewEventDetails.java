package view;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;

public class viewEventDetails extends GridPane{
	User user;
	Stage stage;
	MenuBar menuBar;
	

	public viewEventDetails(Stage stage, MenuBar menuBar, User user) {
		// TODO Auto-generated constructor stub
		this.stage = stage;
		this.user = user;
		this.menuBar = menuBar;
//		initialize();
//		setLayout();
		Scene scene = new Scene(this, 500, 500);
		stage.setScene(scene);
		stage.setTitle("All Events");
		stage.show();
	}

}
