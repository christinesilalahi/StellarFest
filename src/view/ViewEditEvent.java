package view;

import java.util.List;

import controller.EventOrganizerController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Event;

public class ViewEditEvent extends GridPane{
	Stage stage;
	MenuBar menuBar;
	Event event;
	Label editLabel, idLabel, nameLabel, idEvent, nameEvent, message;
	TextField editField;
	Button editSubmit;
	
	public void initialize() {
        
		idLabel = new Label("Event id: ");
		nameLabel = new Label("Old event name: ");
		
		idEvent = new Label(event.getEvent_id());
		nameEvent = new Label(event.getEvent_name());
		
		editLabel = new Label("Insert new Name:");
		editField = new TextField();
		editSubmit = new Button("Edit Event Name");
        
        message = new Label("");
	}
	
	public void setLayout() {
		this.getChildren().clear();

        VBox topContainer = new VBox(menuBar);
        this.add(topContainer, 0, 0, 2, 1);
        
        this.add(idLabel, 0, 1);
        this.add(idEvent, 1, 1);
        this.add(nameLabel, 0, 2);
        this.add(nameEvent, 1, 2);
        
        this.add(editLabel, 0, 7);
        this.add(editField, 1, 7);
        this.add(message, 0, 8);
        this.add(editSubmit, 0, 9);
	}
	
	public void setButton() {
		editSubmit.setOnAction(e -> {
			String newName = editField.getText();
			message.setText(EventOrganizerController.editEventName(event.getEvent_id(), newName));
        });
	}
	

	public ViewEditEvent(Stage stage, MenuBar menuBar, Event event) {
		// TODO Auto-generated constructor stub
		this.stage = stage;
		this.menuBar = menuBar;
		this.event = event;
		initialize();
		setLayout();
		setButton();
		Scene scene = new Scene(this, 500, 500);
		stage.setScene(scene);
		stage.setTitle("All Events");
		stage.show();
	}

}
