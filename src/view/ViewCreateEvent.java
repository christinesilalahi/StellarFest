package view;

import controller.EventController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class ViewCreateEvent extends GridPane{
	User user;
	Stage stage;
	MenuBar menuBar;
	Button createEventSubmit;
    Label eventName, eventDate, eventLocation, eventDescription, createMessage;
    TextField eventNameField, eventDateField, eventLocationField;
    TextArea eventDescriptionField;
	
	public void initialize() {
        
        eventName = new Label("Event name: ");
        eventDate = new Label("Event date (YYYY-MM-DD): ");
        eventLocation = new Label("Event location: ");
        eventDescription = new Label("Event descriptions: ");

        eventNameField = new TextField();
        eventDateField = new TextField();
        eventLocationField = new TextField();
        eventDescriptionField = new TextArea();
        createEventSubmit = new Button("Create Event");
        
        createMessage = new Label("");
    }
	
	public void setLayout() {
		this.getChildren().clear();

        VBox topContainer = new VBox(menuBar);
        this.add(topContainer, 0, 0, 2, 1);
        
        this.add(eventName, 0, 1);
        this.add(eventNameField, 0, 2);
        this.add(eventDate, 0, 3);
        this.add(eventDateField, 0, 4);
        this.add(eventLocation, 0, 5);
        this.add(eventLocationField, 0, 6);
        this.add(eventDescription, 0, 7);
        this.add(eventDescriptionField, 0, 8);
        
        this.add(createMessage, 0, 10);
        
        this.add(createEventSubmit, 0, 9);
	}
	
	public void setButton() {
		createEventSubmit.setOnAction(e -> {
    		String name = eventNameField.getText();
    		String date = eventDateField.getText();
    		String location = eventLocationField.getText();
    		String desc = eventDescriptionField.getText();
    		
    		createMessage.setText(EventController.createEvent(name, date, location, desc, user.getId()));
        });
	}

	public ViewCreateEvent(Stage stage, MenuBar menuBar, User user) {
		// TODO Auto-generated constructor stub
		this.stage = stage;
		this.menuBar = menuBar;
		this.user = user;
		initialize();
		setLayout();
		setButton();
		Scene scene = new Scene(this, 500, 500); 
		stage.setScene(scene);
		stage.setTitle("Create Event");
		stage.show();
	}

}
